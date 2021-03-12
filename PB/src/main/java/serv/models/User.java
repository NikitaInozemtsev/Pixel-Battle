package serv.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;

/** Сущность пользователь. */
@Entity
@Table(name = "t_user")
public class User implements UserDetails {
    /** Имя пользователя является идентификатором. */
    @Id
    @Column(name = "username")
    private String username;
    /** Пароль пользователя.*/
    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(String u, String p) {
        this.username = u;
        this.password = p;
    }

    public String getUsername() {
        return username;
    }

    /** Метод отвечающий за актуальность аккаунта
     * @return аккаунт актуальны */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    /** Метод отвечающий за блокировку аккаунта
     * @return не заблокирован пользователь*/
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /** Метод отвечающий за актуальность учетных данных
     * @return учетные данные актуальны */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /** Метод отвечающий за разрешение
     * @return разрешение получено */
    @Override
    public boolean isEnabled() {
        return true;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    /** Метод отвечающий за права пользователя
     * @return права пользователя*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
