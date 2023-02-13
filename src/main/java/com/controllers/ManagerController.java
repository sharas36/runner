package com.controllers;

import com.entities.Manager;
import com.entities.Player;
import com.entities.Prize;
import com.entities.Tournament;
import com.services.ManagerService;
import com.utilities.ClientType;
import com.utilities.MyException;
import com.utilities.Token;
import com.utilities.TokenList;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/admin")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private TokenList tokenList;

    @SneakyThrows
    @PostMapping("/loginManager")
    public String Login(@RequestBody String email,@RequestBody String password){
        managerService.loginCheck(email, password);
        Token token = new Token(email, password, ClientType.Manager);
        tokenList.addToken(token);
        return token.getToken();
    }

    @SneakyThrows
    @PostMapping("/addTournament")
    public void addTournament(@RequestBody LocalDateTime dateTime,@RequestBody int price,@RequestBody int rebuyPrice,
                              @RequestBody String locationName, @RequestBody String token){
        managerService.tokenCheck(token, ClientType.Manager);
        managerService.addTournament(dateTime, locationName, price, rebuyPrice);
    }

    @SneakyThrows
    @DeleteMapping("/deleteTournament/{tournamentId}")
    public void deleteTournament(@PathVariable int tournamentId, @RequestBody String token){
        managerService.tokenCheck(token, ClientType.Manager);
        managerService.deleteTournament(tournamentId);
    }

    @SneakyThrows
    @PostMapping("/updateTournament")
    public void updateTournament(@RequestBody Tournament tournament, @RequestBody String token){
        managerService.tokenCheck(token, ClientType.Manager);
        managerService.updateTournament(tournament);
    }

    @SneakyThrows
    @PostMapping("/addManager")
    public void addManager(@RequestBody String email,@RequestBody String password, @RequestBody String token){
        managerService.tokenCheck(token, ClientType.Manager);
        managerService.addManager(email, password);
    }

    @SneakyThrows
    @DeleteMapping("/deleteManager/{managerId}")
    public void deleteManager(@PathVariable int managerId, @RequestBody String token){
        managerService.tokenCheck(token, ClientType.Manager);
        managerService.deleteManager(managerId);
    }

    @SneakyThrows
    @PostMapping("/updateManager")
    public void updateManager(@RequestBody Manager manager, @RequestBody String token){
        managerService.tokenCheck(token, ClientType.Manager);
        managerService.updateManager(manager);
    }

    @SneakyThrows
    @PostMapping("/addDealer")
    public void addDealer(@RequestBody String email,@RequestBody String password, @RequestBody String token){
        managerService.tokenCheck(token, ClientType.Manager);
        managerService.addDealer(email, password);
    }

    @SneakyThrows
    @PostMapping("/addPlayer")
    public void addPlayer(@RequestBody String email,@RequestBody String password, @RequestBody String token){
        managerService.tokenCheck(token, ClientType.Manager);
        managerService.addPlayer(email, password);
    }

    @SneakyThrows
    @DeleteMapping("/deletePlayer/{playerId}")
    public void deletePlayer(@PathVariable int playerId, @RequestBody String token){
        managerService.tokenCheck(token, ClientType.Manager);
        managerService.deletePlayer(playerId);
    }

    @SneakyThrows
    @PostMapping("/updatePlayer")
    public void updatePlayer(@RequestBody Player player, @RequestBody String token){
        managerService.tokenCheck(token, ClientType.Manager);
        managerService.updatePlayer(player);
    }

    @SneakyThrows
    @PostMapping("/addPrize")
    public void addPrize(@RequestBody String name,@RequestBody int price,@RequestBody String description, @RequestBody String token){
        managerService.tokenCheck(token, ClientType.Manager);
        managerService.addPrize(name, price, description);
    }

    @SneakyThrows
    @DeleteMapping("/deletePrize/{prizeId}")
    public void deletePrize(@PathVariable int prizeId, @RequestBody String token){
        managerService.tokenCheck(token, ClientType.Manager);
        managerService.deletePrize(prizeId);
    }

    @SneakyThrows
    @PostMapping("/updatePrize")
    public void updatePrize(@RequestBody Prize prize, @RequestBody String token){
        managerService.tokenCheck(token, ClientType.Manager);
        managerService.updatePrize(prize);
    }

    @SneakyThrows
    @PostMapping("/registerToTournament")
    public void addPlayerToTournament(@RequestBody int playerId,@RequestBody int tournamentId, @RequestBody String token){
        managerService.tokenCheck(token, ClientType.Manager);
        managerService.addPlayerToTournament(playerId, tournamentId);
    }

    @SneakyThrows
    @PostMapping("/addDealerToTournament")
    public void addDealerToTournament(@RequestBody int dealerId,@RequestBody int tournamentId, @RequestBody String token){
        managerService.tokenCheck(token, ClientType.Manager);
        managerService.addDealerToTournament(dealerId, tournamentId);
    }

    @SneakyThrows
    @PostMapping("/unregisterFromTournament")
    public void removePlayerFromTournament(@RequestBody int playerId,@RequestBody int tournamentId, @RequestBody String token){
        managerService.tokenCheck(token, ClientType.Manager);
        managerService.removePlayerFromTournament(playerId, tournamentId);
    }

    @SneakyThrows
    @PostMapping("/removeDealerFromTournament")
    public void removeDealerFromTournament(@RequestBody int dealerId,@RequestBody int tournamentId, @RequestBody String token){
        managerService.tokenCheck(token, ClientType.Manager);
        managerService.removeDealerFromTournament(dealerId, tournamentId);
    }

    @SneakyThrows
    @PostMapping("/addPrizeToTournament")
    public void addPrizeToTournament(@RequestBody int tournamentId,@RequestBody int rank,@RequestBody int prize, @RequestBody String token){
        managerService.tokenCheck(token, ClientType.Manager);
        managerService.addPrizeToTournament(tournamentId, rank, prize);
    }

    @SneakyThrows
    @PostMapping("/removePrizeFromTournament")
    public void removePrizeFromTournament(@RequestBody int tournamentId,@RequestBody int rank, @RequestBody String token){
        managerService.tokenCheck(token, ClientType.Manager);
        managerService.removePrizeFromTournament(tournamentId, rank);
    }

    @SneakyThrows
    @PostMapping("/addPlayerToPlayersInMoney")
    public void addPlayerToPlayersInMoney(@RequestBody int tournamentId,@RequestBody Player player,@RequestBody int rank, @RequestBody String token){
        managerService.tokenCheck(token, ClientType.Manager);
        managerService.addPlayerToPlayersInMoney(tournamentId, player, rank);
    }

    @SneakyThrows
    @PostMapping("/removePlayerFromPlayersInMoney")
    public void removePlayerFromPlayersInMoney(@RequestBody int tournamentId,@RequestBody int rank, @RequestBody String token){
        managerService.tokenCheck(token, ClientType.Manager);
        managerService.removePlayerFromPlayersInMoney(tournamentId, rank);
    }

    @SneakyThrows
    @PostMapping("/closeTournament")
    public void closeTournament(@RequestBody int tournamentId, @RequestBody String token){
        managerService.tokenCheck(token, ClientType.Manager);
        managerService.closeTournament(tournamentId);
    }
}