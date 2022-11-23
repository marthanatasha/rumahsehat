package apap.tugas.akhir.rumahsehat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import apap.tugas.akhir.rumahsehat.model.users.AdminModel;
import apap.tugas.akhir.rumahsehat.model.users.UserType;
import apap.tugas.akhir.rumahsehat.repository.AdminDb;
import apap.tugas.akhir.rumahsehat.service.AdminService;

@Controller
public class BaseController {

    @Autowired
    AdminService adminService;

    @GetMapping("/")
    private String Home() {
        return "dashboard/index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/api/initial")
    private String Initial() {
        if (adminService.getListAdmin().size() == 0) {
            AdminModel admin = new AdminModel();
            admin.setEmail("admin@rumahsehat.com");
            admin.setNama("admin utama");
            admin.setPassword("admin");
            admin.setRole(UserType.ADMIN);
            admin.setUsername("admin");
            adminService.addAdmin(admin);
        }

        return "pages/home";
    }
}