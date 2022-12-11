package apap.tugas.akhir.rumahsehatapi.models.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class AppointmentDTO {
    private LocalDateTime waktuAwal;
    private String dokterId;
    private String pasienId;
}
