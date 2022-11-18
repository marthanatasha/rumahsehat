package apap.tugas.akhir.rumahsehat.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import apap.tugas.akhir.rumahsehat.service.TagihanService;

@Controller
public class TagihanController {

    @Autowired
    TagihanService tagihanService;

    @GetMapping("/tagihan")
    public String getTagihanList(Model model) {
        model.addAttribute("tagihans", tagihanService.getListTagihan());
        return "pages/tagihan/list";
    }

    @GetMapping("/tagihan/{id}")
    public String updateCourseFormPage(@PathVariable Long id, Model model) {
        return "pages/tagihan/detail";
    }
}
