package apap.tugas.akhir.rumahsehat.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import apap.tugas.akhir.rumahsehat.service.AppointmentService;

@Controller
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @GetMapping("/appointment")
    public String getAppointmentList(Model model) {
        model.addAttribute("appointments", appointmentService.getListAppointment());
        return "page/appointment/list";
    }

    @GetMapping("/appointment/{id}")
    public String updateCourseFormPage(@PathVariable Long id, Model model) {
        return "page/appointment/detail";
    }
}
