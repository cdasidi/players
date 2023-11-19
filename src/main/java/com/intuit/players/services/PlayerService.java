package com.intuit.players.services;

import com.intuit.players.models.Player;
import com.intuit.players.repositories.PlayerRepository;
import com.intuit.players.exceptions.PlayerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Page<Player> getAllPlayers(Pageable pageable) {
        return playerRepository.findAll(pageable);
    }

    public Player getPlayerById(String id) throws PlayerNotFoundException {
        log.debug("Fetching player with ID: {}", id);
        return playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException("Player not found with ID: " + id));
    }
}

