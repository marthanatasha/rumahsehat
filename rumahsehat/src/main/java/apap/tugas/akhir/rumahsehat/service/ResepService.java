package apap.tugas.akhir.rumahsehat.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.akhir.rumahsehat.model.ResepModel;
import apap.tugas.akhir.rumahsehat.repository.ResepDb;

@Service
@Transactional
public class ResepService {
    @Autowired
    private ResepDb resepDb;

    public List<ResepModel> getListResep() {
        return resepDb.findAll();
    }
}