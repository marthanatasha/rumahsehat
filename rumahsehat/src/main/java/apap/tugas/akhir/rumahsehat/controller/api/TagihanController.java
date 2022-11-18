package apap.tugas.akhir.rumahsehat.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.tugas.akhir.rumahsehat.model.TagihanModel;
import apap.tugas.akhir.rumahsehat.service.TagihanService;

@Controller
public class TagihanController {

    @Autowired
    TagihanService tagihanService;

    // List tagihan
    @GetMapping("/tagihan")
    public String getTagihanList(Model model) {
        model.addAttribute("tagihans", tagihanService.getListTagihan());
        return "pages/tagihan/list";
    }

    // Detail tagihan
    @GetMapping("/tagihan/{id}")
    public String getTagihanById(@PathVariable Long id, Model model) {
        return "pages/tagihan/detail";
    }

    // Form create tagihan
    @GetMapping("/tagihan/add")
    public String getTagihanAddForm(Model model) {
        return "pages/tagihan/form-add";
    }

    // Confirmation create tagihan
    @PostMapping(value = "/tagihan/add")
    public String postTagihanAddForm(
            @ModelAttribute TagihanModel tagihan, Model model) {
        return "pages/tagihan/confirmation-add";
    }

    // Form update tagihan
    @GetMapping("/tagihan/update/{id}")
    public String getTagihanAddUpdate(@PathVariable Long id, Model model) {
        return "pages/tagihan/form-update";
    }

    // Confirmation update tagihan
    @PostMapping(value = "/tagihan/update")
    public String postTagihanUpdateForm(
            @ModelAttribute TagihanModel tagihan, Model model) {
        return "pages/tagihan/confirmation-update";
    }

    // Delete tagihan
    @PostMapping("/tagihan/delete")
    public String deletePengajarSubmit(@ModelAttribute TagihanModel tagihan, Model model) {
        return "pages/tagihan/confirmation-delete";
    }
}
