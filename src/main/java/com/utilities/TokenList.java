package com.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
@AllArgsConstructor
public class TokenList {

    private List<Token> tokenList;

    public void TokensList() {
        tokensWork();

    }

    @Scheduled(fixedDelay = 60*1000)
    public void tokensWork(){
        Thread tokensWork = new Thread(() -> {
            while (true) {
                List<Token> existTokens = this.tokenList;
                if(!existTokens.isEmpty()) {
                    List<Token> tokensToRemove = existTokens.stream().filter(token -> token.getExpirationTime().before(Date.from(Instant.now()))).collect(Collectors.toList());
                    existTokens.removeAll(tokensToRemove);
                };
            }
        });
        tokensWork.start();
    }
    public void addToken(Token token){
        tokenList.add(token);
    }

    public boolean isThisTokenExist(String token){
        return tokenList.contains(getToken(token));
    }

    public Token getToken(String token){
        for (Token t: tokenList) {
            if(t.getToken().equals(token)){
                return t;
            }
        }
        return null;
    }
}
