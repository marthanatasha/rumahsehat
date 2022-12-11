package apap.tugas.akhir.rumahsehat.controller.web;

import apap.tugas.akhir.rumahsehat.model.*;
import apap.tugas.akhir.rumahsehat.model.users.DokterModel;
import apap.tugas.akhir.rumahsehat.model.users.UserModel;
import apap.tugas.akhir.rumahsehat.service.AppointmentService;
import apap.tugas.akhir.rumahsehat.service.TagihanService;
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

    @Autowired
    TagihanService tagihanService;

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
        boolean canUpdateStatus = false; // canUpdateStatus true saat appointment tidak punya resep
        boolean showResepWarning = false;

        Long kodeResep = null;

        if (role.equals("DOKTER") || role.equals("ADMIN")) {
            canAccess = true;
        }

        if (apt != null && role.equals("ADMIN") && !apt.getIsDone()) { // TODO: harusnya "DOKTER, "ADMIN" buat testing aja
            if (apt.getResep() == null) {
                canUpdateStatus = true;
                canCreateResep = true;
                showResepWarning = true;
            }
        }

        // TODO: debug
        System.out.println("role: " + role);
        System.out.println("can create resep: " + canCreateResep);
        System.out.println("can update status: " + canUpdateStatus);
        System.out.println("can update with warning: " + showResepWarning);


        model.addAttribute("apt", apt);
        model.addAttribute("role", role);
        model.addAttribute("canAccess", canAccess);
        model.addAttribute("canCreateResep", canCreateResep);
        model.addAttribute("canUpdateStatus", canUpdateStatus);
        model.addAttribute("showResepWarning", showResepWarning);
        model.addAttribute("kodeResep", kodeResep);

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

            System.out.println("otomatis create tagihan"); // TODO: debug
            Integer harga = apt.getDokter().getTarif(); // udh pasti gapunya resep
            TagihanModel newBill = new TagihanModel();
            TagihanModel createdBill = tagihanService.addTagihan(newBill, harga, apt);

            System.out.println("kode tagihan:  " + createdBill.getKode()); // TODO: debug
            System.out.println("jumlah tagihan: " + createdBill.getKode()); // TODO: debug

        } else {
            System.out.println("gagal update"); // TODO: debug
            redirectUrl = "/appointment/detail/?kode=APT-null";
        }

        return new RedirectView(redirectUrl);
    }
}
