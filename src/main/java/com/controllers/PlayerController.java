package com.controllers;


import com.entities.Prize;
import com.entities.Tournament;
import com.services.PlayerService;
import com.utilities.ClientType;
import com.utilities.TokenList;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TokenList tokenList;

    private final int pageSize = 10;

    @SneakyThrows
    @GetMapping("/registerToTournament")
    public void registerToTournament(@RequestBody int tournamentId, @RequestBody String token) {
        playerService.tokenCheck(token, ClientType.Player);
        int playerId = playerService.getPlayerIdFromToken(token);
        playerService.registerToTournament(playerId, tournamentId);
    }

    @SneakyThrows
    @GetMapping("/unregisterFromTournament")
    public void unregisterFromTournament(@RequestBody int tournamentId, @RequestBody String token) {
        playerService.tokenCheck(token, ClientType.Player);
        int playerId = playerService.getPlayerIdFromToken(token);
        playerService.unregisterFromTournament(playerId, tournamentId);
    }

    @SneakyThrows
    @GetMapping("/buyPrize")
    public void buyPrize(@RequestBody int prizeId, @RequestBody String token) {
        playerService.tokenCheck(token, ClientType.Player);
        int playerId = playerService.getPlayerIdFromToken(token);
        playerService.buyPrize(playerId, prizeId);
    }

    @SneakyThrows
    @GetMapping("/getAllPrizes")
    public ResponseEntity<?> getAllPrizes(@RequestBody int pageNum, @RequestBody String token) {
        playerService.tokenCheck(token, ClientType.Player);
        int playerId = playerService.getPlayerIdFromToken(token);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Prize> prizes = playerService.getAllPrizes(playerId, pageable);
        return new ResponseEntity<>(prizes, HttpStatus.OK);
    }

    @SneakyThrows
    @GetMapping("/allPlayersTournaments")
    public ResponseEntity<?> getAllPlayersTournaments(@RequestBody int pageNum, @RequestBody String token){
        playerService.tokenCheck(token, ClientType.Player);
        int playerId = playerService.getPlayerIdFromToken(token);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Tournament> tournaments = playerService.getPlayersTournaments(playerId, pageable);
        return new ResponseEntity<>(tournaments, HttpStatus.OK);
    }
}
