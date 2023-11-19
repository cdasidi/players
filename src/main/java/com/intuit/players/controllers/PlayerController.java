package com.intuit.players.controllers;

import com.intuit.players.models.Player;
import com.intuit.players.services.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/players")
@Slf4j
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<Page<Player>> getAllPlayers(Pageable pageable) {
        Page<Player> players = playerService.getAllPlayers(pageable);
        if(players.hasContent()) {
            return ResponseEntity.ok(players);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable String id) {
        log.debug("Received request to get player with ID: {}", id);
        Player player = playerService.getPlayerById(id);
        return ResponseEntity.ok(player);
    }
}
