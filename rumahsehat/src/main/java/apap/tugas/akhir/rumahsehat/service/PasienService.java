package apap.tugas.akhir.rumahsehat.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.akhir.rumahsehat.model.DTO.PasienDTO;
import apap.tugas.akhir.rumahsehat.model.users.PasienModel;
import apap.tugas.akhir.rumahsehat.model.users.UserType;
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

    public PasienModel addPasien(PasienDTO pasien) {
        PasienModel newPasien = new PasienModel();
        newPasien.setNama(pasien.getNama());
        newPasien.setUsername(pasien.getUsername());
        newPasien.setPassword(pasien.getPassword());
        newPasien.setEmail(pasien.getEmail());
        newPasien.setUmur(pasien.getUmur());
        newPasien.setSaldo(0);
        newPasien.setIsSso(false);
        newPasien.setRole(UserType.PASIEN);
        return pasienDb.save(newPasien);
    }

    public PasienModel updatePasien(String username, int saldo) {
        PasienModel updatePasien = pasienDb.findByUsername(username);
        updatePasien.setSaldo(updatePasien.getSaldo()+saldo);
        return pasienDb.save(updatePasien);
    }
}
