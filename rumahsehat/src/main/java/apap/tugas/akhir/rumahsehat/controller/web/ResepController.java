package apap.tugas.akhir.rumahsehat.controller.web;

import apap.tugas.akhir.rumahsehat.model.*;
import apap.tugas.akhir.rumahsehat.model.users.ApotekerModel;
import apap.tugas.akhir.rumahsehat.model.users.DokterModel;
import apap.tugas.akhir.rumahsehat.model.users.PasienModel;
import apap.tugas.akhir.rumahsehat.model.users.UserModel;
import apap.tugas.akhir.rumahsehat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ResepController {

    @Autowired
    ResepService resepService;

    @Autowired
    ObatService obatService;

    @Autowired
    JumlahService jumlahService;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    TagihanService tagihanService;

    @Autowired
    ApotekerService apotekerService;

    @Autowired
    UserService userService;

    @Autowired
    DokterService dokterService;

    // List resep
    @GetMapping("/resep")
    public String getResepList(Model model, Principal principal) {
        if (userService.isApoteker(principal) || userService.isAdmin(principal)){
            List <ResepModel> listResep = resepService.getListResep();
            model.addAttribute("listResep", listResep);
            return "dashboard/resep/list";
        }
        else {
            System.out.println("salah role");
            return "error/404";
        }
    }

    // Detail resep
    @GetMapping("/resep/{id}")
    public String getResepById(@PathVariable Long id, Model model, Principal principal, Authentication authentication) {
        if (userService.isPasien(principal)){
            return "error/404";
        }

        ResepModel resep = resepService.getResepById(id);
        List<JumlahModel> listJumlah = resep.getJumlah();
        Boolean isApoteker = false;
        Boolean canConfirm = resepService.canConfirm(resep);

        if (userService.isDokter(principal) && !authentication.getName().equals(resep.getAppointment().getDokter().getUsername())){
            System.out.println("salah dokter");
            return "error/404";
        }
        if (userService.isApoteker(principal)){
            isApoteker = true;
        }

        model.addAttribute("resep",resep);
        model.addAttribute("listJumlah", listJumlah);
        model.addAttribute("isApoteker", isApoteker);
        model.addAttribute("canConfirm", canConfirm);
        return "dashboard/resep/detail";
    }

    // Form create resep
    @GetMapping("/resep/add/{kodeApt}")
    public String getResepAddForm(Model model, @PathVariable("kodeApt") String kodeApt, Principal principal, Authentication authentication) {
        String dokterLogin = authentication.getName();
//        System.out.println("login:" + dokterLogin);

        AppointmentModel apt = appointmentService.getAppointmentById(kodeApt);
//        System.out.println("apt:" + apt.getDokter().getUsername());
        // cek: role dokter, dokter yg login = dokter yg bersangkutan dgn appointment, appointment blm pny resep
        if (userService.isDokter(principal) && apt.getDokter().getUsername().equals(dokterLogin) && apt.getResep() == null){
            ResepModel resep = new ResepModel();
            List<ObatModel> listObat = obatService.getListObat();
            List<JumlahModel> listJumlah = jumlahService.getListJumlah();

            resep.setJumlah(new ArrayList<>());
            resep.getJumlah().add(new JumlahModel());

            model.addAttribute("resep", resep);
            model.addAttribute("listObat", listObat);
            model.addAttribute("listJumlah", listJumlah);
            model.addAttribute("kodeApt", kodeApt);

            return "dashboard/resep/form-add";
        }
        // Debug, delete klo udah

//        else if (!apt.getDokter().getUsername().equals(dokterLogin)){
//            System.out.println("salah dokter");
//            return "dashboard/index";
//        }
//        else if (apt.getResep() != null){
//            System.out.println("udh ada resep");
//            return "dashboard/index";
//        }
        else {
            return "error/404";
        }

    }

    // Confirmation create resep
    @PostMapping(value = "/resep/add/{kodeApt}")
    public String postResepAddForm(@ModelAttribute ResepModel resep, Model model, @PathVariable String kodeApt) {
        AppointmentModel apt = appointmentService.getAppointmentById(kodeApt);
        apt.setResep(resep);
        resep.setAppointment(apt);

        if (resep.getJumlah() == null){
            resep.setJumlah(new ArrayList<>());
        }
        else{
            int idx = 0;
            for (JumlahModel jml : resep.getJumlah()){
                jml.setResep(resep);
                jml.setObat(resep.getJumlah().get(idx).getObat());
                jml.setKuantitas(resep.getJumlah().get(idx).getKuantitas());
                idx++;
            }
        }
        resep.setIsDone(false);
        resep.setCreatedAt(LocalDateTime.now());
        resepService.addResep(resep);

        model.addAttribute("idResep", resep.getId());
        return "dashboard/resep/confirmation-add";
    }

    // Add Row obat
    @PostMapping(value="/resep/add/{kode}", params = {"addRow"})
    public String addRowObat(@ModelAttribute ResepModel resep, Model model, @PathVariable String kode){
        List<ObatModel> listObat = obatService.getListObat();
        if (resep.getJumlah() == null){
            resep.setJumlah(new ArrayList<>());
        }

        resep.getJumlah().add(new JumlahModel());
        List<JumlahModel> listJumlah = jumlahService.getListJumlah();

        model.addAttribute("resep", resep);
        model.addAttribute("listJumlah", listJumlah);
        model.addAttribute("listObat", listObat);

        return "dashboard/resep/form-add";
    }

    // Delete Row obat
    @PostMapping(value = "/resep/add/{kode}", params = {"deleteRow"})
    public String deleteRowObat(@ModelAttribute ResepModel resep, Model model, @RequestParam("deleteRow") Integer row, @PathVariable String kode){
        List<ObatModel> listObat = obatService.getListObat();
        final Integer rowInt = Integer.valueOf(row);
        resep.getJumlah().remove(rowInt.intValue());

        List<JumlahModel> listJumlah = resep.getJumlah();

        model.addAttribute("resep", resep);
        model.addAttribute("listJumlah", listJumlah);
        model.addAttribute("listObat", listObat);

        return "dashboard/resep/form-add";
    }

    // Update resep
    @GetMapping("/resep/update/{id}")
    public String resepUpdate(@PathVariable Long id, Model model, Principal principal, Authentication authentication){
        if (userService.isApoteker(principal)) {
            ResepModel resep = resepService.getResepById(id);
            ApotekerModel apoteker = apotekerService.getApotekerByUsername(authentication.getName());


            //cek kuantitas obat, ada semua --> bisa confirm
            boolean canConfirm = true;
            canConfirm = resepService.canConfirm(resep);

            // update status resep, update appointment, dan buat tagihan
            if (canConfirm) {
                // update resep
                resep.setIsDone(true);
                resep.setApoteker(apoteker);
                resepService.updateResep(resep);

                // update appointment
                resep.getAppointment().setIsDone(true);

                // buat tagihan
                Integer harga = resep.getAppointment().getDokter().getTarif();
                for (JumlahModel jml : resep.getJumlah()){
                    harga += jml.getObat().getHarga();
                }
                TagihanModel newBill = new TagihanModel();
                tagihanService.addTagihan(newBill, harga, resep.getAppointment());
            }
            else {
                canConfirm = false;
                System.out.println("gacukup obatnya");
                return "error/404";
            }

            model.addAttribute("resep", resep);
            model.addAttribute("canConfirm", canConfirm);
            return "dashboard/resep/confirmation-update";
        }
        else {
            System.out.println("bukan apoteker");
            return "error/404";
        }
    }

    // Delete resep
    @PostMapping("/resep/delete")
    public String deletePengajarSubmit(@ModelAttribute ResepModel resep, Model model) {
        return "dashboard/resep/confirmation-delete";
    }
}
