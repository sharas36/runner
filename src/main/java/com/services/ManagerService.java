package com.services;

import com.entities.*;
import com.utilities.ClientType;
import com.utilities.MyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ManagerService extends MainService{

    public void loginCheck(String email, String password) throws MyException {
        if(managerRepository.findByEmail(email).isEmpty()){
            throw new MyException("This email is not exist. Please try again");
        }
        Manager manager = managerRepository.getByEmail(email);
        if(!manager.getPassword().equals(password)){
            throw new MyException("Wrong Password");
        }
    }

    public void addManager(String email, String password){
        Manager manager = new Manager();
        manager.setEmail(email);
        manager.setPassword(password);
        managerRepository.save(manager);

    }

    public void deleteManager(int managerId){
        managerRepository.deleteById(managerId);
    }

    public void addDealer(String email, String password){
        Dealer dealer = new Dealer();
        dealer.setEmail(email);
        dealer.setPassword(password);
        dealerRepository.save(dealer);
    }

    public void deleteDealer(int dealerId){
        dealerRepository.deleteById(dealerId);
    }

    public void addPlayer(String email, String password){
        Player player = new Player();
        player.setEmail(email);
        player.setPassword(password);
        playerRepository.save(player);
    }

    public void deletePlayer(int playerId){
        playerRepository.deleteById(playerId);
    }

    public void addPrize(String name, int price, String description){
        Prize prize = Prize.builder().price(price).description(description).name(name).build();
        prizeRepository.save(prize);
    }

    public void deletePrize(int prizeId){
        prizeRepository.deleteById(prizeId);
    }

    public void addTournament(LocalDateTime dateTime, String locationName, int price, int rebuyPrice, Manager manager){
        Tournament tournament = Tournament.builder().manager(manager).dateTime(dateTime).location(locationRepository.findByLocationName(locationName)).price(price).
                rebuyPrice(rebuyPrice).build();
        tournamentRepository.save(tournament);
    }

    public void deleteTournament(int tournamentId){
        tournamentRepository.deleteById(tournamentId);
    }

    public void addPlayerToTournament(int playerId, int tournamentId) throws MyException {
        Tournament tournament = tournamentRepository.getById(tournamentId);
        Player player = playerRepository.getById(playerId);
        if(tournament.getPlayers().contains(player)){
            throw new MyException("This player is already registered");
        }
        tournament.addPlayer(player);
        tournamentRepository.save(tournament);
    }

    public void addDealerToTournament(int dealerId, int tournamentId) throws MyException {
        Tournament tournament = tournamentRepository.getById(tournamentId);
        Dealer dealer = dealerRepository.getById(dealerId);
        if(tournament.getDealers().contains(dealer)){
            throw new MyException("This dealer is already registered");
        }
        tournament.addDealer(dealer);
        tournamentRepository.save(tournament);
    }

    public void removePlayerFromTournament(int playerId, int tournamentId){
        Tournament tournament = tournamentRepository.getById(tournamentId);
        Player player = playerRepository.getById(playerId);
        tournament.removePlayer(player);
        tournamentRepository.save(tournament);
    }


    public void removeDealerFromTournament(int dealerId, int tournamentId){
        Tournament tournament = tournamentRepository.getById(tournamentId);
        Dealer dealer = dealerRepository.getById(dealerId);
        tournament.removeDealer(dealer);
        tournamentRepository.save(tournament);
    }

    public void addPrizeToTournament(Prize prize, Tournament tournament){
        tournament.addPrize(prize);
        tournamentRepository.save(tournament);
    }

    public void removePrizeFromTournament(Prize prize, Tournament tournament){
        tournament.removePrize(prize);
        tournamentRepository.save(tournament);
    }

    public Page<Tournament> getAllTournament(Pageable pageable){
        return tournamentRepository.findAll(pageable);
    }

    public Page<Tournament> getNextTournaments(Pageable pageable){
        return tournamentRepository.findByDateTimeAfter(LocalDateTime.now(), pageable);
    }

    public Page<Tournament> getPriviousTournaments(Pageable pageable){
        return tournamentRepository.findByDateTimeBefore(LocalDateTime.now(), pageable);
    }
}
