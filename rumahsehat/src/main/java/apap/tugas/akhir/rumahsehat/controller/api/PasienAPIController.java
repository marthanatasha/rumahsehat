package apap.tugas.akhir.rumahsehat.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.tugas.akhir.rumahsehat.model.users.PasienModel;
import apap.tugas.akhir.rumahsehat.service.PasienService;

@CrossOrigin()
@RestController
@RequestMapping("/api/v1")
public class PasienAPIController {

    @Autowired
    PasienService pasienService;

    // List pasien
    @GetMapping("/pasien")
    public String getPasienList(Model model) {
        model.addAttribute("pasiens", pasienService.getListPasien());
        return "pages/pasien/list";
    }

    // Detail pasien
    @GetMapping("/pasien/{id}")
    public String getPasienById(@PathVariable Long id, Model model) {
        return "pages/pasien/detail";
    }

    // Form create pasien
    @GetMapping("/pasien/add")
    public String getPasienAddForm(Model model) {
        return "pages/pasien/form-add";
    }

    // Confirmation create pasien
    @PostMapping(value = "/pasien/add")
    public String postPasienAddForm(
            @ModelAttribute PasienModel pasien, Model model) {
        return "pages/pasien/confirmation-add";
    }

    // Form update pasien
    @GetMapping("/pasien/update/{id}")
    public String getPasienAddUpdate(@PathVariable Long id, Model model) {
        return "pages/pasien/form-update";
    }

    // Confirmation update pasien
    @PostMapping(value = "/pasien/update")
    public String postPasienUpdateForm(
            @ModelAttribute PasienModel pasien, Model model) {
        return "pages/pasien/confirmation-update";
    }

    // Delete pasien
    @PostMapping("/pasien/delete")
    public String deletePengajarSubmit(@ModelAttribute PasienModel pasien, Model model) {
        return "pages/pasien/confirmation-delete";
    }
}
