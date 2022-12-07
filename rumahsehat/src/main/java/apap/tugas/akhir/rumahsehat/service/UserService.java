package apap.tugas.akhir.rumahsehat.service;

import apap.tugas.akhir.rumahsehat.model.users.UserModel;
import apap.tugas.akhir.rumahsehat.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserDb userDb;

    public List<UserModel> getListUser() {
        return userDb.findAll();
    }

    public UserModel getUserById(String id) {
        Optional<UserModel> user = userDb.findById(id);
        return user.orElse(null);
    }

    public UserModel getRestUserById(String id) {
        System.out.println("masuk service"); // TODO: debug
        Optional<UserModel> user = userDb.findById(id);
        if (user.isPresent()) {
            System.out.println(user.get().getId()); // TODO: debug
            return user.get();
        } else {
            System.out.println("not found"); // TODO: debug
            throw new NoSuchElementException();
        }
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
        return userDb.findByUsername(username);
    }
}
