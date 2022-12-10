package apap.tugas.akhir.rumahsehatapi.controllers;

import apap.tugas.akhir.rumahsehatapi.models.DTO.AppointmentDTO;
import apap.tugas.akhir.rumahsehatapi.models.users.PasienModel;
import apap.tugas.akhir.rumahsehatapi.models.users.UserModel;
import apap.tugas.akhir.rumahsehatapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import apap.tugas.akhir.rumahsehatapi.models.AppointmentModel;
import apap.tugas.akhir.rumahsehatapi.service.AppointmentService;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class AppointmentAPIController {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    UserService userService;

    // List appointment
    @GetMapping("/appointment/{pasienId}")
    public List<AppointmentModel> getAppointmentList(@PathVariable("pasienId") String pasienId) {
        try {
            System.out.println("masuk controllers"); // TODO: debug
            PasienModel pasien = (PasienModel) userService.getRestUserById(pasienId);
            System.out.println("udh get pasien"); // TODO: debug
            return pasien.getListAppointment();
        } catch (NoSuchElementException e) {
            System.out.println("masuk not found"); // TODO: debug
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pasien ID " + pasienId + " not found.");
        }
    }

    // Detail appointment
    @GetMapping("/appointment/detail/{kode}")
    public AppointmentModel getAppointmentById(@PathVariable("kode") String kode) {
        try {
            return appointmentService.getRestAppointmentById(kode);
        } catch (NoSuchElementException e) {
            System.out.println("masuk not found"); // TODO: debug
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment Kode " + kode + " not found.");
        }
    }

    // Form create appointment
    @PostMapping("/appointment/add")
    public AppointmentModel getAppointmentAddForm(@RequestBody AppointmentDTO appointmentDTO,
            BindingResult bindingResult) {
        System.out.println("masuk controllers"); // TODO: debug
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        } else {
            AppointmentModel result = appointmentService.addAppointment(appointmentDTO);
            if (result == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Waktu appointment tidak tersedia.");
            } else {
                return result;
            }
        }
    }

    // Form update appointment
    @GetMapping("/appointment/update/{id}")
    public String getAppointmentAddUpdate(@PathVariable Long id, Model models) {
        return "pages/appointment/form-update";
    }

    // Confirmation update appointment
    @PostMapping(value = "/appointment/update")
    public String postAppointmentUpdateForm(
            @ModelAttribute AppointmentModel appointment, Model models) {
        return "pages/appointment/confirmation-update";
    }
}
