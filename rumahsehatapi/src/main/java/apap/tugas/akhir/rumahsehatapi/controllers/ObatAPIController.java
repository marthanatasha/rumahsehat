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

import apap.tugas.akhir.rumahsehatapi.models.ObatModel;
import apap.tugas.akhir.rumahsehatapi.service.ObatService;

@RestController
@RequestMapping("/api/v1")
public class ObatAPIController {

    @Autowired
    ObatService obatService;

    // List obat
    @GetMapping("/obat")
    public String getObatList(Model models) {
        models.addAttribute("obats", obatService.getListObat());
        return "pages/obat/list";
    }

    // Detail obat
    @GetMapping("/obat/{id}")
    public String getObatById(@PathVariable Long id, Model models) {
        return "pages/obat/detail";
    }

    // Form create obat
    @GetMapping("/obat/add")
    public String getObatAddForm(Model models) {
        return "pages/obat/form-add";
    }

    // Confirmation create obat
    @PostMapping(value = "/obat/add")
    public String postObatAddForm(
            @ModelAttribute ObatModel obat, Model models) {
        return "pages/obat/confirmation-add";
    }

    // Form update obat
    @GetMapping("/obat/update/{id}")
    public String getObatAddUpdate(@PathVariable Long id, Model models) {
        return "pages/obat/form-update";
    }

    // Confirmation update obat
    @PostMapping(value = "/obat/update")
    public String postObatUpdateForm(
            @ModelAttribute ObatModel obat, Model models) {
        return "pages/obat/confirmation-update";
    }

    // Delete obat
    @PostMapping("/obat/delete")
    public String deletePengajarSubmit(@ModelAttribute ObatModel obat, Model models) {
        return "pages/obat/confirmation-delete";
    }
}
