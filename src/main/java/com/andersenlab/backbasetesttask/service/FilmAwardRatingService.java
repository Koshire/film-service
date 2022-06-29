package com.andersenlab.backbasetesttask.service;

import com.andersenlab.backbasetesttask.model.dto.FilmAwardRatingDto;
import com.andersenlab.backbasetesttask.repository.FullTextFilmFileRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FilmAwardRatingService {

    /**
     * Find a film in OMDB API by title with apikey by OMDB API.
     * Method saves the film in the DB with the award value from the file.
     *
     * @param title - Film title (is used for search in the OMDB API)
     * @param apiKey - Api key by OMDB API
     * @return - return dto with the award and the calculated rating value,
     * includes the film dto from OMDB API {@link FilmAwardRatingDto}
     */
    FilmAwardRatingDto getFilm(String title, String apiKey);

    /**
     *
     * @param id - Film id from {@link FilmAwardRatingDto::id}
     * @param rate - Film rating value from 1 to 10 (int)
     * @return - return dto with the award and the calculated rating value,
     * includes film dto from OMDB API {@link FilmAwardRatingDto}
     */
    FilmAwardRatingDto rateFilm(Long id, int rate);

    /**
     * Method returns top ten films sorted by box office value. Films without
     * box office value are displayed at the end of the list and are sorted by rating.
     *
     * @return - List of dto with the award and the calculated rating value,
     * includes film dto from OMDB API {@link FilmAwardRatingDto}
     */
    List<FilmAwardRatingDto> getTopTenFilms();
}
