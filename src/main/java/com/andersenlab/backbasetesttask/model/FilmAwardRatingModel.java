package com.andersenlab.backbasetesttask.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "FILM_AWARD_RATING")
@NamedQueries({
        @NamedQuery(name = "getTopRated",
                query = "select f from FilmAwardRatingModel f order by f.rate desc nulls last")
})
public class FilmAwardRatingModel implements Comparable<FilmAwardRatingModel> {

    @Id
    @GeneratedValue(generator = "FILM_AWARD_RATING_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "FILM_AWARD_RATING_SEQ", sequenceName = "FILM_AWARD_RATING_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IMDB_FILM_MODEL_ID")
    private IMDbFilmModel film;

    @Column(name = "WIN")
    private boolean win;

    @Formula("(SELECT AVG(R.RATE) FROM FILM_RATING R WHERE R.FILM_ID = ID)")
    private BigDecimal rate;

    @Override
    public int compareTo(FilmAwardRatingModel o) {
        if (Objects.isNull(getFilm().getBoxOffice()) && Objects.isNull(o.getFilm().getBoxOffice())) {
            return Optional.ofNullable(getRate()).orElse(BigDecimal.ZERO)
                    .compareTo(Optional.ofNullable(o.getRate()).orElse(BigDecimal.ZERO));
        }
        if (Objects.isNull(getFilm().getBoxOffice())) {
            return -1;
        }
        if (Objects.isNull(o.getFilm().getBoxOffice())) {
            return 1;
        }
        return getFilm().getBoxOffice().compareTo(o.getFilm().getBoxOffice());
    }
}
