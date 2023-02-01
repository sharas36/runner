package com.services;

import com.repositories.*;
import com.utilities.ClientType;
import com.utilities.MyException;
import com.utilities.Token;
import com.utilities.TokenList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class MainService {

    @Autowired
    protected DealerRepository dealerRepository;

    @Autowired
    protected ManagerRepository managerRepository;

    @Autowired
    protected PlayerRepository playerRepository;

    @Autowired
    protected PrizeRepository prizeRepository;

    @Autowired
    protected TournamentRepository tournamentRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected LocationRepository locationRepository;

    @Autowired
    protected TokenList tokensList;


    public String loginToken(String email,String password,ClientType clientType){
        Token token = new Token(email, password, clientType);
        System.out.println(token.getToken());
        tokensList.addToken(token);
        return token.getToken();
    }

    public void tokenCheck(String token, ClientType clientType) throws MyException {
        if(!tokensList.isThisTokenExist(token)){
            throw new MyException("your time is expired");
        }
        if(!tokensList.getToken(token).getClientType().equals(clientType)){
            throw new MyException("you cant get this page");
        }
    }

    public Integer getIdFromToken(String token){
        return Integer.valueOf(tokensList.getToken(token).getExpandedJwt().getBody().getId());
    }
}
