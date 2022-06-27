package com.andersenlab.backbasetesttask.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "FILM_RATING")
public class FilmRating {

    @Id
    @GeneratedValue(generator = "FILM_RATING_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "FILM_RATING_SEQ", sequenceName = "FILM_RATING_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FILM_ID")
    private Long filmId;

    @Column(name = "RATE")
    private Integer rate;
}
