package apap.tugas.akhir.rumahsehat.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.tugas.akhir.rumahsehat.model.users.DokterModel;
import apap.tugas.akhir.rumahsehat.service.DokterService;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin()
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
                HttpStatus.NOT_FOUND, "Belum ada dokter tersedia"
            );
        }
    }

    // Detail dokter
    @GetMapping("/dokter/{id}")
    public String getDokterById(@PathVariable Long id, Model model) {
        return "pages/dokter/detail";
    }

    // Form create dokter
    @GetMapping("/dokter/add")
    public String getDokterAddForm(Model model) {
        return "pages/dokter/form-add";
    }

    // Confirmation create dokter
    @PostMapping(value = "/dokter/add")
    public String postDokterAddForm(
            @ModelAttribute DokterModel dokter, Model model) {
        return "pages/dokter/confirmation-add";
    }

    // Form update dokter
    @GetMapping("/dokter/update/{id}")
    public String getDokterAddUpdate(@PathVariable Long id, Model model) {
        return "pages/dokter/form-update";
    }

    // Confirmation update dokter
    @PostMapping(value = "/dokter/update")
    public String postDokterUpdateForm(
            @ModelAttribute DokterModel dokter, Model model) {
        return "pages/dokter/confirmation-update";
    }

    // Delete dokter
    @PostMapping("/dokter/delete")
    public String deletePengajarSubmit(@ModelAttribute DokterModel dokter, Model model) {
        return "pages/dokter/confirmation-delete";
    }
}
