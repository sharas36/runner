package com.services;

import com.entities.Player;
import com.entities.Prize;
import com.entities.Tournament;
import com.utilities.MyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService extends MainService{

    private int playerId = 1;
    private Player player = playerRepository.getById(playerId);

    public void register(Player player){
        playerRepository.save(player);
    }

    public void registerToTournament(int tournamentId) throws MyException {
        Tournament tournament = tournamentRepository.getById(tournamentId);
        if(tournament.getPlayers().contains(player)){
            throw new MyException("You're already registered");
        }
        tournament.addPlayer(player);
        player.addCredit(-tournament.getPrice());
        playerRepository.save(player);
        tournamentRepository.save(tournament);
    }

    public void unregisterFromTournament(int tournamentId){
        Tournament tournament = tournamentRepository.getById(tournamentId);
        tournament.removePlayer(player);
        player.addCredit(tournament.getPrice());
        playerRepository.save(player);
        tournamentRepository.save(tournament);
    }

    public void buyPrize(int prizeId) throws MyException {
        Prize prize = prizeRepository.getById(prizeId);
        if(prize.getPrice() > player.getCredit()){
            throw new MyException("You dont have enough credit");
        }
        player.addPrize(prize);
        playerRepository.save(player);
    }

    public Page<Prize> getAllPrizes(Pageable pageable){
        return prizeRepository.getPrizesByPlayer(playerId, pageable);
    }

    public Page<Tournament> getPlayersTournaments(Pageable pageable){
        return tournamentRepository.getTournamentsByPlayerId(playerId, pageable);
    }

    public void setPlayerId(int playerId){
        this.playerId = playerId;
    }
}
