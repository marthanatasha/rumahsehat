package apap.tugas.akhir.rumahsehatapi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.akhir.rumahsehatapi.models.users.ApotekerModel;
import apap.tugas.akhir.rumahsehatapi.repository.ApotekerDb;

@Service
@Transactional
public class ApotekerService {
    @Autowired
    private ApotekerDb apotekerDb;

    public List<ApotekerModel> getListApoteker() {
        return apotekerDb.findAll();
    }

    public ApotekerModel getApotekerById(Long id) {
        return apotekerDb.findById(id).get();
    }

    public void addApoteker(ApotekerModel apoteker) {
        apotekerDb.save(apoteker);
    }

    public ApotekerModel updateApoteker(ApotekerModel apoteker) {
        apotekerDb.save(apoteker);
        return apoteker;
    }

    public ApotekerModel deleteApoteker(ApotekerModel apoteker) {
        apotekerDb.delete(apoteker);
        return apoteker;
    }
}
