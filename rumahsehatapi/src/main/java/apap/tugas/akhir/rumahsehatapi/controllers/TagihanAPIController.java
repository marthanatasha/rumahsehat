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

import apap.tugas.akhir.rumahsehatapi.models.TagihanModel;
import apap.tugas.akhir.rumahsehatapi.service.TagihanService;

@RestController
@RequestMapping("/api/v1")
public class TagihanAPIController {

    @Autowired
    TagihanService tagihanService;

    // List tagihan
    @GetMapping("/tagihan")
    public String getTagihanList(Model models) {
        models.addAttribute("tagihans", tagihanService.getListTagihan());
        return "pages/tagihan/list";
    }

    // Detail tagihan
    @GetMapping("/tagihan/{id}")
    public String getTagihanById(@PathVariable Long id, Model models) {
        return "pages/tagihan/detail";
    }

    // Form create tagihan
    @GetMapping("/tagihan/add")
    public String getTagihanAddForm(Model models) {
        return "pages/tagihan/form-add";
    }

    // Confirmation create tagihan
    @PostMapping(value = "/tagihan/add")
    public String postTagihanAddForm(
            @ModelAttribute TagihanModel tagihan, Model models) {
        return "pages/tagihan/confirmation-add";
    }

    // Form update tagihan
    @GetMapping("/tagihan/update/{id}")
    public String getTagihanAddUpdate(@PathVariable Long id, Model models) {
        return "pages/tagihan/form-update";
    }

    // Confirmation update tagihan
    @PostMapping(value = "/tagihan/update")
    public String postTagihanUpdateForm(
            @ModelAttribute TagihanModel tagihan, Model models) {
        return "pages/tagihan/confirmation-update";
    }

    // Delete tagihan
    @PostMapping("/tagihan/delete")
    public String deletePengajarSubmit(@ModelAttribute TagihanModel tagihan, Model models) {
        return "pages/tagihan/confirmation-delete";
    }
}
