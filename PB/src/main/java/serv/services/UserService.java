package serv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import serv.models.User;
import serv.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    private UserRepository reps;

    public UserService(UserRepository reps, BCryptPasswordEncoder enc) {
        this.reps = reps;
        this.encoder = enc;
    }

    public void signUpUser(User user) {

        final String encryptedPassword = encoder.encode(user.getPassword());

        user.setPassword(encryptedPassword);

        if(reps.findById(user.getUsername()).isEmpty()) {
            reps.save(user);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        return reps.findById(s).get();
    }
}
