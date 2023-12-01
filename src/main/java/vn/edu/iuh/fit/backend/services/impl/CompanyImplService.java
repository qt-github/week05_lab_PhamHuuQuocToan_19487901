package vn.edu.iuh.fit.backend.services.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.backend.models.Company;
import vn.edu.iuh.fit.backend.repositories.CompanyRepository;
import vn.edu.iuh.fit.backend.services.CompanyService;

import java.util.List;
import java.util.Optional;

@Component
public class CompanyImplService implements CompanyService {
    private final CompanyRepository repo;

    private final JavaMailSender emailSender;

    public CompanyImplService(CompanyRepository repo, JavaMailSender emailSender) {
        this.repo = repo;
        this.emailSender = emailSender;
    }

    @Override
    public List<Company> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Company> getById(Long id) {
        return repo.findById(id);
    }

    public void save(Company company) {
        repo.save(company);
    }

    @Override
    public Company update(Company company) {
        return repo.findById(company.getId()).map(existingCompany -> {
            existingCompany.setName(company.getName());
            existingCompany.setAddress(company.getAddress());
            existingCompany.setPhone(company.getPhone());
            existingCompany.setEmail(company.getEmail());

            return repo.save(existingCompany);
        }).orElseThrow(() -> new IllegalArgumentException("Company not found with ID: " + company.getId()));
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void sendSimpleMessage(String email) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");

            String htmlMsg = "<h1>Invitation to Join Our Company</h1>" +
                    "<p>Dear Candidate,</p>" +
                    "<p>We are pleased to extend an offer to join our company. We were impressed with your skills and experience and believe you would be a great addition to our team.</p>" +
                    "<p>We look forward to the possibility of working with you.</p>" +
                    "<p>Best Regards,</p>" +
                    "<p><strong>Sun Jobs</strong></p>";

            message.setContent(htmlMsg, "text/html");
            helper.setTo(email);
            helper.setSubject("Invitation to Join Our Company");

            emailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }

    }


}
