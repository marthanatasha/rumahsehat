package apap.tugas.akhir.rumahsehat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "obat")
public class ObatModel {
    @Id
    @Size(max = 255)
    private String idObat;

    @NotNull
    @Column(name = "namaObat", nullable = false)
    private String namaObat;

    @NotNull
    @Column(name = "stok", nullable = false)
    private Integer stok;

    @NotNull
    @Column(name = "harga", nullable = false)
    private Long harga;
}
