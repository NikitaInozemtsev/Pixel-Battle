package serv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import serv.models.Pixel;

public interface PixelRepository extends JpaRepository<Pixel, Integer> {
    @Modifying
    @Query("update Pixel p set p.color = ?1 where p.id = 1")
    void update(String color);
}
