package apap.tugas.akhir.rumahsehat.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class ResepDTO {
    private Long id;
    private String namaDokter;
    private String namaPasien;
    private String statusResep;
    private String namaApoteker;
    private List<JumlahDTO> listObat;

    public ResepDTO(Long id, String namaDokter, String namaPasien, List<JumlahDTO> listObat){
        this.id = id;
        this.namaDokter = namaDokter;
        this.namaPasien = namaPasien;
        this.listObat = listObat;
    }

    public void setApoteker (String namaApoteker){
        this.namaApoteker = namaApoteker;
    }
    public void setStatus (String status){
        this.statusResep = status;
    }
}
