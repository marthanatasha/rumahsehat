package apap.tugas.akhir.rumahsehat.service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import apap.tugas.akhir.rumahsehat.model.DTO.LoginAPIDTO;
import apap.tugas.akhir.rumahsehat.model.users.AdminModel;
import apap.tugas.akhir.rumahsehat.model.users.UserModel;
import apap.tugas.akhir.rumahsehat.repository.AdminDb;
import apap.tugas.akhir.rumahsehat.repository.UserDb;

@Service
@Transactional
public class AuthService {
    @Autowired
    private UserDb userDb;

    @Autowired
    private UserService userService;

    @Autowired
    private GeneralService generalService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String getToken(LoginAPIDTO loginAPIDTO) {
        String token = tokenGenerator(256);
        UserModel user = userService.getUserByEmail(loginAPIDTO.getEmail());

        if (encoder.matches(loginAPIDTO.getPassword(), user.getPassword())) {
            user.setToken(token);
            return token;
        }
        return null;
    }

    public UserModel getUserInfo(String token) {
        List<UserModel> listUser = userDb.findAll();
        String tokenparsed = token.split(" ")[1];
        for (UserModel user : listUser) {
            if (tokenparsed.equals(user.getToken())) {
                return user;
            }
        }
        return null;
    }

    public Boolean tokenCheck(String token) {
        List<UserModel> listUser = userDb.findAll();
        String tokenparsed = token.split(" ")[1];
        for (UserModel user : listUser) {
            if (tokenparsed.equals(user.getToken())) {
                return true;
            }
        }
        return false;
    }

    static String tokenGenerator(int n) {

        byte[] array = new byte[256];
        new Random().nextBytes(array);
        String randomString = new String(array, Charset.forName("UTF-8"));

        StringBuffer r = new StringBuffer();

        for (int k = 0; k < randomString.length(); k++) {
            char ch = randomString.charAt(k);
            if (((ch >= 'a' && ch <= 'z')
                    || (ch >= 'A' && ch <= 'Z')
                    || (ch >= '0' && ch <= '9'))
                    && (n > 0)) {
                r.append(ch);
                n--;
            }
        }

        return r.toString();
    }

}
