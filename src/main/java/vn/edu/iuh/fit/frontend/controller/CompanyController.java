package vn.edu.iuh.fit.frontend.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.backend.enums.SkillLevel;
import vn.edu.iuh.fit.backend.enums.SkillType;
import vn.edu.iuh.fit.backend.models.*;
import vn.edu.iuh.fit.backend.services.CompanyService;
import vn.edu.iuh.fit.backend.services.JobService;
import vn.edu.iuh.fit.backend.services.JobSkillService;
import vn.edu.iuh.fit.backend.services.SkillService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    private final JobService jobService;

    private final SkillService skillService;
    private final JobSkillService jobSkillService;

    public CompanyController(CompanyService companyService, JobService jobService, SkillService skillService, JobSkillService jobSkillService) {
        this.companyService = companyService;
        this.jobService = jobService;
        this.skillService = skillService;
        this.jobSkillService = jobSkillService;
    }


    @GetMapping("/list")
    public String getAllCompanies(Model model) {
        model.addAttribute("companies", companyService.getAll());
        return "company/list";
    }

    @GetMapping("/details/{companyId}")
    public String getCompany(Model model, @PathVariable Long companyId) {
        model.addAttribute("company", companyService.getById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found")));
        return "company/details";
    }

    @GetMapping("/detailsJob/{jobId}")
    public String detailsJob(Model model, @PathVariable Long jobId) {
        Job job = jobService.findById(jobId);
        model.addAttribute("job", job);
        List<Candidate> recommendedCandidates = jobService.recommendCandidatesForJob(jobId);
        model.addAttribute("recommendedCandidates", recommendedCandidates);

        return "company/jobs/details";
    }

    @GetMapping("/addJob/{companyId}")
    public ModelAndView showCompanyJobsForm(@PathVariable Long companyId) {
        ModelAndView modelAndView = new ModelAndView("company/post");

        Job job = new Job();
        List<JobSkill> jobSkills = job.getJobSkills();
        List<Skill> skills = new ArrayList<>();

        if (jobSkills != null) {
            for (JobSkill jobSkill : jobSkills) {
                if (jobSkill.getSkill() != null) {
                    skills.add(jobSkill.getSkill());
                }
            }
        }

        modelAndView.addObject("job", job);
        modelAndView.addObject("company", companyId);
        modelAndView.addObject("skills", skills);
        modelAndView.addObject("type", SkillType.values());


        return modelAndView;
    }

    @GetMapping("/show-add/{companyId}")
    public ModelAndView showAddJobForm(Model model, @PathVariable("companyId") long companyId) {
        ModelAndView modelAndView = new ModelAndView();

        Job job = new Job();
        modelAndView.addObject("companyId", companyId);

        modelAndView.addObject("job", job);
        modelAndView.setViewName("company/jobs/add");

        return modelAndView;
    }

    @PostMapping("/add")
    public String addJob(
            @ModelAttribute("job") Job job,
            @RequestParam("companyId") long companyId,
            Model model) {

        Optional<Company> company = Optional.ofNullable(companyService
                .getById(companyId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Invalid companyId posting Id:" + companyId
                        )));
        job.setCompany(company.get());

        jobService.save(job);

        return "redirect:/company/details/" + companyId;
    }

    @PostMapping("/addSkill")
    public String addJobSkill(
            @ModelAttribute("job") Skill skill,

            Model model) {

        skillService.addSkill(skill);

        return "redirect:/company/list";
    }

    @GetMapping("/show-add-job-skill/{jobId}/{companyId}")
    public ModelAndView showAddJobSkillForm(Model model, @PathVariable("jobId") long jobId, @PathVariable("companyId") long companyId) {
        ModelAndView modelAndView = new ModelAndView();

        JobSkill jobSkill = new JobSkill();
        modelAndView.addObject("jobId", jobId);
        modelAndView.addObject("skills", skillService.getAllSkills());
        modelAndView.addObject("jobSkill", jobSkill);
        modelAndView.setViewName("company/jobSkill/add");

        return modelAndView;
    }


    @PostMapping("/addJobSkill")
    public String addJobSkill(
            @ModelAttribute("job") JobSkill jobSkill,
            @RequestParam("jobId") long jobId,
            @RequestParam("skillId") long skillId,
            @RequestParam("skillLevel") SkillLevel skillLevel,

            Model model) {

        Job job = jobService.findById(jobId);
        jobSkill.setJob(job);

        Optional<Skill> skill = Optional.ofNullable(
                skillService
                        .getSkillById(skillId)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Invalid skill Id:" + skillId
                                )
                        )
        );
        jobSkill.setSkill(skill.get());

        jobSkill.setSkillLevel(skillLevel);


        jobSkillService.save(jobSkill);
        return "redirect:/company/detailsJob/" + job.getId();
    }

    @PostMapping("/send-invitation")
    public String sendInvitation(@RequestParam("id") Long jobId, @RequestParam("email") String email,
                                 Model model) {
        try {
            companyService.sendSimpleMessage(email);
        } catch (DataIntegrityViolationException e) {
            // Handle the exception here, e.g., by adding an error message to the model
            model.addAttribute("error", "Email already exists: " + email);
            return "redirect:/company/detailsJob/" + jobId;
        }

        return "redirect:/company/detailsJob/" + jobId;
    }
}
