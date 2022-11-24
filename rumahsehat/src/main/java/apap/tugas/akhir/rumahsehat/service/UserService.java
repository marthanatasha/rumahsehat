package apap.tugas.akhir.rumahsehat.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public UserModel getUserById(String id) {
        return userDb.findById(id).get();
    }

    public void addUser(UserModel user) {
        userDb.save(user);
    }

    public UserModel updateUser(UserModel user) {
        userDb.save(user);
        return user;
    }

    public UserModel deleteUser(UserModel user) {
        userDb.delete(user);
        return user;
    }

    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    public UserModel getUserByUsername(String username) {
        UserModel user = userDb.findByUsername(username);
        return user;
    }
}
