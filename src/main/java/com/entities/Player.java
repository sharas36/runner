package com.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.utilities.ClientType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Player implements Comparable<Player> {


    @Id
    @GeneratedValue
    protected int id;
    protected String email;
    protected String firstName;
    protected String lastName;
    protected int age;
    protected String imgURL;
    protected String password;
    private int points;
    private int ranking;

    private int credit;

    @ManyToMany(mappedBy = "playersThatBuy", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Prize> prizes;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Tournament> tournaments;

    public void addPrize(Prize prize){
        this.prizes.add(prize);
        this.credit -= prize.getPrice();
    }


    @Override
    public int compareTo(Player otherPlayer) {
        return Integer.compare(this.getPoints(), otherPlayer.getPoints());
    }
}
