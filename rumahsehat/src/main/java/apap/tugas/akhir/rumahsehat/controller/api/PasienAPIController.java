package apap.tugas.akhir.rumahsehat.controller.api;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import apap.tugas.akhir.rumahsehat.model.DTO.PasienDTO;
import apap.tugas.akhir.rumahsehat.model.DTO.SaldoDTO;
import apap.tugas.akhir.rumahsehat.model.users.PasienModel;
import apap.tugas.akhir.rumahsehat.service.PasienService;
import apap.tugas.akhir.rumahsehat.service.UserService;

@CrossOrigin()
@RestController
@RequestMapping("/api/v1")
public class PasienAPIController {

    @Autowired
    PasienService pasienService;

    @Autowired
    UserService userService;

    // List pasien
    @GetMapping("/pasien")
    public String getPasienList(Model model) {
        model.addAttribute("pasiens", pasienService.getListPasien());
        return "pages/pasien/list";
    }

    // Detail pasien
    // @GetMapping("/pasien/{id}")
    // public String getPasienById(@PathVariable Long id, Model model) {
    //     return "pages/pasien/detail";
    // }

    // Form create pasien
    @GetMapping("/pasien/add")
    public String getPasienAddForm(Model model) {
        return "pages/pasien/form-add";
    }

    // Confirmation create pasien
    @PostMapping(value = "/pasien/add")
    public PasienModel createPasien(@RequestBody PasienDTO pasien, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        } else {
            return pasienService.addPasien(pasien);
        }
    }

    // Form update pasien
    @GetMapping("/pasien/update/{id}")
    public String getPasienAddUpdate(@PathVariable Long id, Model model) {
        return "pages/pasien/form-update";
    }

    // Confirmation update pasien
    // @PutMapping(value = "/pasien/{pasienId}")
    // public PasienModel updateSaldo(@PathVariable("pasienId") String pasienId, @RequestBody PasienModel pasien) {
    //     try {
    //         return pasienService.updatePasien(pasienId, pasien);
    //     } catch (NoSuchElementException e) {
    //         throw new ResponseStatusException(
    //             HttpStatus.NOT_FOUND, "Pasien dengan id " + pasienId + " not found"
    //         );
    //     }
    // }

    // Delete pasien
    @PostMapping("/pasien/delete")
    public String deletePengajarSubmit(@ModelAttribute PasienModel pasien, Model model) {
        return "pages/pasien/confirmation-delete";
    }

    @GetMapping("/pasien/{pasienId}")
    public PasienModel getUserProfile(@PathVariable("pasienId") String pasienId) {
        try {
            PasienModel pasien = (PasienModel) userService.getRestUserById(pasienId);
            return pasien;
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pasien ID " + pasienId + " not found.");
        }
    }

    @PostMapping("/pasien/addSaldo")
    public PasienModel updateSaldo(@RequestBody SaldoDTO saldoPasien, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        } else {
            SaldoDTO saldo = new SaldoDTO(saldoPasien.getUsername(), saldoPasien.getSaldo());
            return pasienService.updatePasien(saldo.getUsername(), saldo.getSaldo());
        }
    }
}
