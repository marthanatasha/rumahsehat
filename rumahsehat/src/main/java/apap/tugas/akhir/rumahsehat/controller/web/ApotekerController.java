package apap.tugas.akhir.rumahsehat.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.tugas.akhir.rumahsehat.model.users.ApotekerModel;
import apap.tugas.akhir.rumahsehat.model.users.UserType;
import apap.tugas.akhir.rumahsehat.service.ApotekerService;
import apap.tugas.akhir.rumahsehat.service.DokterService;

@Controller
public class ApotekerController {

    @Autowired
    ApotekerService apotekerService;

    @Autowired
    DokterService dokterService;

    // List apoteker
    @GetMapping("/apoteker")
    public String getApotekerList(Model model) {
        model.addAttribute("dokters", dokterService.getListDokter());
        model.addAttribute("apotekers", apotekerService.getListApoteker());
        return "pages/apoteker/list";
    }

    // Detail apoteker
    @GetMapping("/apoteker/{id}")
    public String getApotekerById(@PathVariable Long id, Model model) {
        return "pages/apoteker/detail";
    }

    // Form create apoteker
    @GetMapping("/apoteker/add")
    public String getApotekerAddForm(Model model) {
        return "pages/apoteker/form-add";
    }

    // Confirmation create apoteker
    @PostMapping(value = "/apoteker/add")
    public String postApotekerAddForm(
            @ModelAttribute ApotekerModel apoteker, Model model) {
        apoteker.setRole(UserType.APOTEKER);
        apotekerService.addApoteker(apoteker);
        return "pages/apoteker/confirmation-add";
    }

    // Form update apoteker
    @GetMapping("/apoteker/update/{id}")
    public String getApotekerAddUpdate(@PathVariable Long id, Model model) {
        return "pages/apoteker/form-update";
    }

    // Confirmation update apoteker
    @PostMapping(value = "/apoteker/update")
    public String postApotekerUpdateForm(
            @ModelAttribute ApotekerModel apoteker, Model model) {
        return "pages/apoteker/confirmation-update";
    }

    // Delete apoteker
    @PostMapping("/apoteker/delete")
    public String deletePengajarSubmit(@ModelAttribute ApotekerModel apoteker, Model model) {
        return "pages/apoteker/confirmation-delete";
    }
}
