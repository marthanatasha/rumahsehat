package apap.tugas.akhir.rumahsehat.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.akhir.rumahsehat.model.ObatModel;
import apap.tugas.akhir.rumahsehat.repository.ObatDb;

@Service
@Transactional
public class ObatService {
    @Autowired
    private ObatDb obatDb;

    public List<ObatModel> getListObat() {
        return obatDb.findAll();
    }

    public ObatModel getObatById(String id) {
        return obatDb.findById(id).get();
    }

    public void addObat(ObatModel obat) {
        obatDb.save(obat);
    }

    public ObatModel updateObat(ObatModel obat) {
        obatDb.save(obat);
        return obat;
    }

    public ObatModel deleteObat(ObatModel obat) {
        obatDb.delete(obat);
        return obat;
    }
}
