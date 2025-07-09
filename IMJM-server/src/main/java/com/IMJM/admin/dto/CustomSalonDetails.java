package com.IMJM.admin.dto;

import com.IMJM.common.entity.Salon;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomSalonDetails implements UserDetails {

    private final Salon salon;

    public CustomSalonDetails(Salon salon) {
        this.salon = salon;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_ADMIN";
            }
        });

        return collection;
    }

    public Salon getSalon() {
        return salon;
    }

    @Override
    public String getPassword() {
        return salon.getPassword();
    }

    @Override
    public String getUsername() {
        return salon.getId();
    }

    public String getSalonId() {
        return salon.getId();
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
