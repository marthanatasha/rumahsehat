package apap.tugas.akhir.rumahsehat.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.akhir.rumahsehat.model.users.AdminModel;
import apap.tugas.akhir.rumahsehat.repository.AdminDb;

@Service
@Transactional
public class AdminService {
    @Autowired
    private AdminDb adminDb;

    public List<AdminModel> getListAdmin() {
        return adminDb.findAll();
    }
}
