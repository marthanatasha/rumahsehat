package apap.tugas.akhir.rumahsehatapi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.akhir.rumahsehatapi.models.users.PasienModel;
import apap.tugas.akhir.rumahsehatapi.repository.PasienDb;

@Service
@Transactional
public class PasienService {
    @Autowired
    private PasienDb pasienDb;

    public List<PasienModel> getListPasien() {
        return pasienDb.findAll();
    }

    public PasienModel getPasienById(String id) {
        return pasienDb.findById(id).get();
    }

    public void addPasien(PasienModel pasien) {
        pasienDb.save(pasien);
    }

    public PasienModel updatePasien(PasienModel pasien) {
        pasienDb.save(pasien);
        return pasien;
    }

    public PasienModel deletePasien(PasienModel pasien) {
        pasienDb.delete(pasien);
        return pasien;
    }
}
