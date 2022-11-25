package apap.tugas.akhir.rumahsehat.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.tugas.akhir.rumahsehat.model.JumlahModel;
import apap.tugas.akhir.rumahsehat.service.JumlahService;

@Controller
public class JumlahController {

    @Autowired
    JumlahService jumlahService;

    // List jumlah
    @GetMapping("/jumlah")
    public String getJumlahList(Model model) {
        model.addAttribute("jumlahs", jumlahService.getListJumlah());
        return "dashboard/jumlah/list";
    }

    // Detail jumlah
    @GetMapping("/jumlah/{id}")
    public String getJumlahById(@PathVariable Long id, Model model) {
        return "dashboard/jumlah/detail";
    }

    // Form create jumlah
    @GetMapping("/jumlah/add")
    public String getJumlahAddForm(Model model) {
        return "dashboard/jumlah/form-add";
    }

    // Confirmation create jumlah
    @PostMapping(value = "/jumlah/add")
    public String postJumlahAddForm(
            @ModelAttribute JumlahModel jumlah, Model model) {
        return "dashboard/jumlah/confirmation-add";
    }

    // Form update jumlah
    @GetMapping("/jumlah/update/{id}")
    public String getJumlahAddUpdate(@PathVariable Long id, Model model) {
        return "dashboard/jumlah/form-update";
    }

    // Confirmation update jumlah
    @PostMapping(value = "/jumlah/update")
    public String postJumlahUpdateForm(
            @ModelAttribute JumlahModel jumlah, Model model) {
        return "dashboard/jumlah/confirmation-update";
    }

    // Delete jumlah
    @PostMapping("/jumlah/delete")
    public String deletePengajarSubmit(@ModelAttribute JumlahModel jumlah, Model model) {
        return "dashboard/jumlah/confirmation-delete";
    }
}
