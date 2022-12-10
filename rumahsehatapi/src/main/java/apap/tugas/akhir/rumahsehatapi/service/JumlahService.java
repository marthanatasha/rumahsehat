package apap.tugas.akhir.rumahsehatapi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.akhir.rumahsehatapi.models.JumlahModel;
import apap.tugas.akhir.rumahsehatapi.repository.JumlahDb;

@Service
@Transactional
public class JumlahService {
    @Autowired
    private JumlahDb jumlahDb;

    public List<JumlahModel> getListJumlah() {
        return jumlahDb.findAll();
    }

    public JumlahModel getJumlahById(String id) {
        return jumlahDb.findById(id).get();
    }

    public void addJumlah(JumlahModel jumlah) {
        jumlahDb.save(jumlah);
    }

    public JumlahModel updateJumlah(JumlahModel jumlah) {
        jumlahDb.save(jumlah);
        return jumlah;
    }

    public JumlahModel deleteJumlah(JumlahModel jumlah) {
        jumlahDb.delete(jumlah);
        return jumlah;
    }
}
