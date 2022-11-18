package apap.tugas.akhir.rumahsehat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import apap.tugas.akhir.rumahsehat.service.ObatService;

@Controller
public class BaseController {
    @Autowired
    ObatService obatService;

    @GetMapping("/")
    private String Home() {
        System.out.println(obatService.getListObat());
        return "home";
    }
}