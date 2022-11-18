package apap.tugas.akhir.rumahsehat.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.tugas.akhir.rumahsehat.model.AppointmentModel;
import apap.tugas.akhir.rumahsehat.service.AppointmentService;

@Controller
public class AppointmentController {

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
    @GetMapping("/appointment/add")
    public String getAppointmentAddForm(Model model) {
        return "pages/appointment/form-add";
    }

    // Confirmation create appointment
    @PostMapping(value = "/appointment/add")
    public String postAppointmentAddForm(
            @ModelAttribute AppointmentModel appointment, Model model) {
        return "pages/appointment/confirmation-add";
    }

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
