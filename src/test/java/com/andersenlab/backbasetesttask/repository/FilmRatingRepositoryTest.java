package com.andersenlab.backbasetesttask.repository;

import com.andersenlab.backbasetesttask.model.FilmRating;
import com.andersenlab.backbasetesttask.model.dto.FilmAwardRatingDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FilmRatingRepositoryTest {

    public static final Long TEST_ID = 7L;
    public static final int TEST_RATE = 10;

    @Autowired
    private FilmRatingRepository repository;

    @Test
    @Transactional(readOnly = true)
    @Rollback
    void checkRateFilm() {
        FilmRating filmRate = repository.saveAndFlush(new FilmRating(null, TEST_ID, TEST_RATE));
        Assertions.assertNotNull(filmRate.getId());
    }
}