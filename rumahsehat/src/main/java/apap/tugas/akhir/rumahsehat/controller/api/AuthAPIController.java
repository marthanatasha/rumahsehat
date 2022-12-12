package apap.tugas.akhir.rumahsehat.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apap.tugas.akhir.rumahsehat.model.DTO.LoginAPIDTO;
import apap.tugas.akhir.rumahsehat.service.AuthService;

@CrossOrigin()
@RestController
@RequestMapping("/api/v1")
public class AuthAPIController {

    @Autowired
    AuthService authService;
    
    @PostMapping("/login")
    public String login(@RequestBody LoginAPIDTO loginAPIDTO, BindingResult bindingResult) {
        return authService.getToken(loginAPIDTO);
    }
}
