package apap.tugas.akhir.rumahsehat.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import apap.tugas.akhir.rumahsehat.service.UserService;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public String getUserList(Model model) {
        model.addAttribute("users", userService.getListUser());
        return "pages/user/list";
    }

    @GetMapping("/user/{id}")
    public String updateCourseFormPage(@PathVariable Long id, Model model) {
        return "pages/user/detail";
    }
}
