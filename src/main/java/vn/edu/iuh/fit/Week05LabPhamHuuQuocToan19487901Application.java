package vn.edu.iuh.fit;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.edu.iuh.fit.backend.module.Address;
import vn.edu.iuh.fit.backend.module.Candidate;
import vn.edu.iuh.fit.backend.repositories.AddressRepository;
import vn.edu.iuh.fit.backend.repositories.CandidateRepository;

import java.time.LocalDate;
import java.util.Random;

@SpringBootApplication
public class Week05LabPhamHuuQuocToan19487901Application {

    public static void main(String[] args) {
        SpringApplication.run(Week05LabPhamHuuQuocToan19487901Application.class, args);
    }
    private CandidateRepository candidateRepository;
    private AddressRepository addressRepository;

    public Week05LabPhamHuuQuocToan19487901Application(CandidateRepository candidateRepository, AddressRepository addressRepository) {
        this.candidateRepository = candidateRepository;
        this.addressRepository = addressRepository;
    }
    @Bean
    CommandLineRunner initData(){
        return args -> {
            Random rnd = new Random();
            int i;
            Address address = null;
//            for (i = 1; i < 1001; i++) {
//                address = new Address(rnd.nextInt(1, 1000) + "", "Quang Trung", "HCM",
//                        rnd.nextInt(70000, 80000) + "");
//                addressRepository.save(address);
//            }
//            Candidate can = new Candidate("Name #" + i,
//                    LocalDate.of(1998, rnd.nextInt(1, 13),
//                            rnd.nextInt(1, 29)),
//                    address,
//                    rnd.nextLong(1111111111L, 9999999999L) + "",
//                    "email_" + i + "@gmail.com");
//            candidateRepository.save(can);
//            System.out.println("Added: " + can);
        };
    }
}
