package com.intuit.players.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
public class Player implements Serializable {
    @Id
    @NotBlank(message = "Player ID is mandatory")
    private String playerID;

    @Nullable
    @Min(value = 1700, message = "Birth year should be no earlier than 1700")
    private Integer birthYear;

    @Nullable
    @Min(value = 1, message = "Birth month should be valid")
    @Max(value = 12, message = "Birth month should be valid")
    private Integer birthMonth;

    @Nullable
    @Min(value = 1, message = "Birth day should be valid")
    @Max(value = 31, message = "Birth day should be valid")
    private Integer birthDay;

    private String birthCountry;

    private String birthState;

    private String birthCity;

    @Nullable
    @Min(value = 1700, message = "Death year should be no earlier than 1700")
    private Integer deathYear;

    @Nullable
    @Min(value = 1, message = "Death month should be valid")
    @Max(value = 12, message = "Death month should be valid")
    private Integer deathMonth;

    @Nullable
    @Min(value = 1, message = "Death day should be valid")
    @Max(value = 31, message = "Death day should be valid")
    private Integer deathDay;

    private String deathCountry;
    private String deathState;
    private String deathCity;
    private String nameFirst;
    private String nameLast;
    private String nameGiven;

    @Nullable
    @Positive(message = "Weight must be a positive number")
    private Integer weight;

    @Nullable
    @Positive(message = "Height must be a positive number")
    private Integer height;

//    @Nullable
//    @Pattern(regexp = "^[LRB]?$", message = "Bats should be R (Right) L (Left) or B(Both)")
    private String bats;

//    @Nullable
//    @Pattern(regexp = "^[LRB]?$", message = "throws should be R (Right) or L (Left) or B(Both)")
//    @Column(name = "throws")
    private String throwing;

    private LocalDate debut;
    private LocalDate finalGame;

    private String retroID;
    private String bbrefID;
}
