package vn.edu.iuh.fit.week05_lab_phamhuuquoctoan_19487901;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Week05LabPhamHuuQuocToan19487901Application.class);
    }

}
