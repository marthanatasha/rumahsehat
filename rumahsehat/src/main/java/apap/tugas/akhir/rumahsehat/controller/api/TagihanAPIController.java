package apap.tugas.akhir.rumahsehat.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.tugas.akhir.rumahsehat.model.TagihanModel;
import apap.tugas.akhir.rumahsehat.model.DTO.TagihanDTO;
import apap.tugas.akhir.rumahsehat.service.TagihanService;

@CrossOrigin()
@RestController
@RequestMapping("/api/v1")
public class TagihanAPIController {

    @Autowired
    TagihanService tagihanService;

    // All tagihan pasien
    @GetMapping("/tagihan/{id}")
    public List<TagihanDTO> getTagihanPasien(@PathVariable("id") String id) {
        return tagihanService.getTagihanDTO(id);
    }

    // Detail tagihan pasien
    @GetMapping("/tagihan/detail/{id}")
    public TagihanDTO getDetailTagihan(@PathVariable("id") String id) {
        return tagihanService.getTagihanById(id);
    }

    // Pembayaran tagihan
    @GetMapping("/tagihan/pembayaran/{noTagihan}")
    public TagihanDTO pembayaranTagihan(@PathVariable("noTagihan") String noTagihan) {
        return tagihanService.pembayaranTagihan(noTagihan);
    }

    // Confirmation create tagihan
    @PostMapping(value = "/tagihan/add")
    public String postTagihanAddForm(
            @ModelAttribute TagihanModel tagihan, Model model) {
        return "pages/tagihan/confirmation-add";
    }

    // Form update tagihan
    @GetMapping("/tagihan/update/{id}")
    public String getTagihanAddUpdate(@PathVariable Long id, Model model) {
        return "pages/tagihan/form-update";
    }

    // Confirmation update tagihan
    @PostMapping(value = "/tagihan/update")
    public String postTagihanUpdateForm(
            @ModelAttribute TagihanModel tagihan, Model model) {
        return "pages/tagihan/confirmation-update";
    }

    // Delete tagihan
    @PostMapping("/tagihan/delete")
    public String deletePengajarSubmit(@ModelAttribute TagihanModel tagihan, Model model) {
        return "pages/tagihan/confirmation-delete";
    }
}
