package com.andersenlab.backbasetesttask.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;
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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "FILE_FILM_MODEL")
@Indexed(index = "FileFilmModel")
public class FileFilmModel {

    @Id
    @GeneratedValue(generator = "FILE_FILM_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "FILE_FILM_SEQ", sequenceName = "FILE_FILM_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FILM_YEAR")
    @Field(name = "filmYear")
    private String filmYear;

    @Column(name = "DESCRIPTION")
    @Field(name = "description")
    private String description;

    @Column(name = "WIN")
    private boolean isWin;
}
