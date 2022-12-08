package apap.tugas.akhir.rumahsehat.controller.web;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.tugas.akhir.rumahsehat.model.users.AdminModel;
import apap.tugas.akhir.rumahsehat.service.AdminService;
import apap.tugas.akhir.rumahsehat.service.UserService;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    // List admin
    @GetMapping("/admin")
    public String getAdminList(Model model, Principal principal) {
        if (userService.isAdmin(principal)) {
            model.addAttribute("admins", adminService.getListAdmin());
            return "dashboard/admin/list";
        } else {
            return "error/404";
        }
    }

    // Detail admin
    @GetMapping("/admin/{id}")
    public String getAdminById(@PathVariable Long id, Model model) {
        return "dashboard/admin/detail";
    }

    // Form create admin
    @GetMapping("/admin/add")
    public String getAdminAddForm(Model model) {
        return "dashboard/admin/form-add";
    }

    // Confirmation create admin
    @PostMapping(value = "/admin/add")
    public String postAdminAddForm(
            @ModelAttribute AdminModel admin, Model model) {
        return "dashboard/admin/confirmation-add";
    }

    // Form update admin
    @GetMapping("/admin/update/{id}")
    public String getAdminAddUpdate(@PathVariable Long id, Model model) {
        return "dashboard/admin/form-update";
    }

    // Confirmation update admin
    @PostMapping(value = "/admin/update")
    public String postAdminUpdateForm(
            @ModelAttribute AdminModel admin, Model model) {
        return "dashboard/admin/confirmation-update";
    }

    // Delete admin
    @PostMapping("/admin/delete")
    public String deletePengajarSubmit(@ModelAttribute AdminModel admin, Model model) {
        return "dashboard/admin/confirmation-delete";
    }
}
