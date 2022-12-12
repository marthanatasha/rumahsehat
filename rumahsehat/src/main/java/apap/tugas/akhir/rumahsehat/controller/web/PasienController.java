package apap.tugas.akhir.rumahsehat.controller.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.tugas.akhir.rumahsehat.model.users.PasienModel;
import apap.tugas.akhir.rumahsehat.service.PasienService;
import apap.tugas.akhir.rumahsehat.service.UserService;

@Controller
public class PasienController {

    @Autowired
    PasienService pasienService;

    @Autowired
    UserService userService;

    // List pasien
    @GetMapping("/pasien")
    public String getPasienList(Model model, Principal principal) {
        if (userService.isAdmin(principal)) {
            model.addAttribute("pasiens", pasienService.getListPasien());
            return "dashboard/pasien/list";
        } else {
            return "error/404";
        }
        
    }

    // Form create pasien
    @GetMapping("/pasien/add")
    public String getPasienAddForm(Model model) {
        return "dashboard/pasien/form-add";
    }

    // Confirmation create pasien
    @PostMapping(value = "/pasien/add")
    public String postPasienAddForm(
            @ModelAttribute PasienModel pasien, Model model) {
        return "dashboard/pasien/confirmation-add";
    }
}
