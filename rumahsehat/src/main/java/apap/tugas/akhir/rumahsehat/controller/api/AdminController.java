package apap.tugas.akhir.rumahsehat.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apap.tugas.akhir.rumahsehat.model.users.AdminModel;
import apap.tugas.akhir.rumahsehat.service.AdminService;

@RestController
@RequestMapping("/api/v1")
public class AdminController {

    @Autowired
    AdminService adminService;

    // List admin
    @GetMapping("/admin")
    public String getAdminList(Model model) {
        model.addAttribute("admins", adminService.getListAdmin());
        return "pages/admin/list";
    }

    // Detail admin
    @GetMapping("/admin/{id}")
    public String getAdminById(@PathVariable Long id, Model model) {
        return "pages/admin/detail";
    }

    // Form create admin
    @GetMapping("/admin/add")
    public String getAdminAddForm(Model model) {
        return "pages/admin/form-add";
    }

    // Confirmation create admin
    @PostMapping(value = "/admin/add")
    public String postAdminAddForm(
            @ModelAttribute AdminModel admin, Model model) {
        return "pages/admin/confirmation-add";
    }

    // Form update admin
    @GetMapping("/admin/update/{id}")
    public String getAdminAddUpdate(@PathVariable Long id, Model model) {
        return "pages/admin/form-update";
    }

    // Confirmation update admin
    @PostMapping(value = "/admin/update")
    public String postAdminUpdateForm(
            @ModelAttribute AdminModel admin, Model model) {
        return "pages/admin/confirmation-update";
    }

    // Delete admin
    @PostMapping("/admin/delete")
    public String deletePengajarSubmit(@ModelAttribute AdminModel admin, Model model) {
        return "pages/admin/confirmation-delete";
    }
}
