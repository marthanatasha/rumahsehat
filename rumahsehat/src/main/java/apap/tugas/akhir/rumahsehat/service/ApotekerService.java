package apap.tugas.akhir.rumahsehat.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.akhir.rumahsehat.model.users.ApotekerModel;
import apap.tugas.akhir.rumahsehat.repository.ApotekerDb;

@Service
@Transactional
public class ApotekerService {
    @Autowired
    private ApotekerDb apotekerDb;

    @Autowired
    private GeneralService generalService;

    public List<ApotekerModel> getListApoteker() {
        return apotekerDb.findAll();
    }

    public ApotekerModel getApotekerById(String id) {
        return apotekerDb.findById(id).get();
    }

    public ApotekerModel getApotekerByUsername(String username){
        return apotekerDb.findByUsername(username);
    }

    public void addApoteker(ApotekerModel apoteker) {
        String pass = generalService.encrypt(apoteker.getPassword());
        apoteker.setPassword(pass);
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
