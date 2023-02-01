package com.controllers;

import com.entities.Manager;
import com.services.ManagerService;
import com.utilities.ClientType;
import com.utilities.MyException;
import com.utilities.Token;
import com.utilities.TokenList;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

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
    public String Login(String email, String password){
        managerService.loginCheck(email, password);
        Token token = new Token(email, password, ClientType.Manager);
        tokenList.addToken(token);
        return token.getToken();
    }

    public void addTournament(LocalDateTime dateTime, int price, int rebuyPrice, String locationName, Manager manager){
        managerService.addTournament(dateTime, locationName, price, rebuyPrice, manager);
    }

    public void deleteTournament(int tournamentId){
        managerService.deleteTournament(tournamentId);
    }

    public void addManager(String email, String password){
        managerService.addManager(email, password);
    }

    public void addDealer(String email, String password){
        managerService.addDealer(email, password);
    }

    public void addPlayer(String email, String password){
        managerService.addPlayer(email, password);
    }

    public void addPrize(String name, int price, String description){
        managerService.addPrize(name, price, description);
    }


}