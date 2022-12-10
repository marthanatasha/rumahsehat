package apap.tugas.akhir.rumahsehatapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apap.tugas.akhir.rumahsehatapi.models.users.UserModel;
import apap.tugas.akhir.rumahsehatapi.repository.UserDb;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserDb userDb;

    @GetMapping("/info")
    public UserModel getUserDetails() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDb.findByEmail(email).get();
    }

}