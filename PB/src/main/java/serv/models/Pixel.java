package serv.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** —ущность пиксели*/
@Entity
@Table(name = "colores")
public class Pixel {
    /** ÷вета пикселей*/
    @Id
    @Column(name = "color")
    String color;

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
