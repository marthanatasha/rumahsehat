package apap.tugas.akhir.rumahsehat.controller.api;

import apap.tugas.akhir.rumahsehat.model.AppointmentModel;
import apap.tugas.akhir.rumahsehat.model.DTO.AppointmentDTO;
import apap.tugas.akhir.rumahsehat.model.users.PasienModel;
import apap.tugas.akhir.rumahsehat.service.AppointmentService;
import apap.tugas.akhir.rumahsehat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin()
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
            PasienModel pasien = (PasienModel) userService.getRestUserById(pasienId);
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
    public AppointmentModel getAppointmentAddForm(@RequestBody AppointmentDTO appointmentDTO, BindingResult bindingResult) {
        System.out.println("masuk controller"); // TODO: debug
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

//    // Form update appointment
//    @GetMapping("/appointment/update/{id}")
//    public String getAppointmentAddUpdate(@PathVariable Long id, Model model) {
//        return "pages/appointment/form-update";
//    }

//    // Confirmation update appointment
//    @PostMapping(value = "/appointment/update")
//    public String postAppointmentUpdateForm(
//            @ModelAttribute AppointmentModel appointment, Model model) {
//        return "pages/appointment/confirmation-update";
//    }
}
