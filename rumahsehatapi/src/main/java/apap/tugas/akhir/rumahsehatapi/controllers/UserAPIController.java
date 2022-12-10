package apap.tugas.akhir.rumahsehatapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apap.tugas.akhir.rumahsehatapi.models.users.UserModel;
import apap.tugas.akhir.rumahsehatapi.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserAPIController {

    @Autowired
    UserService userService;

    // List user
    @GetMapping("/user")
    public String getUserList(Model models) {
        models.addAttribute("users", userService.getListUser());
        return "pages/user/list";
    }

    // Detail user
    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable Long id, Model models) {
        return "pages/user/detail";
    }

    // Form create user
    @GetMapping("/user/add")
    public String getUserAddForm(Model models) {
        return "pages/user/form-add";
    }

    // Confirmation create user
    @PostMapping(value = "/user/add")
    public String postUserAddForm(
            @ModelAttribute UserModel user, Model models) {
        return "pages/user/confirmation-add";
    }

    // Form update user
    @GetMapping("/user/update/{id}")
    public String getUserAddUpdate(@PathVariable Long id, Model models) {
        return "pages/user/form-update";
    }

    // Confirmation update user
    @PostMapping(value = "/user/update")
    public String postUserUpdateForm(
            @ModelAttribute UserModel user, Model models) {
        return "pages/user/confirmation-update";
    }

    // Delete user
    @PostMapping("/user/delete")
    public String deletePengajarSubmit(@ModelAttribute UserModel user, Model models) {
        return "pages/user/confirmation-delete";
    }
}
