package serv.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;

/** �������� ������������. */
@Entity
@Table(name = "t_user")
public class User implements UserDetails {
    /** ��� ������������ �������� ���������������. */
    @Id
    @Column(name = "username")
    private String username;
    /** ������ ������������.*/
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

    /** ����� ���������� �� ������������ ��������
     * @return ������� ��������� */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    /** ����� ���������� �� ���������� ��������
     * @return �� ������������ ������������*/
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /** ����� ���������� �� ������������ ������� ������
     * @return ������� ������ ��������� */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /** ����� ���������� �� ����������
     * @return ���������� �������� */
    @Override
    public boolean isEnabled() {
        return true;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    /** ����� ���������� �� ����� ������������
     * @return ����� ������������*/
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
