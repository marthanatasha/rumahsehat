package apap.tugas.akhir.rumahsehat.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import apap.tugas.akhir.rumahsehat.service.ApotekerService;

@Controller
public class ApotekerController {

    @Autowired
    ApotekerService apotekerService;

    @GetMapping("/apoteker")
    public String getApotekerList(Model model) {
        model.addAttribute("apotekers", apotekerService.getListApoteker());
        return "page/apoteker/list";
    }

    @GetMapping("/apoteker/{id}")
    public String updateCourseFormPage(@PathVariable Long id, Model model) {
        return "page/apoteker/detail";
    }
}
