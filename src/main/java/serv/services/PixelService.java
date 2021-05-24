package serv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serv.LZW;
import serv.models.Pixel;
import serv.repositories.PixelRepository;

import javax.transaction.Transactional;
import java.util.Arrays;

/** Реализация логики сервиса*/
@Service
public class PixelService {
    /** Репозиторий отвечающий за связь с таблицей*/
    @Autowired
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
    @Transactional
    public void setPixels(Pixel px) {
        reps.update(px.getColor());
    }

    /** Сохранение пикселей в таблице. Нужно для первичного заполнения
     * @param px пиксели
     */
    public void savePixels(Pixel px) {
        reps.save(px);
    }
}
