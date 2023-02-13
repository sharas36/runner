package com.controllers;

import com.entities.Manager;
import com.entities.Player;
import com.entities.Tournament;
import com.services.GuestService;
import com.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("")
public class GuestController {

    @Autowired
    private GuestService guestService;

    private final int pageSize = 10;

    @GetMapping("/allTournaments")
    public Page<Tournament> getAllTournament(@RequestBody int pageNum){
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "tournamentId"));
        return guestService.getAllTournament(pageable);
    }

    @GetMapping("/getNextTournaments")
    public Page<Tournament> getNextTournaments(@RequestBody int pageNum){
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "tournamentId"));
        return guestService.getNextTournaments(pageable);
    }

    @GetMapping("/getPreviousTournaments")
    public Page<Tournament> getPreviousTournaments(@RequestBody int pageNum){
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "tournamentId"));
        return guestService.getPreviousTournaments(pageable);
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
    public  Page<Object> getAllStaff(@RequestBody int pageNum){
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "lastName"));
        return guestService.getAllStaff(pageable);
    }
}
