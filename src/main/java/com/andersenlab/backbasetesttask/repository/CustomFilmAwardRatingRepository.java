package com.andersenlab.backbasetesttask.repository;

import com.andersenlab.backbasetesttask.model.FilmAwardRatingModel;

import java.util.List;

public interface CustomFilmAwardRatingRepository {

    List<FilmAwardRatingModel> getTopTenFilms();
}
