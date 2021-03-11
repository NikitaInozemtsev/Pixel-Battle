package serv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import serv.models.Pixel;

public interface PixelRepository extends JpaRepository<Pixel, String> {
}
