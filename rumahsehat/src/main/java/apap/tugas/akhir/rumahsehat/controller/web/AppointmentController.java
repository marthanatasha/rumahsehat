package apap.tugas.akhir.rumahsehat.controller.web;

import apap.tugas.akhir.rumahsehat.model.AppointmentModel;
import apap.tugas.akhir.rumahsehat.model.JumlahModel;
import apap.tugas.akhir.rumahsehat.model.ObatModel;
import apap.tugas.akhir.rumahsehat.model.ResepModel;
import apap.tugas.akhir.rumahsehat.model.users.DokterModel;
import apap.tugas.akhir.rumahsehat.model.users.UserModel;
import apap.tugas.akhir.rumahsehat.service.AppointmentService;
import apap.tugas.akhir.rumahsehat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

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
    @GetMapping("/appointment/detail")
    public String getAppointmentById(@RequestParam(value = "kode") String kode, Model model, Principal principal) {
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

        if (apt != null && role.equals("DOKTER") && !apt.getIsDone()) { // TODO: harusnya "DOKTER, "ADMIN" buat testing aja
            if (apt.getResep() == null) {
                canUpdateStatus = true;
                canCreateResep = true;
                showResepWarning = true;
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

    // Update appointment
    @GetMapping("/appointment/update")
    public RedirectView updateAppointment(@RequestParam(value = "kode") String kode) {
        System.out.println("masuk controller"); // TODO: debug
        AppointmentModel apt = appointmentService.getAppointmentById(kode);
        AppointmentModel updated = appointmentService.updateAppointment(apt);

        String redirectUrl;
        if (updated != null) {
            System.out.println("berhasil update"); // TODO: debug
            redirectUrl = "/appointment/detail/?kode=" + updated.getKode();

            System.out.println("siapin harga buat create tagihan"); // TODO: debug
            Integer jumlahTagihan = apt.getDokter().getTarif();
            if (apt.getResep() != null) {
                ResepModel resep = apt.getResep();
                for (JumlahModel j : resep.getJumlah()) {
                    ObatModel obatJ = j.getObat();
                    Integer kuantitasJ = j.getKuantitas();
                    jumlahTagihan += (obatJ.getHarga() * kuantitasJ);
                }
            }
            // TODO: create TAGIHAN pake jumlahTagihan ini

        } else {
            System.out.println("gagal update"); // TODO: debug
            redirectUrl = "/appointment/detail/?kode=APT-null";
        }

        return new RedirectView(redirectUrl);
    }
}
