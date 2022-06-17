package com.manhnguyen.demoredis.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "account")
@Data
public class Account implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "password")
    private String password;

    @Column(name = "provider")
    private String provider;

//    @Column(name = "providerKey")
//    private String providerKey;

//    @Column(name = "tokenResetPassword")
//    private String tokenResetPassword;

    @Column(name = "type")
    private Long type;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "username")
    private String username;


}
