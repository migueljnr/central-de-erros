package com.projetofinal.data;

import io.swagger.annotations.ApiModelProperty;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "authority")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    @ApiModelProperty(hidden = true)
    UserRoleName userRoleName;

    public Authority() {

    }

    public Authority(UserRoleName userRoleName) {
        this.userRoleName = userRoleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserRoleName getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(UserRoleName userRoleName) {
        this.userRoleName = userRoleName;
    }

    @Override
    public String getAuthority() {
        return userRoleName.name();
    }
}
