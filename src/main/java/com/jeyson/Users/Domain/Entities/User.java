package com.jeyson.Users.Domain.Entities;

import com.jeyson.BooksRent.Domain.Entities.BookRent;
import com.jeyson.Core.Domain.Entities.BaseEntity;
import com.jeyson.Users.Domain.Constants.Security.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "deleted_at is NULL")
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity implements UserDetails {

    @Column
    private String name;

    @Column
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "document_number",unique = true, nullable = false)
    private String documentNumber;

    @Column
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userWhoRented")
    private Set<BookRent> booksRented;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userWhoDelivery")
    private Set<BookRent> booksDelivered;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = role.getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));

        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
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

    @Override
    public String toString() {
        return "UserEntity{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", address='" + address + '\'' +
                ", role=" + role +
                '}';
    }
}
