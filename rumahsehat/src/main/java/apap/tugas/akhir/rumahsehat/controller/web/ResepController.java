package apap.tugas.akhir.rumahsehat.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import apap.tugas.akhir.rumahsehat.service.ResepService;

@Controller
public class ResepController {

    @Autowired
    ResepService resepService;

    @GetMapping("/resep")
    public String getResepList(Model model) {
        model.addAttribute("reseps", resepService.getListResep());
        return "page/resep/list";
    }

    @GetMapping("/resep/{id}")
    public String updateCourseFormPage(@PathVariable Long id, Model model) {
        return "page/resep/detail";
    }
}
