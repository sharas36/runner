package com.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.utilities.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Manager{

    @Id
    @GeneratedValue
    protected int id;
    protected String email;
    protected String firstName;
    protected String lastName;
    protected int age;
    protected String imgURL;
    protected String password;


    @OneToMany(mappedBy = "tournamentId", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JsonIgnoreProperties({"players", "dealers", "prizes", "inTheMoney", "manager"})
    private List<Tournament> tournaments;


}
