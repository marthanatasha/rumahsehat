package apap.tugas.akhir.rumahsehat.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.tugas.akhir.rumahsehat.model.users.DokterModel;
import apap.tugas.akhir.rumahsehat.service.DokterService;

@Controller
public class DokterController {

    @Autowired
    DokterService dokterService;

    // List dokter
    @GetMapping("/dokter")
    public String getDokterList(Model model) {
        model.addAttribute("dokters", dokterService.getListDokter());
        return "dashboard/dokter/list";
    }

    // Detail dokter
    @GetMapping("/dokter/{id}")
    public String getDokterById(@PathVariable Long id, Model model) {
        return "dashboard/dokter/detail";
    }

    // Form create dokter
    @GetMapping("/dokter/add")
    public String getDokterAddForm(Model model) {
        return "dashboard/dokter/form-add";
    }

    // Confirmation create dokter
    @PostMapping(value = "/dokter/add")
    public String postDokterAddForm(
            @ModelAttribute DokterModel dokter, Model model) {
        return "dashboard/dokter/confirmation-add";
    }

    // Form update dokter
    @GetMapping("/dokter/update/{id}")
    public String getDokterAddUpdate(@PathVariable Long id, Model model) {
        return "dashboard/dokter/form-update";
    }

    // Confirmation update dokter
    @PostMapping(value = "/dokter/update")
    public String postDokterUpdateForm(
            @ModelAttribute DokterModel dokter, Model model) {
        return "dashboard/dokter/confirmation-update";
    }

    // Delete dokter
    @PostMapping("/dokter/delete")
    public String deletePengajarSubmit(@ModelAttribute DokterModel dokter, Model model) {
        return "dashboard/dokter/confirmation-delete";
    }
}
