package vn.edu.iuh.fit.frontend.controller;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.backend.models.*;
import vn.edu.iuh.fit.backend.repositories.AddressRepository;
import vn.edu.iuh.fit.backend.repositories.JobSkillRepository;
import vn.edu.iuh.fit.backend.services.SkillService;
import vn.edu.iuh.fit.backend.services.impl.CompanyImplService;
import vn.edu.iuh.fit.backend.services.impl.JobImplService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/jobs")
public class JobController {

    private final JobImplService jobImplService;

    private final CompanyImplService companyImplService;

    private final AddressRepository addressRepository;

    private final JobSkillRepository jobSkillRepository;

    private final SkillService skillService;


    public JobController(JobImplService jobImplService, CompanyImplService companyImplService,
                         AddressRepository addressRepository, JobSkillRepository jobSkillRepository, SkillService skillService) {
        this.jobImplService = jobImplService;
        this.companyImplService = companyImplService;
        this.addressRepository = addressRepository;
        this.jobSkillRepository = jobSkillRepository;
        this.skillService = skillService;
    }

    //TODO: get list paging of jobs
    @RequestMapping("/list-paging")
    public String JobListPaging(Model model,
                                      @RequestParam("page") Optional<Integer> page,
                                      @RequestParam("size")Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        Page<Job> jobPage = jobImplService.findAllPaginated(currentPage, pageSize, "id","asc");
        model.addAttribute("jobPage", jobPage);

        int totalPages = jobPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "company/jobs/list";
    }

    //TODO:Get form add job
    @RequestMapping("/show-add-form")
    public ModelAndView showAddForm(Model model){
        ModelAndView modelAndView = new ModelAndView("company/jobs/add");
        Job job = new Job();
        job.setCompany(new Company());
        job.setJobSkills(new ArrayList<>());
        modelAndView.addObject("job", job);
        modelAndView.addObject("companies", job.getCompany());
        modelAndView.addObject("address", job.getCompany().getAddress());
        modelAndView.addObject("jobSkills", job.getJobSkills());
        modelAndView.addObject("countries", CountryCode.values());
        return modelAndView;
    }

    //TODO: Add job
    @PostMapping("/add")
    public String addJob(
            @ModelAttribute("job") Job job,
            @ModelAttribute("company") Company company,
            @ModelAttribute("address") Address address,
            @ModelAttribute("jobSkills") List<JobSkill> jobSkills
    ){
        try {
            company.setAddress(address);
            job.setCompany(company);
            job.setJobSkills(jobSkills);

            jobImplService.save(job);

            return "redirect:/jobs/list-paging";
        } catch (Exception e) {
            return "redirect:/jobs/error-page";
        }
    }


    //TODO: Get form edit job
    @RequestMapping("/show-edit-form/{jobId}")
    public ModelAndView showEditForm(@PathVariable("jobId") Long jobId){
        ModelAndView modelAndView = new ModelAndView("company/jobs/update");
        Optional<Job> opt = jobImplService.findById(jobId);
        if (opt.isPresent()){
            Job job = opt.get();
            modelAndView.addObject("job", job);
            modelAndView.addObject("company", job.getCompany());
            modelAndView.addObject("jobSkills", job.getJobSkills());
            modelAndView.addObject("address", job.getCompany().getAddress());
            modelAndView.addObject("countries", CountryCode.values());
        }
        return modelAndView;
    }

    //TODO: Update job
    @PostMapping("/update")
    public String updateJob(
            @ModelAttribute("job") Job job,
            @ModelAttribute("company") Company company,
            @ModelAttribute("jobSkills") JobSkill[] jobSkills,
            @ModelAttribute("address") Address address
    ){
        companyImplService.save(company);
        job.setCompany(company);
        job.setJobSkills(List.of(jobSkills));
        jobImplService.save(job);
        return "redirect:/jobs/show-edit-form/"+job.getId();
    }

    //TODO: Delete job by id
    @RequestMapping("/delete/{id}")
    public String deleteJob(@PathVariable Long id){
        jobImplService.deleteById(id);
        return "redirect:/jobs/list-paging";
    }

    @PostMapping("/skills/save")
    public String save(Skill skill){
        skillService.addSkill(skill);
        return "redirect:/skills";
    }
}
