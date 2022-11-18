package apap.tugas.akhir.rumahsehat.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import apap.tugas.akhir.rumahsehat.service.AdminService;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/admin")
    public String getAdminList(Model model) {
        model.addAttribute("admins", adminService.getListAdmin());
        return "page/admin/list";
    }

    @GetMapping("/admin/{id}")
    public String updateCourseFormPage(@PathVariable Long id, Model model) {
        return "page/admin/detail";
    }
}
