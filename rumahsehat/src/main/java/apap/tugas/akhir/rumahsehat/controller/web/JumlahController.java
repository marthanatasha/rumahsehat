package apap.tugas.akhir.rumahsehat.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import apap.tugas.akhir.rumahsehat.service.JumlahService;

@Controller
public class JumlahController {

    @Autowired
    JumlahService jumlahService;

    @GetMapping("/jumlah")
    public String getJumlahList(Model model) {
        model.addAttribute("jumlahs", jumlahService.getListJumlah());
        return "pages/jumlah/list";
    }

    @GetMapping("/jumlah/{id}")
    public String updateCourseFormPage(@PathVariable Long id, Model model) {
        return "pages/jumlah/detail";
    }
}
