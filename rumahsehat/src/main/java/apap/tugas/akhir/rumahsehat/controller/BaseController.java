package apap.tugas.akhir.rumahsehat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {
    @GetMapping("/")
    private String Home() {
        return "pages/home";
    }
}