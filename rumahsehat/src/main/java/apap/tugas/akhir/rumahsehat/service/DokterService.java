package apap.tugas.akhir.rumahsehat.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.akhir.rumahsehat.model.users.DokterModel;
import apap.tugas.akhir.rumahsehat.repository.DokterDb;

@Service
@Transactional
public class DokterService {
    @Autowired
    private DokterDb dokterDb;

    public List<DokterModel> getListDokter() {
        return dokterDb.findAll();
    }
}
