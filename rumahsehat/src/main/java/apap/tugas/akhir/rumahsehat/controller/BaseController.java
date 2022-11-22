package apap.tugas.akhir.rumahsehat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import apap.tugas.akhir.rumahsehat.model.users.AdminModel;
import apap.tugas.akhir.rumahsehat.model.users.UserType;
import apap.tugas.akhir.rumahsehat.repository.AdminDb;

@Controller
public class BaseController {
    @Autowired
    AdminDb adminDb;

    @GetMapping("/")
    private String Home() {
        return "pages/home";
    }

    @RequestMapping("/login")
    public String login() {
        return "pages/login";
    }

    @GetMapping("/initial")
    private String Initial() {
        if (adminDb.findAll().size() == 0) {
            AdminModel admin = new AdminModel();
            admin.setEmail("admin@rumahsehat.com");
            admin.setNama("admin utama");
            admin.setPassword("admin");
            admin.setRole(UserType.ADMIN);
            admin.setUsername("admin");

            adminDb.save(admin);
        }
        return "pages/home";
    }
}