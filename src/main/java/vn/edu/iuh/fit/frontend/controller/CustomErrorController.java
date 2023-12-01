package vn.edu.iuh.fit.frontend.controller;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // You can return a custom error message or page here
        return "An unexpected error has occurred. Please try again later.";
    }
}