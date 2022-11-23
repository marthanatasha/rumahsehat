package apap.tugas.akhir.rumahsehat.controller.web;

import apap.tugas.akhir.rumahsehat.model.AppointmentModel;
import apap.tugas.akhir.rumahsehat.model.JumlahModel;
import apap.tugas.akhir.rumahsehat.model.ObatModel;
import apap.tugas.akhir.rumahsehat.service.AppointmentService;
import apap.tugas.akhir.rumahsehat.service.JumlahService;
import apap.tugas.akhir.rumahsehat.service.ObatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.tugas.akhir.rumahsehat.model.ResepModel;
import apap.tugas.akhir.rumahsehat.service.ResepService;

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

    // List resep
    @GetMapping("/resep")
    public String getResepList(Model model) {
        List<ResepModel> listResep = resepService.getListResep();
        //System.out.println(listResep);
        model.addAttribute("resep", listResep);

        return "pages/resep/list";
    }

    // Detail resep
    @GetMapping("/resep/{id}")
    public String getResepById(@PathVariable Long id, Model model) {
        return "pages/resep/detail";
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

        return "pages/resep/form-add";
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
        return "pages/resep/confirmation-add";
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

        return "pages/resep/form-add";
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

        return "pages/resep/form-add";
    }

    // Form update resep
    @GetMapping("/resep/update/{id}")
    public String getResepAddUpdate(@PathVariable Long id, Model model) {
        return "pages/resep/form-update";
    }

    // Confirmation update resep
    @PostMapping(value = "/resep/update")
    public String postResepUpdateForm(
            @ModelAttribute ResepModel resep, Model model) {
        return "pages/resep/confirmation-update";
    }

    // Delete resep
    @PostMapping("/resep/delete")
    public String deletePengajarSubmit(@ModelAttribute ResepModel resep, Model model) {
        return "pages/resep/confirmation-delete";
    }
}
