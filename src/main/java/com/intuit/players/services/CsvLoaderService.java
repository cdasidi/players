package com.intuit.players.services;

import com.intuit.players.models.Player;
import com.intuit.players.repositories.PlayerRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class CsvLoaderService implements CommandLineRunner {

    private final PlayerRepository playerRepository;

    @Autowired
    public CsvLoaderService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (!(playerRepository.count() == 0)) {
            return;
        }
        String filePath = "src/main/resources/data/player.csv";
        Reader reader = Files.newBufferedReader(Paths.get(filePath));
        CSVParser csvParser = new CSVParser(reader,
                CSVFormat.DEFAULT.builder()
                        .setTrim(true)
                        .setIgnoreHeaderCase(true)
                        .setHeader().setSkipHeaderRecord(true).build());

        for (CSVRecord csvRecord : csvParser) {
            Player player = new Player();
            player.setPlayerID(csvRecord.get("playerID"));
            if (!csvRecord.get("birthYear").isEmpty()) {
                player.setBirthYear(Integer.parseInt(csvRecord.get("birthYear")));
            }
            if (!csvRecord.get("birthMonth").isEmpty()) {
                player.setBirthMonth(Integer.parseInt(csvRecord.get("birthMonth")));
            }
            if (!csvRecord.get("birthDay").isEmpty()) {
                player.setBirthDay(Integer.parseInt(csvRecord.get("birthDay")));
            }

            player.setBirthCountry(csvRecord.get("birthCountry"));
            player.setBirthState(csvRecord.get("birthState"));
            player.setBirthCity(csvRecord.get("birthCity"));
            String deathYear = csvRecord.get("deathYear");
            if (deathYear != null && !deathYear.isEmpty()) {
                player.setDeathYear(Integer.parseInt(deathYear));
                if (!csvRecord.get("deathMonth").isEmpty()) {
                    player.setDeathMonth(Integer.parseInt(csvRecord.get("deathMonth")));
                }
                if (!csvRecord.get("deathDay").isEmpty()) {
                    player.setDeathDay(Integer.parseInt(csvRecord.get("deathDay")));
                }
                if (!csvRecord.get("deathCountry").isEmpty()) {
                    player.setDeathCountry(csvRecord.get("deathCountry"));
                }
                if (!csvRecord.get("deathState").isEmpty()) {
                    player.setDeathState(csvRecord.get("deathState"));
                }
                if (!csvRecord.get("deathCity").isEmpty()) {
                    player.setDeathCity(csvRecord.get("deathCity"));
                }
            }
            player.setNameFirst(csvRecord.get("nameFirst"));
            player.setNameLast(csvRecord.get("nameLast"));
            player.setNameGiven(csvRecord.get("nameGiven"));
            if (!csvRecord.get("weight").isEmpty()) {
                player.setWeight(Integer.parseInt(csvRecord.get("weight")));
            }
            if (!csvRecord.get("height").isEmpty()) {
                player.setHeight(Integer.parseInt(csvRecord.get("height")));
            }
            if (!csvRecord.get("bats").isEmpty()) {
                player.setBats(csvRecord.get("bats"));
            }
            if (!csvRecord.get("throws").isEmpty()) {
                player.setThrowing(csvRecord.get("throws"));
            }
            if (!csvRecord.get("debut").isEmpty()) {
                player.setDebut(LocalDate.parse(csvRecord.get("debut"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
            if (!csvRecord.get("finalGame").isEmpty()) {
                player.setFinalGame(LocalDate.parse(csvRecord.get("finalGame"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
            player.setRetroID(csvRecord.get("retroID"));
            player.setBbrefID(csvRecord.get("bbrefID"));

            playerRepository.save(player);
        }

        csvParser.close();
        reader.close();
    }
}

