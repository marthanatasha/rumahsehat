package apap.tugas.akhir.rumahsehat.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import apap.tugas.akhir.rumahsehat.service.PasienService;

@Controller
public class PasienController {

    @Autowired
    PasienService pasienService;

    @GetMapping("/pasien")
    public String getPasienList(Model model) {
        model.addAttribute("pasiens", pasienService.getListPasien());
        return "page/pasien/list";
    }

    @GetMapping("/pasien/{id}")
    public String updateCourseFormPage(@PathVariable Long id, Model model) {
        return "page/pasien/detail";
    }
}
