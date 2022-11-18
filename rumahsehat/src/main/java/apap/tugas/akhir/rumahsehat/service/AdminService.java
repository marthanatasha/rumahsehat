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

    public AdminModel getAdminById(String id) {
        return adminDb.findById(id).get();
    }

    public void addAdmin(AdminModel admin) {
        adminDb.save(admin);
    }

    public AdminModel updateAdmin(AdminModel admin) {
        adminDb.save(admin);
        return admin;
    }

    public AdminModel deleteAdmin(AdminModel admin) {
        adminDb.delete(admin);
        return admin;
    }
}
