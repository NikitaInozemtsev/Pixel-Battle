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
        if (reps.findAll().isEmpty()) {
            String ab = "";
            for(int i = 0; i < 160000; i++) {
                ab += "#FFFFFF ";
            }
            ab = ab.substring(0, ab.length()-1);
            ab = Arrays.stream(LZW.compress(ab).toArray())
                    .map(String::valueOf)
                    .reduce((a, b) -> a.concat(" ").concat(b))
                    .get();
            reps.save(new Pixel(ab));
        }
    }

    /** ��������� �������� �� �������
     * @return �������*/
    public Pixel getPixels() {
        return reps.findAll().get(0);
    }

    /** ���������� �������� � �������
     * @param px �������*/
    @Transactional
    public void setPixels(Pixel px) {
        reps.update(px.getColor());
    }
}
