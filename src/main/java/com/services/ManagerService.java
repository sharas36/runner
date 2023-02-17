package com.services;

import com.entities.*;
import com.utilities.MyException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;

@Service
public class ManagerService extends MainService{


    public void addManager(String email, String password) throws MyException {

        if(!managerRepository.findByEmail(email).isEmpty()){
            throw new MyException("this email is already exist");
        }
        Manager manager = new Manager();
        manager.setEmail(email);
        manager.setPassword(password);
        managerRepository.save(manager);

    }

    public void deleteManager(int managerId){
        managerRepository.deleteManager(managerId);
    }

    public void updateManager(Manager manager){
        managerRepository.save(manager);
    }

    public void addDealer(String email, String password) throws MyException {
        if(!playerRepository.findByEmail(email).isEmpty()){
            throw new MyException("this email is already exist");
        }
        Player dealer = new Player();
        dealer.setEmail(email);
        dealer.setPassword(password);
        dealer.setIsDealer(true);
        playerRepository.save(dealer);
    }

    public void deleteDealer(int dealerId){
        playerRepository.deletePlayer(dealerId);
    }

    public void addPlayer(String email, String password) throws MyException {
        if(!playerRepository.findByEmail(email).isEmpty()){
            throw new MyException("this email is already exist");
        }
        Player player = new Player();
        player.setEmail(email);
        player.setPassword(password);
        playerRepository.save(player);
    }

    public void deletePlayer(int playerId){
        playerRepository.deletePlayer(playerId);
    }

    public void updatePlayer(Player player){
        playerRepository.save(player);
    }

    public void addPrize(String name, int price, String description){
        Prize prize = Prize.builder().price(price).description(description).name(name).build();
        prizeRepository.save(prize);
    }

    public void deletePrize(int prizeId){
        prizeRepository.deleteById(prizeId);
    }

    public void updatePrize(Prize prize){
        prizeRepository.save(prize);
    }

    public void addTournament(LocalDateTime dateTime, String locationName, int price, int rebuyPrice){
        Tournament tournament = Tournament.builder().dateTime(dateTime).location(locationRepository.findByLocationName(locationName)).price(price).
                rebuyPrice(rebuyPrice).build();
        tournamentRepository.save(tournament);
    }

    public void deleteTournament(int tournamentId){
        tournamentRepository.deleteById(tournamentId);
    }

    public void updateTournament(Tournament tournament){
        tournamentRepository.save(tournament);
    }

    public void addPlayerToTournament(int playerId, int tournamentId) throws MyException {
        Tournament tournament = tournamentRepository.getById(tournamentId);
        Player player = playerRepository.getById(playerId);
        if(tournament.getPlayers().contains(player)){
            throw new MyException("This player is already registered");
        }
        tournament.addPlayer(player);
        player.addCredit(-tournament.getPrice());
        playerRepository.save(player);
        tournamentRepository.save(tournament);
    }

    public void addDealerToTournament(int dealerId, int tournamentId) throws MyException {
        Tournament tournament = tournamentRepository.getById(tournamentId);
        Player dealer = playerRepository.getById(dealerId);
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
        Player dealer = playerRepository.getById(dealerId);
        tournament.removeDealer(dealer);
        tournamentRepository.save(tournament);
    }

    public void addPrizeToTournament(int tournamentId, int rank, int prize){
        Tournament tournament = tournamentRepository.getById(tournamentId);
        HashMap<Integer, Integer> prizes = tournament.getPrizes();
        prizes.put(rank, prize);
        tournamentRepository.save(tournament);
    }

    public void removePrizeFromTournament(int tournamentId, int rank){
        Tournament tournament = tournamentRepository.getById(tournamentId);
        HashMap<Integer, Integer> prizes = tournament.getPrizes();
        prizes.remove(rank);
        tournamentRepository.save(tournament);
    }

    public void addPlayerToPlayersInMoney(int tournamentId, Player player, int rank){
        Tournament tournament = tournamentRepository.getById(tournamentId);
        HashMap<Integer, Player> playerHashMap = tournament.getPlayersInMoney();
        playerHashMap.put(rank, player);
        tournamentRepository.save(tournament);
    }

    public void removePlayerFromPlayersInMoney(int tournamentId, int rank){
        Tournament tournament = tournamentRepository.getById(tournamentId);
        HashMap<Integer, Player> playerHashMap = tournament.getPlayersInMoney();
        playerHashMap.remove(rank);
        tournamentRepository.save(tournament);
    }

    public void closeTournament(int tournamentId){
        Tournament tournament = tournamentRepository.getById(tournamentId);
        HashMap<Integer, Integer> prizes = tournament.getPrizes();
        HashMap<Integer, Player> playerHashMap = tournament.getPlayersInMoney();

        prizes.forEach((rank, prize) -> {
            playerHashMap.get(rank).addCredit(prize);
        });
    }


}
