package apap.tugas.akhir.rumahsehatapi.service;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import apap.tugas.akhir.rumahsehatapi.models.users.UserModel;
import apap.tugas.akhir.rumahsehatapi.models.users.UserType;
import apap.tugas.akhir.rumahsehatapi.repository.UserDb;

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

    public Boolean isAdmin(Principal principal) {
        UserModel user = userDb.findByUsername(principal.getName());
        if (user.getRole().equals(UserType.ADMIN)) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean isApoteker(Principal principal) {
        UserModel user = userDb.findByUsername(principal.getName());
        if (user.getRole().equals(UserType.APOTEKER)) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean isDokter(Principal principal) {
        UserModel user = userDb.findByUsername(principal.getName());
        if (user.getRole().equals(UserType.DOKTER)) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean isPasien(Principal principal) {
        UserModel user = userDb.findByUsername(principal.getName());
        if (user.getRole().equals(UserType.PASIEN)) {
            return true;
        } else {
            return false;
        }
    }
}
