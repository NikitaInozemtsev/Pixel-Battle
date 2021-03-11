package serv.services;

import org.springframework.stereotype.Service;
import serv.models.Pixel;
import serv.repositories.PixelRepository;

@Service
public class PixelService {
    private PixelRepository reps;

    public PixelService(PixelRepository reps) {
        this.reps = reps;
    }

    public Pixel getPixels() {
        if (reps.findAll().isEmpty()) {
            return new Pixel("");
        }
        else {
            return reps.findAll().get(0);
        }
    }

    public void setPixels(Pixel px) {
        if (!reps.findAll().isEmpty()) {
            reps.deleteAll();
        }
        reps.save(px);
    }
}
