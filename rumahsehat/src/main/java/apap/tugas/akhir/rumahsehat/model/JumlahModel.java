package apap.tugas.akhir.rumahsehat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tagihan")
public class JumlahModel {
    @Id
    @Size(max = 255)
    private String id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private ObatModel obat;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private ResepModel resep;

    @NotNull
    @Column(name = "kuantitas", nullable = false)
    private Integer kuantitas;
}
