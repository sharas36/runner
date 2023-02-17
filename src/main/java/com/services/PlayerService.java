package com.services;

import com.entities.Player;
import com.entities.Prize;
import com.entities.Tournament;
import com.utilities.MyException;
import com.utilities.Token;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService extends MainService{






    public void registerToTournament(int playerId, int tournamentId) throws MyException {
        Tournament tournament = tournamentRepository.getById(tournamentId);
        Player player = playerRepository.getById(playerId);
        if(tournament.getPlayers().contains(player)){
            throw new MyException("You're already registered");
        }
        tournament.addPlayer(player);
        player.addCredit(-tournament.getPrice());
        playerRepository.save(player);
        tournamentRepository.save(tournament);
    }

    public void unregisterFromTournament(int playerId, int tournamentId){
        Tournament tournament = tournamentRepository.getById(tournamentId);
        Player player = playerRepository.getById(playerId);
        tournament.removePlayer(player);
        player.addCredit(tournament.getPrice());
        playerRepository.save(player);
        tournamentRepository.save(tournament);
    }

    public void buyPrize(int playerId, int prizeId) throws MyException {
        Prize prize = prizeRepository.getById(prizeId);
        Player player = playerRepository.getById(playerId);
        if(prize.getPrice() > player.getCredit()){
            throw new MyException("You dont have enough credit");
        }
        if(!prize.isAvailable()){
            throw new MyException("This prize isnt available now");
        }
        player.addPrize(prize);
        playerRepository.save(player);
    }

    public Page<Prize> getAllPrizes(int playerId, Pageable pageable){
        return prizeRepository.getPrizesByPlayer(playerId, pageable);
    }

    public Page<Tournament> getPlayersTournaments(int playerId, Pageable pageable){
        return tournamentRepository.getTournamentsByPlayerId(playerId, pageable);
    }

    public Integer getPlayerIdFromToken(String token){
        Token token1 = tokensList.getToken(token);
        String email = token1.getExpandedJwt().getBody().getId();
        return playerRepository.getByEmail(email).getId();
    }
}
