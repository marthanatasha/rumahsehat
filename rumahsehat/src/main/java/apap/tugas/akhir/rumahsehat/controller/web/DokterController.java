package apap.tugas.akhir.rumahsehat.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import apap.tugas.akhir.rumahsehat.service.DokterService;

@Controller
public class DokterController {

    @Autowired
    DokterService dokterService;

    @GetMapping("/dokter")
    public String getDokterList(Model model) {
        model.addAttribute("dokters", dokterService.getListDokter());
        return "pages/dokter/list";
    }

    @GetMapping("/dokter/{id}")
    public String updateCourseFormPage(@PathVariable Long id, Model model) {
        return "pages/dokter/detail";
    }
}
