package com.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Location {

    @Id
    @GeneratedValue
    private int locationId;

    private String LocationName;
    private String address;
    private Boolean parking;
    private String imgURL;

    @OneToMany(orphanRemoval = true, mappedBy = "location", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JsonIgnoreProperties("location")
    private List<Tournament> tournamentList;

}
