package serv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import serv.models.User;

public interface UserRepository extends JpaRepository<User, String> {
}
