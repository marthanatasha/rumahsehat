package apap.tugas.akhir.rumahsehat.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.tugas.akhir.rumahsehat.model.ObatModel;
import apap.tugas.akhir.rumahsehat.service.ObatService;

@Controller
public class ObatController {

    @Autowired
    ObatService obatService;

    // List obat
    @GetMapping("/obat")
    public String getObatList(Model model) {
        model.addAttribute("daftarObat", obatService.getListObat());
        return "dashboard/obat/list";
    }

    // Detail obat
    @GetMapping("/obat/{id}")
    public String getObatById(@PathVariable Long id, Model model) {
        return "dashboard/obat/detail";
    }

    // Form create obat
    @GetMapping("/obat/add")
    public String getObatAddForm(Model model) {
        return "dashboard/obat/form-add";
    }

    // Confirmation create obat
    @PostMapping(value = "/obat/add")
    public String postObatAddForm(
            @ModelAttribute ObatModel obat, Model model) {
        return "dashboard/obat/confirmation-add";
    }

    // Form update obat
    @GetMapping("/obat/update/{idObat}")
    public String getObatAddUpdate(@PathVariable String idObat, Model model) {
        ObatModel obat = obatService.getObatById(idObat);
        model.addAttribute("obat", obat);
        return "dashboard/obat/form-update";
    }

    // Confirmation update obat
    @PostMapping(value = "/obat/update")
    public String postObatUpdateForm(
            @ModelAttribute ObatModel obat, Model model) {
        ObatModel updatedObat = obatService.updateObat(obat);
        model.addAttribute("updatedObat", updatedObat);
        return "dashboard/obat/confirmation-update";
    }

    // Delete obat
    @PostMapping("/obat/delete")
    public String deletePengajarSubmit(@ModelAttribute ObatModel obat, Model model) {
        return "dashboard/obat/confirmation-delete";
    }
}
