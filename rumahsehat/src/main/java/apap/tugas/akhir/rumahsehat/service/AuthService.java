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

@Service
@Transactional
public class AuthService {
    @Autowired
    private AdminDb adminDb;

    @Autowired
    private UserService userService;

    @Autowired
    private GeneralService generalService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String getToken(LoginAPIDTO loginAPIDTO) {
        String token = tokenGenerator(256);
        UserModel user = userService.getUserByEmail(loginAPIDTO.getEmail());

        if (encoder.matches(loginAPIDTO.getPassword(), user.getPassword())) {
            return token;
        }

        return "0";
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
