package apap.tugas.akhir.rumahsehat.service;

import apap.tugas.akhir.rumahsehat.model.DokterModel;

public class GeneralService {

    public static void testing() {
        DokterModel dokter;
        dokter = new DokterModel();

        dokter.setNama("dokter");
        dokter.setTarif(1000000L);
        dokter.setEmail(null);
    }

}
