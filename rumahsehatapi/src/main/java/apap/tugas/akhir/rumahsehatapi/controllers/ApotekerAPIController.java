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

import apap.tugas.akhir.rumahsehatapi.models.users.ApotekerModel;
import apap.tugas.akhir.rumahsehatapi.service.ApotekerService;

@RestController
@RequestMapping("/api/v1")
public class ApotekerAPIController {

    @Autowired
    ApotekerService apotekerService;

    // List apoteker
    @GetMapping("/apoteker")
    public String getApotekerList(Model models) {
        models.addAttribute("apotekers", apotekerService.getListApoteker());
        return "pages/apoteker/list";
    }

    // Detail apoteker
    @GetMapping("/apoteker/{id}")
    public String getApotekerById(@PathVariable Long id, Model models) {
        return "pages/apoteker/detail";
    }

    // Form create apoteker
    @GetMapping("/apoteker/add")
    public String getApotekerAddForm(Model models) {
        return "pages/apoteker/form-add";
    }

    // Confirmation create apoteker
    @PostMapping(value = "/apoteker/add")
    public String postApotekerAddForm(
            @ModelAttribute ApotekerModel apoteker, Model models) {
        return "pages/apoteker/confirmation-add";
    }

    // Form update apoteker
    @GetMapping("/apoteker/update/{id}")
    public String getApotekerAddUpdate(@PathVariable Long id, Model models) {
        return "pages/apoteker/form-update";
    }

    // Confirmation update apoteker
    @PostMapping(value = "/apoteker/update")
    public String postApotekerUpdateForm(
            @ModelAttribute ApotekerModel apoteker, Model models) {
        return "pages/apoteker/confirmation-update";
    }

    // Delete apoteker
    @PostMapping("/apoteker/delete")
    public String deletePengajarSubmit(@ModelAttribute ApotekerModel apoteker, Model models) {
        return "pages/apoteker/confirmation-delete";
    }
}
