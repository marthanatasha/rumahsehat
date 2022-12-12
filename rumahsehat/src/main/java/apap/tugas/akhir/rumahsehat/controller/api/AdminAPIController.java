package apap.tugas.akhir.rumahsehat.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.tugas.akhir.rumahsehat.model.users.AdminModel;
import apap.tugas.akhir.rumahsehat.service.AdminService;
import apap.tugas.akhir.rumahsehat.service.AuthService;

@CrossOrigin()
@RestController
@RequestMapping("/api/v1")
public class AdminAPIController {

    @Autowired
    AdminService adminService;

    @Autowired
    AuthService authService;

    // List admin
    @GetMapping("/admin")
    public ResponseEntity<?> getAdminList(@RequestHeader("Authorization") String bearerToken, Model model) {
        if (!authService.tokenCheck(bearerToken)) {
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);

        }

        return new ResponseEntity<>(adminService.getListAdmin(), HttpStatus.OK);
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
