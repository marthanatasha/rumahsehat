package apap.tugas.akhir.rumahsehat.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import apap.tugas.akhir.rumahsehat.model.AppointmentModel;
import apap.tugas.akhir.rumahsehat.service.AppointmentService;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1")
public class AppointmentAPIController {

    @Autowired
    AppointmentService appointmentService;

    // List appointment
    @GetMapping("/appointment")
    public String getAppointmentList(Model model) {
        model.addAttribute("appointments", appointmentService.getListAppointment());
        return "pages/appointment/list";
    }

    // Detail appointment
    @GetMapping("/appointment/{id}")
    public String getAppointmentById(@PathVariable Long id, Model model) {
        return "pages/appointment/detail";
    }

    // Form create appointment
    @PostMapping("/appointment/add")
    public AppointmentModel getAppointmentAddForm(@RequestBody AppointmentModel appointment, BindingResult bindingResult) {
        System.out.println("masuk controller"); // TODO: debug
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        } else {
            return appointmentService.addAppointment(appointment);
        }
    }

//    // Confirmation create appointment
//    @PostMapping(value = "/appointment/add")
//    public String postAppointmentAddForm(
//            @ModelAttribute AppointmentModel appointment, Model model) {
//        return "pages/appointment/confirmation-add";
//    }

    // Form update appointment
    @GetMapping("/appointment/update/{id}")
    public String getAppointmentAddUpdate(@PathVariable Long id, Model model) {
        return "pages/appointment/form-update";
    }

    // Confirmation update appointment
    @PostMapping(value = "/appointment/update")
    public String postAppointmentUpdateForm(
            @ModelAttribute AppointmentModel appointment, Model model) {
        return "pages/appointment/confirmation-update";
    }

    // Delete appointment
    @PostMapping("/appointment/delete")
    public String deletePengajarSubmit(@ModelAttribute AppointmentModel appointment, Model model) {
        return "pages/appointment/confirmation-delete";
    }
}
