package com.services;

import com.entities.Manager;
import com.entities.Player;
import com.entities.Tournament;
import com.utilities.ClientType;
import com.utilities.MyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GuestService extends MainService{

    public String login(String email, String password) throws MyException {
        if(managerRepository.findByEmail(email).isEmpty() && playerRepository.findByEmail(email).isEmpty()){
            throw new MyException("This email is not exist. Please try again");
        }
        else if(!managerRepository.findByEmail(email).isEmpty()){
            Manager manager = managerRepository.getByEmail(email);
            if(!manager.getPassword().equals(password)){
                throw new MyException("Wrong Password");
            }
            return loginToken(email, password, ClientType.Manager);
        }
        else{
            Player player = playerRepository.getByEmail(email);
            if(!player.getPassword().equals(password))
                throw new MyException("Wrong Password");
            return loginToken(email, password, ClientType.Player);
        }
    }

    public void playerRegister(Player player) throws MyException {
        if(!playerRepository.findByEmail(player.getEmail()).isEmpty()){
            throw new MyException("This email is already exist");
        }
        playerRepository.save(player);
    }

    public Page<Tournament> getAllTournament(Pageable pageable){
        return tournamentRepository.findAll(pageable);
    }

    public Page<Tournament> getNextTournaments(Pageable pageable){
        return tournamentRepository.findByDateTimeAfter(LocalDateTime.now(), pageable);
    }

    public Page<Tournament> getPreviousTournaments(Pageable pageable){
        return tournamentRepository.findByDateTimeBefore(LocalDateTime.now(), pageable);
    }

    public Tournament getTournament(int tournamentId){
        return tournamentRepository.getById(tournamentId);
    }

    public Player getOnePlayer(int playerId){
        return playerRepository.getById(playerId);
    }

    public Page<Player> getAllPlayers(Pageable pageable){
        return playerRepository.findAll(pageable);
    }

    public Manager getOneManager(int managerId){
        return managerRepository.getById(managerId);
    }

    public Page<Manager> getAllManagers(Pageable pageable){
        return managerRepository.findAll(pageable);
    }

    public Page<Object> getAllStaff(Pageable pageable){
        List<Manager> managers = (List<Manager>) managerRepository.findAll();
        List<Player> dealers = playerRepository.getAllDealers();
        List<Object> allStaff = new ArrayList<>();
        allStaff.add(managers);
        allStaff.add(dealers);
        return new PageImpl<Object>(allStaff, pageable, pageable.getPageSize());
    }
}
