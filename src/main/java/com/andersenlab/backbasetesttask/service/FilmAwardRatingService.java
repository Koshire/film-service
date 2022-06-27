package com.andersenlab.backbasetesttask.service;

import com.andersenlab.backbasetesttask.model.dto.FilmAwardRatingDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FilmAwardRatingService {

    FilmAwardRatingDto getFilm(String title, String apiKey);

    FilmAwardRatingDto rateFilm(Long id, int rate);

    List<FilmAwardRatingDto> getTopTenFilms();
}
