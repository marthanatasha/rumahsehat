package apap.tugas.akhir.rumahsehat.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.tugas.akhir.rumahsehat.model.ResepModel;
import apap.tugas.akhir.rumahsehat.service.ResepService;

@Controller
public class ResepController {

    @Autowired
    ResepService resepService;

    // List resep
    @GetMapping("/resep")
    public String getResepList(Model model) {
        model.addAttribute("reseps", resepService.getListResep());
        return "pages/resep/list";
    }

    // Detail resep
    @GetMapping("/resep/{id}")
    public String getResepById(@PathVariable Long id, Model model) {
        return "pages/resep/detail";
    }

    // Form create resep
    @GetMapping("/resep/add")
    public String getResepAddForm(Model model) {
        return "pages/resep/form-add";
    }

    // Confirmation create resep
    @PostMapping(value = "/resep/add")
    public String postResepAddForm(
            @ModelAttribute ResepModel resep, Model model) {
        return "pages/resep/confirmation-add";
    }

    // Form update resep
    @GetMapping("/resep/update/{id}")
    public String getResepAddUpdate(@PathVariable Long id, Model model) {
        return "pages/resep/form-update";
    }

    // Confirmation update resep
    @PostMapping(value = "/resep/update")
    public String postResepUpdateForm(
            @ModelAttribute ResepModel resep, Model model) {
        return "pages/resep/confirmation-update";
    }

    // Delete resep
    @PostMapping("/resep/delete")
    public String deletePengajarSubmit(@ModelAttribute ResepModel resep, Model model) {
        return "pages/resep/confirmation-delete";
    }
}
