package apap.tugas.akhir.rumahsehatapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apap.tugas.akhir.rumahsehatapi.models.users.DokterModel;
import apap.tugas.akhir.rumahsehatapi.service.DokterService;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class DokterAPIController {

    @Autowired
    DokterService dokterService;

    // List dokter
    @GetMapping("/dokter")
    public List<DokterModel> getDokterList() {
        try {
            return dokterService.getListDokter();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Belum ada dokter tersedia");
        }
    }

    // Detail dokter
    @GetMapping("/dokter/{id}")
    public String getDokterById(@PathVariable Long id, Model models) {
        return "pages/dokter/detail";
    }

    // Form create dokter
    @GetMapping("/dokter/add")
    public String getDokterAddForm(Model models) {
        return "pages/dokter/form-add";
    }

    // Confirmation create dokter
    @PostMapping(value = "/dokter/add")
    public String postDokterAddForm(
            @ModelAttribute DokterModel dokter, Model models) {
        return "pages/dokter/confirmation-add";
    }

    // Form update dokter
    @GetMapping("/dokter/update/{id}")
    public String getDokterAddUpdate(@PathVariable Long id, Model models) {
        return "pages/dokter/form-update";
    }

    // Confirmation update dokter
    @PostMapping(value = "/dokter/update")
    public String postDokterUpdateForm(
            @ModelAttribute DokterModel dokter, Model models) {
        return "pages/dokter/confirmation-update";
    }

    // Delete dokter
    @PostMapping("/dokter/delete")
    public String deletePengajarSubmit(@ModelAttribute DokterModel dokter, Model models) {
        return "pages/dokter/confirmation-delete";
    }
}
