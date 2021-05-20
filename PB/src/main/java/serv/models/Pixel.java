package serv.models;

import javax.persistence.*;

/** �������� �������*/
@Entity
@Table(name = "colores")
public class Pixel {
    /** ����� ��������*/
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "color")
    @Lob
    String color;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Pixel() {
    }

    public Pixel(String color) {
        this.color = color;
    }
}
