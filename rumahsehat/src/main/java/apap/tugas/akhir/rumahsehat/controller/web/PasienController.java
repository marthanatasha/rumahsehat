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

    // Detail pasien
    @GetMapping("/pasien/{id}")
    public String getPasienById(@PathVariable Long id, Model model) {
        return "dashboard/pasien/detail";
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

    // Form update pasien
    @GetMapping("/pasien/update/{id}")
    public String getPasienAddUpdate(@PathVariable Long id, Model model) {
        return "dashboard/pasien/form-update";
    }

    // Confirmation update pasien
    @PostMapping(value = "/pasien/update")
    public String postPasienUpdateForm(
            @ModelAttribute PasienModel pasien, Model model) {
        return "dashboard/pasien/confirmation-update";
    }

    // Delete pasien
    @PostMapping("/pasien/delete")
    public String deletePengajarSubmit(@ModelAttribute PasienModel pasien, Model model) {
        return "dashboard/pasien/confirmation-delete";
    }
}
