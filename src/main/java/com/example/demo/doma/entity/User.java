package com.example.demo.doma.entity;

import com.example.demo.doma.annotation.TimestampableEntity;
import com.example.demo.doma.listener.CommonListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.seasar.doma.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Date;

@Entity(listener = CommonListener.class)
@Data
@Table(name = "user")
@TimestampableEntity
public class User implements UserDetails, BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotEmpty
    private String name;

    private Integer age;

    @Length(min = 5)
    @NotEmpty
    @JsonIgnore
    private String password;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

//    /**
//     * UserDetailsServiceでチェックするパスワードを返却する
//     * Lombokを使用している場合、フィールドに「password」があれば、
//     * ＠GetterでgetPassword()を生成してくれる為、不要
//     */
//    @Override
//    public String getPassword() {
//        return password;
//    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
