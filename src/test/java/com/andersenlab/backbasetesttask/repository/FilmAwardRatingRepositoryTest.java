package com.andersenlab.backbasetesttask.repository;

import com.andersenlab.backbasetesttask.model.FilmAwardRatingModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FilmAwardRatingRepositoryTest {

    public static final String IMDB_TEST_ID = "tt0018578";

    @Autowired
    private FilmAwardRatingRepository repository;

    @Test
    void checkGetFilmAwardRatingModelByFilm_ImdbId() {
        Optional<FilmAwardRatingModel> film = repository.getFilmAwardRatingModelByFilm_ImdbId(IMDB_TEST_ID);
        Assertions.assertAll(() -> {
            Assertions.assertTrue(film.isPresent());
            Assertions.assertEquals(IMDB_TEST_ID, film.get().getFilm().getImdbId());
        });
    }

    @Test
    @Transactional(readOnly = true)
    void checkGetTopTenFilms() {
        List<FilmAwardRatingModel> topTenFilms = repository.getTopTenFilms();

        List<FilmAwardRatingModel> collect = topTenFilms.stream()
                .filter(f -> Optional.ofNullable(f.getFilm().getBoxOffice()).isPresent())
                .sorted((o1, o2) -> o2.getFilm().getBoxOffice().compareTo(o1.getFilm().getBoxOffice()))
                .collect(Collectors.toList());

        collect.addAll(topTenFilms.stream()
                .filter(f -> Optional.ofNullable(f.getFilm().getBoxOffice()).isEmpty())
                .sorted((o1, o2) -> Optional.ofNullable(o2.getRate()).orElse(BigDecimal.ZERO)
                        .compareTo(Optional.ofNullable(o1.getRate()).orElse(BigDecimal.ZERO)))
                .collect(Collectors.toList()));

        List<Long> ids = collect.stream().map(FilmAwardRatingModel::getId).collect(Collectors.toList());
        Assertions.assertAll(() -> {
            assertEquals(10, topTenFilms.size());
            Assertions.assertTrue(ids.containsAll(topTenFilms.stream()
                    .map(FilmAwardRatingModel::getId).collect(Collectors.toList())));
        });
    }
}