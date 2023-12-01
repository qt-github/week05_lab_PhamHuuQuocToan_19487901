package vn.edu.iuh.fit.frontend.controller;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.backend.enums.SkillLevel;
import vn.edu.iuh.fit.backend.models.*;
import vn.edu.iuh.fit.backend.repositories.AddressRepository;
import vn.edu.iuh.fit.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.backend.services.*;
import vn.edu.iuh.fit.backend.services.impl.CandidateImplService;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateRepository candidateRepository;
    private final CandidateSkillService candidateSkillService;
    private final CandidateImplService candidateImplService;
    private final CandidateService candidateService;

    private final AddressRepository addressRepository;

    private final SkillService skillService;

    private final JobService jobService;

    private final ExperienceService experienceService;

    public CandidateController(CandidateRepository candidateRepository, CandidateSkillService candidateSkillService, CandidateImplService candidateImplService, CandidateService candidateService, AddressRepository addressRepository, SkillService skillService, JobService jobService, ExperienceService experienceService) {
        this.candidateRepository = candidateRepository;
        this.candidateSkillService = candidateSkillService;
        this.candidateImplService = candidateImplService;
        this.candidateService = candidateService;
        this.addressRepository = addressRepository;
        this.skillService = skillService;
        this.jobService = jobService;
        this.experienceService = experienceService;
    }

    @GetMapping("/list")
    public String showCandidateList(Model model) {
        model.addAttribute("candidates", candidateRepository.findAll());
        return "candidates/list_no_paging";
    }

    @GetMapping("/list-paging")
    public String CandidateListPaging(Model model, @RequestParam("page")Optional<Integer> page, @RequestParam("size")Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        Page<Candidate> candidatePage = candidateImplService.findAllPaginated(currentPage, pageSize, "id","asc");
        model.addAttribute("candidatePage", candidatePage);

        if (candidatePage.getTotalPages() > 0) {
            model.addAttribute("pageNumbers", IntStream.rangeClosed(1, candidatePage.getTotalPages()).boxed().toList());
        }
        return "candidates/list";
    }

    @GetMapping("/show-add-form")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView();
        Candidate candidate = new Candidate();
        candidate.setAddress(new Address());
        modelAndView.addObject("candidate", candidate);
        modelAndView.addObject("address", candidate.getAddress());
        modelAndView.addObject("countries", CountryCode.values());
        modelAndView.setViewName("candidates/add");
        return modelAndView;
    }

    @PostMapping("/add")
    public String addCandidate(@ModelAttribute("candidate") Candidate candidate, @ModelAttribute("address") Address address) {
        // Kiểm tra xem email đã tồn tại chưa
        Optional<Candidate> existingCandidate = candidateRepository.findByEmail(candidate.getEmail());
        if (existingCandidate.isPresent()) {
            // Nếu đã tồn tại, chuyển hướng về trang thêm mới với thông báo lỗi
            return "redirect:/candidates/show-add-form?error";
        }
        // Nếu chưa tồn tại, thêm mới và chuyển hướng về trang danh sách
        addressRepository.save(address);
        candidate.setAddress(address);
        candidateRepository.save(candidate);
        return "redirect:/candidates/list-paging";
    }

    @GetMapping("/show-edit-form/{id}")
    public ModelAndView edit(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Candidate> opt = candidateRepository.findById(id);
        if(opt.isPresent()) {
            Candidate candidate = opt.get();
            modelAndView.addObject("candidate", candidate);
            modelAndView.addObject("address", candidate.getAddress());
            modelAndView.addObject("countries", CountryCode.values());
            modelAndView.setViewName("candidates/update");
        }
        return modelAndView;
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("candidate") Candidate candidate, @ModelAttribute("address") Address address) {
        addressRepository.save(address);
        candidate.setAddress(address);
        candidateRepository.save(candidate);
        return "redirect:/candidates/list-paging";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        candidateRepository.deleteById(id);
        return "redirect:/candidates/list-paging";
    }

    @GetMapping("/details/{canId}")
    public String detailsCan(Model model, @PathVariable Long canId) {
        Optional<Candidate> candidate = candidateService.getCandidateById(canId);
        List<Job> recommendedJobs = jobService.recommendJobsForCandidate(canId);
        List<Skill> suggestedSkills = skillService.suggestSkillsForCandidate(canId);
        if (!recommendedJobs.isEmpty()) {
            model.addAttribute("recommendedJobs", recommendedJobs);
        } else {
            model.addAttribute("noJobsFound", true);
        }
        if (!suggestedSkills.isEmpty()) {
            model.addAttribute("suggestedSkills", suggestedSkills);
        } else {
            model.addAttribute("noJobsFound", true);
        }
        model.addAttribute("candidate", candidate);
        return "candidates/can-details";
    }

    @GetMapping("/show-add-can-skill/{canId}")
    public ModelAndView showAddJobSkillForm(Model model, @PathVariable("canId") long canId) {
        ModelAndView modelAndView = new ModelAndView();

        CandidateSkill candidateSkill = new CandidateSkill();
        modelAndView.addObject("canId", canId);
        System.out.println(canId);
        modelAndView.addObject("skills", skillService.getAllSkills());
        modelAndView.addObject("canSkill", candidateSkill);
        modelAndView.setViewName("candidates/add-skill");

        return modelAndView;
    }


    @PostMapping("/addCanSkill")
    public String addJobSkill(
            @ModelAttribute("job") CandidateSkill candidateSkill,
            @RequestParam("canId") long canId,
            @RequestParam("skillId") long skillId,
            @RequestParam("skillLevel") SkillLevel skillLevel,

            Model model) {

        Optional<Candidate> candidate = candidateService.getCandidateById(canId);
        candidateSkill.setCandidate(candidate.get());

        Skill skill = skillService.getSkillById(skillId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid skill Id:" + skillId));
        candidateSkill.setSkill(skill);

        candidateSkill.setSkillLevel(skillLevel);


        candidateSkillService.save(candidateSkill);
        return "redirect:/can/details/" + candidate.get().getId();
    }

    @GetMapping("/show-add-exp/{canId}")
    public ModelAndView showAddExperienceForm(Model model,@PathVariable("canId") long canId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("canId", canId);

        modelAndView.addObject("experience", new Experience());
        modelAndView.setViewName("candidates/add-exp");
        return modelAndView;
    }

    @PostMapping("/addExp")
    public String addExperience(@ModelAttribute("experienceForm") Experience experience,            @RequestParam("canId") long canId
    ) {
        Optional<Candidate> candidate= candidateService.getCandidateById(canId);
        experience.setCandidate(candidate.get());
        experienceService.save(experience);
        return "redirect:/can/details/" + canId;
    }
}