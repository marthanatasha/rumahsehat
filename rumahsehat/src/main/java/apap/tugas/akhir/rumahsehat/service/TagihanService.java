package apap.tugas.akhir.rumahsehat.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.akhir.rumahsehat.model.TagihanModel;
import apap.tugas.akhir.rumahsehat.model.DTO.TagihanDTO;
import apap.tugas.akhir.rumahsehat.model.users.PasienModel;
import apap.tugas.akhir.rumahsehat.repository.TagihanDb;

@Service
@Transactional
public class TagihanService {
    @Autowired
    private TagihanDb tagihanDb;

    @Autowired
    PasienService pasienService;

    public List<TagihanModel> getListTagihan() {
        return tagihanDb.findAll();
    }

    public List<TagihanDTO> getTagihanDTO(String idPasien) {
        List<TagihanModel> tagihan = tagihanDb.findAll();
        List<TagihanDTO> tagihanDTO = new ArrayList<TagihanDTO>();

        for (TagihanModel aTagihan: tagihan){
            if(aTagihan.getAppointment().getPasien().getId().equals(idPasien)){
                TagihanDTO newTagihan = new TagihanDTO();
                PasienModel pasien = aTagihan.getAppointment().getPasien();
                newTagihan.setNomorTagihan(aTagihan.getKode());
                newTagihan.setTanggalTerbuat(aTagihan.getTanggalTerbuat().toString());
                newTagihan.setIdPasien(pasien.getId());
                newTagihan.setJumlahTagihan(aTagihan.getJumlahTagihan());

                // Cek Status Pasien
                if (aTagihan.getIsPaid() == true){
                    newTagihan.setStatus("LUNAS");
                    newTagihan.setTanggalBayar(aTagihan.getTanggalBayar().toString());
                } else{
                    newTagihan.setStatus("BELUM LUNAS");
                    newTagihan.setTanggalBayar("Anda belum melunasi pembayaran");
                }

                tagihanDTO.add(newTagihan);
            } else{
                continue;
            }
        }
        return tagihanDTO;
    }

    public TagihanDTO getTagihanById(String id) {
        TagihanModel tagihan = tagihanDb.findById(id).get();
        TagihanDTO tagihanDTO = new TagihanDTO();

        tagihanDTO.setIdPasien(tagihan.getAppointment().getPasien().getId());
        tagihanDTO.setNomorTagihan(tagihan.getKode());
        tagihanDTO.setTanggalTerbuat(tagihan.getTanggalTerbuat().toString());
        tagihanDTO.setJumlahTagihan(tagihan.getJumlahTagihan());

        // Cek Status Pasien
        if (tagihan.getIsPaid() == true){
            tagihanDTO.setStatus("LUNAS");
            tagihanDTO.setTanggalBayar(tagihan.getTanggalBayar().toString());
        } else{
            tagihanDTO.setStatus("BELUM LUNAS");
            tagihanDTO.setTanggalBayar("Anda belum melunasi pembayarab");
        }
        return tagihanDTO;
    }

    public TagihanDTO pembayaranTagihan(String id) {
        TagihanModel tagihan = tagihanDb.findById(id).get();
        TagihanDTO tagihanDTO = new TagihanDTO();

        // Pemilik tagihan dan saldonya
        PasienModel pasien = tagihan.getAppointment().getPasien();
        int saldoBaru = pasien.getSaldo() - tagihan.getJumlahTagihan();

        // Pembayaran valid: Set pembayaran terbayar dan tanggal
        if(pasien.getSaldo() > tagihan.getJumlahTagihan()){
            tagihan.setTanggalBayar(LocalDateTime.now());
            tagihan.setIsPaid(true);
            pasien.setSaldo(saldoBaru);
        } 

        tagihanDTO.setIdPasien(tagihan.getAppointment().getPasien().getId());
        tagihanDTO.setNomorTagihan(tagihan.getKode());
        tagihanDTO.setTanggalTerbuat(tagihan.getTanggalTerbuat().toString());
        tagihanDTO.setJumlahTagihan(tagihan.getJumlahTagihan());

        // Cek Status Pasien
        if (tagihan.getIsPaid() == true){
            tagihanDTO.setStatus("LUNAS");
            tagihanDTO.setTanggalBayar(tagihan.getTanggalBayar().toString());
        } else{
            tagihanDTO.setStatus("BELUM LUNAS");
            tagihanDTO.setTanggalBayar("Anda belum melunasi pembayarab");
        }

        System.out.println("Saldo: " + pasien.getSaldo());
        return tagihanDTO;
    }

    public void addTagihan(TagihanModel tagihan) {
        tagihanDb.save(tagihan);
    }

    public TagihanModel updateTagihan(TagihanModel tagihan) {
        tagihanDb.save(tagihan);
        return tagihan;
    }

    public TagihanModel deleteTagihan(TagihanModel tagihan) {
        tagihanDb.delete(tagihan);
        return tagihan;
    }
}
