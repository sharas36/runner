package com.controllers;

import com.entities.Manager;
import com.entities.Player;
import com.entities.Tournament;
import com.services.GuestService;
import com.services.ManagerService;
import com.utilities.MyException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("")
public class GuestController {

    @Autowired
    private GuestService guestService;

    private final int pageSize = 10;

    @SneakyThrows
    @GetMapping("/login")
    public String login(@RequestBody String email, @RequestBody String password){
        return guestService.login(email, password);
    }

    @SneakyThrows
    @GetMapping("/playerRegister")
    public void playerRegister(@RequestBody Player player){
        guestService.playerRegister(player);
    }

    @GetMapping("/allTournaments")
    public ResponseEntity<?> getAllTournament(@RequestBody int pageNum){
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "tournamentId"));
        Page<Tournament> tournaments= guestService.getAllTournament(pageable);
        return new ResponseEntity<>(tournaments, HttpStatus.OK);
    }

    @GetMapping("/getNextTournaments")
    public ResponseEntity<?> getNextTournaments(@RequestBody int pageNum){
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "tournamentId"));
        Page<Tournament> tournaments= guestService.getNextTournaments(pageable);
        return new ResponseEntity<>(tournaments, HttpStatus.OK);
    }

    @GetMapping("/getPreviousTournaments")
    public ResponseEntity<?> getPreviousTournaments(@RequestBody int pageNum){
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "tournamentId"));
        Page<Tournament> tournaments= guestService.getPreviousTournaments(pageable);
        return new ResponseEntity<>(tournaments, HttpStatus.OK);
    }

    @GetMapping("/tournament/{tournamentId}")
    public Tournament getTournament(@PathVariable int tournamentId){

        return guestService.getTournament(tournamentId);
    }

    @GetMapping("/player/{playerId}")
    public Player getOnePlayer(@PathVariable int playerId){
        return guestService.getOnePlayer(playerId);
    }

    @GetMapping("/manager/{managerId}")
    public Manager getOneManager(@PathVariable int managerId){
        return guestService.getOneManager(managerId);
    }

    @GetMapping("/staff")
    public  ResponseEntity<?> getAllStaff(@RequestBody int pageNum){
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "lastName"));
        Page<Object> staff = guestService.getAllStaff(pageable);
        return new ResponseEntity<>(staff, HttpStatus.OK);
    }
}
