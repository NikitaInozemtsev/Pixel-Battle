package serv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serv.LZW;
import serv.models.Pixel;
import serv.repositories.PixelRepository;

import javax.transaction.Transactional;
import java.util.Arrays;

/** ���������� ������ �������*/
@Service
public class PixelService {
    /** ����������� ���������� �� ����� � ��������*/
    @Autowired
    private PixelRepository reps;

    public PixelService(PixelRepository reps) {
        this.reps = reps;
    }

    /** ��������� �������� �� �������
     * @return �������*/
    public Pixel getPixels() {
        if (reps.findAll().isEmpty()) {
            return new Pixel("");
        }
        else {
            return reps.findAll().get(0);
        }
    }

    /** ���������� �������� � �������
     * @param px �������*/
    @Transactional
    public void setPixels(Pixel px) {
        reps.update(px.getColor());
    }

    /** ���������� �������� � �������. ����� ��� ���������� ����������
     * @param px �������
     */
    public void savePixels(Pixel px) {
        reps.save(px);
    }
}
