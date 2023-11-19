package com.intuit.players.repositories;

import com.intuit.players.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String> {
}
