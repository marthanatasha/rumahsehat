package apap.tugas.akhir.rumahsehat.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.tugas.akhir.rumahsehat.model.users.PasienModel;
import apap.tugas.akhir.rumahsehat.service.PasienService;

@Controller
public class PasienController {

    @Autowired
    PasienService pasienService;

    // List pasien
    @GetMapping("/pasien")
    public String getPasienList(Model model) {
        model.addAttribute("pasiens", pasienService.getListPasien());
        return "dashboard/pasien/list";
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
