package apap.tugas.akhir.rumahsehatapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apap.tugas.akhir.rumahsehatapi.models.users.PasienModel;
import apap.tugas.akhir.rumahsehatapi.service.PasienService;

@RestController
@RequestMapping("/api/v1")
public class PasienAPIController {

    @Autowired
    PasienService pasienService;

    // List pasien
    @GetMapping("/pasien")
    public String getPasienList(Model models) {
        models.addAttribute("pasiens", pasienService.getListPasien());
        return "pages/pasien/list";
    }

    // Detail pasien
    @GetMapping("/pasien/{id}")
    public String getPasienById(@PathVariable Long id, Model models) {
        return "pages/pasien/detail";
    }

    // Form create pasien
    @GetMapping("/pasien/add")
    public String getPasienAddForm(Model models) {
        return "pages/pasien/form-add";
    }

    // Confirmation create pasien
    @PostMapping(value = "/pasien/add")
    public String postPasienAddForm(
            @ModelAttribute PasienModel pasien, Model models) {
        return "pages/pasien/confirmation-add";
    }

    // Form update pasien
    @GetMapping("/pasien/update/{id}")
    public String getPasienAddUpdate(@PathVariable Long id, Model models) {
        return "pages/pasien/form-update";
    }

    // Confirmation update pasien
    @PostMapping(value = "/pasien/update")
    public String postPasienUpdateForm(
            @ModelAttribute PasienModel pasien, Model models) {
        return "pages/pasien/confirmation-update";
    }

    // Delete pasien
    @PostMapping("/pasien/delete")
    public String deletePengajarSubmit(@ModelAttribute PasienModel pasien, Model models) {
        return "pages/pasien/confirmation-delete";
    }
}
