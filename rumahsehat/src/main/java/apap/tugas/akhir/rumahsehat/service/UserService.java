package apap.tugas.akhir.rumahsehat.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.akhir.rumahsehat.model.users.UserModel;
import apap.tugas.akhir.rumahsehat.repository.UserDb;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserDb userDb;

    public List<UserModel> getListUser() {
        return userDb.findAll();
    }
}
