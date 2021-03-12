package serv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import serv.models.User;
import serv.repositories.UserRepository;

/** Реализация логики сервиса*/
@Service
public class UserService implements UserDetailsService {
    /** Шифратор*/
    @Autowired
    BCryptPasswordEncoder encoder;
    /** Репозиторий отвечающий за связь с таблицей*/
    @Autowired
    private UserRepository reps;

    public UserService(UserRepository reps, BCryptPasswordEncoder enc) {
        this.reps = reps;
        this.encoder = enc;
    }

    /** Добавление пользователя в табоицу
     * @param user пользователь*/
    public void signUpUser(User user) {

        final String encryptedPassword = encoder.encode(user.getPassword());

        user.setPassword(encryptedPassword);

        if(reps.findById(user.getUsername()).isEmpty()) {
            reps.save(user);
        }
    }

    /** Загрузка пользователя по его имени
     * @param s имя пользователя
     * @return пользователь*/
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return reps.findById(s).get();
    }
}
