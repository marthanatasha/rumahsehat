package apap.tugas.akhir.rumahsehat.controller;

import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import apap.tugas.akhir.rumahsehat.model.AppointmentModel;
import apap.tugas.akhir.rumahsehat.model.users.*;
import apap.tugas.akhir.rumahsehat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import apap.tugas.akhir.rumahsehat.repository.AdminDb;
import apap.tugas.akhir.rumahsehat.security.xml.Attributes;
import apap.tugas.akhir.rumahsehat.security.xml.ServiceResponse;
import apap.tugas.akhir.rumahsehat.setting.Setting;

@Controller
public class BaseController {

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @Autowired
    ApotekerService apotekerService;

    @Autowired
    PasienService pasienService;

    @Autowired
    DokterService dokterService;

    private WebClient webClient = WebClient.builder().build();

    @GetMapping("/")
    private String Home() {
        return "dashboard/index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    // @GetMapping("/api/initial")
    // private String Initial() {
    //     if (adminService.getListAdmin().size() < 2) {
    //         AdminModel admin = new AdminModel();
    //         admin.setEmail("admin3@rumahsehat.com");
    //         admin.setNama("admin utama");
    //         admin.setPassword("admin");
    //         admin.setRole(UserType.ADMIN);
    //         admin.setUsername("admin2");
    //         admin.setIsSso(false);
    //         admin.setToken("0");
    //         adminService.addAdmin(admin);
    //     }

    //     return "error/404";
    // }

    // @GetMapping("/api/initial")
    // private String Initial() {
    // if (apotekerService.getListApoteker().size() == 0) {
    // ApotekerModel apoteker = new ApotekerModel();
    // apoteker.setEmail("apoteker@rumahsehat.com");
    // apoteker.setNama("apoteker satu");
    // apoteker.setPassword("apoteker");
    // apoteker.setRole(UserType.APOTEKER);
    // apoteker.setUsername("apoteker");
    // apoteker.setIsSso(false);
    // apotekerService.addApoteker(apoteker);
    // }
    // if (adminService.getListAdmin().size() == 0) {
    // AdminModel admin = new AdminModel();
    // admin.setEmail("admin@rumahsehat.com");
    // admin.setNama("admin utama");
    // admin.setPassword("admin");
    // admin.setRole(UserType.ADMIN);
    // admin.setUsername("admin");
    // admin.setIsSso(false);
    // adminService.addAdmin(admin);
    // }
    // if (dokterService.getListDokter().size() == 0) {
    // DokterModel dokter = new DokterModel();
    // dokter.setEmail("dokter2@rumahsehat.com");
    // dokter.setNama("dokter dua");
    // dokter.setPassword("dokter2");
    // dokter.setRole(UserType.DOKTER);
    // dokter.setUsername("dokter2");
    // dokter.setIsSso(false);
    // Integer tarif = 200000;
    // dokter.setTarif(tarif);
    // dokterService.addDokter(dokter);
    // }
    // if (pasienService.getListPasien().size() == 0) {
    // PasienModel pasien = new PasienModel();
    // pasien.setEmail("pasien2@rumahsehat.com");
    // pasien.setNama("pasien dua");
    // pasien.setPassword("pasien2");
    // pasien.setRole(UserType.PASIEN);
    // pasien.setUsername("pasien2");
    // pasien.setIsSso(false);
    // pasien.setSaldo(70000);
    // pasien.setUmur(20);
    // pasien.setListAppointment(new ArrayList<>());
    // pasienService.addPasien(pasien);
    // }

    // return "pages/home";
    // return ("redirect:/");
    // }

    @GetMapping(value = "/login-sso")
    public ModelAndView loginSSO() {
        return new ModelAndView("redirect:" + Setting.SERVER_LOGIN + Setting.CLIENT_LOGIN);
    }

    @GetMapping(value = "/logout-sso")
    public ModelAndView logoutSSO(Principal principal) {
        UserModel user = userService.getUserByUsername(principal.getName());
        if (user.getIsSso() == false) {
            return new ModelAndView("redirect:/logout");
        }
        return new ModelAndView("redirect:" + Setting.SERVER_LOGOUT + Setting.CLIENT_LOGOUT);
    }

    @GetMapping("/validate-ticket")
    public ModelAndView adminLoginSSO(
            @RequestParam(value = "ticket", required = false) String ticket,
            HttpServletRequest request) {
        ServiceResponse serviceResponse = this.webClient.get().uri(
                String.format(
                        Setting.SERVER_VALIDATE_TICKET,
                        ticket,
                        Setting.CLIENT_LOGIN))
                .retrieve().bodyToMono(ServiceResponse.class).block();

        Attributes attributes = serviceResponse.getAuthenticationSuccess().getAttributes();
        String username = serviceResponse.getAuthenticationSuccess().getUser();

        AdminModel admin = adminService.getAdminByUsername(username);

        if (admin == null) {
            admin = new AdminModel();
            admin.setEmail(username + "@ui.ac.id");
            admin.setNama(attributes.getNama());
            admin.setPassword("rumahsehat");
            admin.setUsername(username);
            admin.setIsSso(true);
            admin.setRole(UserType.ADMIN);
            adminService.addAdmin(admin);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, "rumahsehat");

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);

        return new ModelAndView("redirect:/");
    }
}