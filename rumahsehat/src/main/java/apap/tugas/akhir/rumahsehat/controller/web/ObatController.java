package apap.tugas.akhir.rumahsehat.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import apap.tugas.akhir.rumahsehat.service.ObatService;

@Controller
public class ObatController {

    @Autowired
    ObatService obatService;

    @GetMapping("/obat")
    public String getObatList(Model model) {
        model.addAttribute("obats", obatService.getListObat());
        return "page/obat/list";
    }

    @GetMapping("/obat/{id}")
    public String updateCourseFormPage(@PathVariable Long id, Model model) {
        return "page/obat/detail";
    }
}
