package apap.tugas.akhir.rumahsehat.controller.web;

import apap.tugas.akhir.rumahsehat.model.users.DokterModel;
import apap.tugas.akhir.rumahsehat.model.users.UserModel;
import apap.tugas.akhir.rumahsehat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import apap.tugas.akhir.rumahsehat.model.AppointmentModel;
import apap.tugas.akhir.rumahsehat.service.AppointmentService;

import java.security.Principal;
import java.util.List;

@Controller
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    UserService userService;

    // List appointment
    @GetMapping("/appointment")
    public String getAppointmentList(Model model, Principal principal) {
        UserModel user = userService.getUserByUsername(principal.getName());
        String role = user.getRole().toString();
        List<AppointmentModel> aptList = null;

        if (role.equals("ADMIN")) {
            aptList = appointmentService.getListAppointment();
        } else if (role.equals("DOKTER")) {
            DokterModel dokter = (DokterModel) user;
            aptList = dokter.getListAppointment();
            // TODO: blm bisa testing
        }

        model.addAttribute("appointments", aptList);
        model.addAttribute("role", role);
        return "dashboard/appointment/list";
    }

    // Detail appointment
    @GetMapping("/appointment/detail/{kode}")
    public String getAppointmentById(@PathVariable("kode") String kode, Model model, Principal principal) {
        AppointmentModel apt = appointmentService.getAppointmentById(kode);
        UserModel user = userService.getUserByUsername(principal.getName());
        String role = user.getRole().toString();

        boolean canAccess = false;
        boolean canCreateResep = false;
        boolean canUpdateStatus = false;
        boolean showResepWarning = false;

        if (role.equals("DOKTER") || role.equals("ADMIN")) {
            canAccess = true;
        }

        if (role.equals("DOKTER") && !apt.getIsDone()) { // TODO: harusnya "DOKTER", "ADMIN" buat testing aja
            if (apt.getResep() == null) {
                canUpdateStatus = true;
                showResepWarning = true;
                canCreateResep = true;
            } else {
                if (apt.getResep().getIsDone()) {
                    canUpdateStatus = true;
                }
            }
        }

        model.addAttribute("apt", apt);
        model.addAttribute("role", role);
        model.addAttribute("canAccess", canAccess);
        model.addAttribute("canCreateResep", canCreateResep);
        model.addAttribute("canUpdateStatus", canUpdateStatus);
        model.addAttribute("showResepWarning", showResepWarning);

        return "dashboard/appointment/detail";
    }

//    // Form create appointment
//    @GetMapping("/appointment/add")
//    public String getAppointmentAddForm(Model model) {
//        return "dashboard/appointment/form-add";
//    }

//    // Confirmation create appointment
//    @PostMapping(value = "/appointment/add")
//    public String postAppointmentAddForm(
//            @ModelAttribute AppointmentModel appointment, Model model) {
//        return "dashboard/appointment/confirmation-add";
//    }

//    // Form update appointment
//    @GetMapping("/appointment/update/{id}")
//    public String getAppointmentAddUpdate(@PathVariable Long id, Model model) {
//        return "dashboard/appointment/form-update";
//    }
//
//    // Confirmation update appointment
//    @PostMapping(value = "/appointment/update")
//    public String postAppointmentUpdateForm(
//            @ModelAttribute AppointmentModel appointment, Model model) {
//        return "dashboard/appointment/confirmation-update";
//    }
//
//    // Delete appointment
//    @PostMapping("/appointment/delete")
//    public String deletePengajarSubmit(@ModelAttribute AppointmentModel appointment, Model model) {
//        return "dashboard/appointment/confirmation-delete";
//    }
}
