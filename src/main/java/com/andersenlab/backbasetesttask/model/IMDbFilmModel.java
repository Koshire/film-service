package com.andersenlab.backbasetesttask.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "IMDB_FILM_MODEL")
public class IMDbFilmModel {

    @Id
    @Column(name = "IMDB_ID")
    private String imdbId;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "FILM_YEAR")
    private String year;
    @Column(name = "BOX_OFFICE")
    private BigDecimal boxOffice;
    @Column(name = "POSTER")
    private String poster;
    @Column(name = "GENRE")
    private String genre;
    @Column(name = "PLOT")
    private String plot;
}