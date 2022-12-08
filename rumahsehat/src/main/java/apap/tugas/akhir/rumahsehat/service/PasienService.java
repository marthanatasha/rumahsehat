package apap.tugas.akhir.rumahsehat.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.akhir.rumahsehat.model.users.PasienModel;
import apap.tugas.akhir.rumahsehat.repository.PasienDb;

@Service
@Transactional
public class PasienService {
    @Autowired
    private PasienDb pasienDb;

    @Autowired
    private GeneralService generalService;

    public List<PasienModel> getListPasien() {
        return pasienDb.findAll();
    }

    public PasienModel getPasienById(String id) {
        return pasienDb.findById(id).get();
    }

    public void addPasien(PasienModel pasien) {
        String pass = generalService.encrypt(pasien.getPassword());
        pasien.setPassword(pass);
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
