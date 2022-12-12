package apap.tugas.akhir.rumahsehat.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apap.tugas.akhir.rumahsehat.controller.web.AppointmentController;
import apap.tugas.akhir.rumahsehat.model.DTO.LoginAPIDTO;
import apap.tugas.akhir.rumahsehat.model.users.UserModel;
import apap.tugas.akhir.rumahsehat.service.AuthService;

@CrossOrigin()
@RestController
@RequestMapping("/api/v1")
public class AuthAPIController {

    @Autowired
    AuthService authService;
    
    Logger logger = LoggerFactory.getLogger(AuthAPIController.class);


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginAPIDTO loginAPIDTO, BindingResult bindingResult) {
        String token = authService.getToken(loginAPIDTO);

        if (token == null) {
            logger.info("API POST: Token Invalid");
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        logger.info("API POST: Authentication Valid");
        return new ResponseEntity<>(authService.getToken(loginAPIDTO), HttpStatus.OK);
    }

    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String bearerToken, Model model) {
        if (!authService.tokenCheck(bearerToken)) {
            logger.info("API POST: Token Invalid");
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        logger.info("API GET: Info user");
        return new ResponseEntity<>(authService.getUserInfo(bearerToken), HttpStatus.OK);
    }
}
