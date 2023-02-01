package com.entities;

import com.utilities.ClientType;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    protected int id;
    protected String email;
    protected String firstName;
    protected String lastName;
    protected int age;
    protected String imgURL;
    protected String password;
    protected ClientType clientType;
}
