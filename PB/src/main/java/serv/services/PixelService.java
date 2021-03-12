package serv.services;

import org.springframework.stereotype.Service;
import serv.models.Pixel;
import serv.repositories.PixelRepository;

/** Реализация логики сервиса*/
@Service
public class PixelService {
    /** Репозиторий отвечающий за связь с таблицей*/
    private PixelRepository reps;

    public PixelService(PixelRepository reps) {
        this.reps = reps;
    }

    /** Получение пикселей из таблицы
     * @return пиксели*/
    public Pixel getPixels() {
        if (reps.findAll().isEmpty()) {
            return new Pixel("");
        }
        else {
            return reps.findAll().get(0);
        }
    }

    /** Обновление пикселей в таблице
     * @param px пиксели*/
    public void setPixels(Pixel px) {
        if (!reps.findAll().isEmpty()) {
            reps.deleteAll();
        }
        reps.save(px);
    }
}
