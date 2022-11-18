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

    public List<PasienModel> getListPasien() {
        return pasienDb.findAll();
    }
}
