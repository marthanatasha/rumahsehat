package apap.tugas.akhir.rumahsehat.controller.web;

import apap.tugas.akhir.rumahsehat.model.*;
import apap.tugas.akhir.rumahsehat.model.users.ApotekerModel;
import apap.tugas.akhir.rumahsehat.model.users.DokterModel;
import apap.tugas.akhir.rumahsehat.model.users.PasienModel;
import apap.tugas.akhir.rumahsehat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    // List resep
    @GetMapping("/resep")
    public String getResepList(Model model) {
        List<ResepModel> listResep = resepService.getListResep();
        model.addAttribute("listResep", listResep);
        return "dashboard/resep/list";
    }

    // Detail resep
    @GetMapping("/resep/{id}")
    public String getResepById(@PathVariable Long id, Model model) {
        ResepModel resep = resepService.getResepById(id);
        List<JumlahModel> listJumlah = resep.getJumlah();
        System.out.println(listJumlah);
        DokterModel dokter = resep.getAppointment().getDokter();
        PasienModel pasien = resep.getAppointment().getPasien();
        ApotekerModel apoteker = resep.getApoteker();
        model.addAttribute("resep",resep);
        model.addAttribute("listJumlah", listJumlah);
        model.addAttribute("dokter", dokter);
        model.addAttribute("pasien", pasien);
        model.addAttribute("apoteker", apoteker);
        return "dashboard/resep/detail";
    }

    // Form create resep
    @GetMapping("/resep/add/{kode}")
    public String getResepAddForm(Model model, @PathVariable String kode) {
        ResepModel resep = new ResepModel();
        List<ObatModel> listObat = obatService.getListObat();
        List<JumlahModel> listJumlah = jumlahService.getListJumlah();

        resep.setJumlah(new ArrayList<>());
        resep.getJumlah().add(new JumlahModel());

        model.addAttribute("resep", resep);
        model.addAttribute("listObat", listObat);
        model.addAttribute("listJumlah", listJumlah);
        model.addAttribute("kode", kode);

        return "dashboard/resep/form-add";
    }

    // Confirmation create resep
    @PostMapping(value = "/resep/add/{kode}")
    public String postResepAddForm(@ModelAttribute ResepModel resep, Model model, @PathVariable String kode) {
        AppointmentModel app = appointmentService.getAppointmentById(kode);
        app.setResep(resep);
        resep.setAppointment(app);

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

//        List<AppointmentModel> listAppointment = appointmentService.getListAppointment();
//        for (AppointmentModel a : listAppointment){
//            if (a.getKode().equals(kode)){
//                resep.setAppointment(a);
//                a.setResep(resep);
//            }
//        }

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

    /**
    // Form update resep
    @GetMapping("/resep/update/{id}")
    public String getResepAddUpdate(@PathVariable Long id, Model model) {
        return "dashboard/resep/form-update";
    }

    // Confirmation update resep
    @PostMapping(value = "/resep/update")
    public String postResepUpdateForm(
            @ModelAttribute ResepModel resep, Model model) {
        return "dashboard/resep/confirmation-update";
    }

     */

    // Update resep
    @GetMapping("/resep/update/{id}")
    public String resepUpdate(@PathVariable Long id, Model model, Authentication authentication){
        ResepModel resep = resepService.getResepById(id);
        //to do: ambil id apoteker masih gatau caranya T_T
        // to do: cek rolenya apoteker ato bukan
        String username = authentication.getName();
        ApotekerModel apoteker = apotekerService.getApotekerByUsername(username);


        //cek kuantitas obat
        boolean canConfirm = true;
        List<JumlahModel> listJumlah = resep.getJumlah();
        for (JumlahModel jml : listJumlah){
            ObatModel obat = jml.getObat();
            if (obat.getStok() < jml.getKuantitas()){
                canConfirm = false;
                break;
            }
        }

        // update status resep dan buat tagihan
        if (canConfirm){
            int harga = 0;
            for (JumlahModel jml : listJumlah){
                harga += jml.getObat().getHarga();
            }
            resep.setIsDone(true);
            resep.setApoteker(apoteker);
            resepService.updateResep(resep);
            resep.getAppointment().setIsDone(true);
            TagihanModel bill = new TagihanModel();
            bill.setAppointment(resep.getAppointment());
            //to do: identifiers custom generator buat kode tagihan
            List<TagihanModel> allBill = tagihanService.getListTagihan();
            bill.setKode("BILL-" + allBill.size()+1);
            bill.setIsPaid(false);
            bill.setTanggalTerbuat(LocalDateTime.now());
            bill.setJumlahTagihan(resep.getAppointment().getDokter().getTarif() + harga);
            tagihanService.addTagihan(bill);
        }

        model.addAttribute("resep", resep);
        model.addAttribute("canConfirm", canConfirm);
        return "dashboard/resep/confirmation-update";
    }

    // Delete resep
    @PostMapping("/resep/delete")
    public String deletePengajarSubmit(@ModelAttribute ResepModel resep, Model model) {
        return "dashboard/resep/confirmation-delete";
    }
}
