package com.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "date")
public class Tournament {

    @Id
    @GeneratedValue
    private int tournamentId;
    private LocalDateTime dateTime;
    private int price;
    private int rebuyPrice;
    private int entrances;
    private int rebuys;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_Id")
    @JsonIgnoreProperties("tournaments")
    private Location location;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id")
    @JsonIgnoreProperties("tournaments")
    private Manager manager;

    @ManyToMany(mappedBy = "tournaments", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Player> players;

    @ManyToMany(mappedBy = "tournaments", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Player> dealers;

    private HashMap<Integer, Integer> prizes;

    private HashMap<Integer, Player> playersInMoney;


    public void addDealer(Player dealer){
        this.dealers.add(dealer);
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

    public void removeDealer(Player dealer){
        this.dealers.remove(dealer);
    }

    public void removePlayer(Player player){
        this.players.remove(player);
    }

}
