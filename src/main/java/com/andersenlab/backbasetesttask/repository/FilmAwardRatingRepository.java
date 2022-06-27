package com.andersenlab.backbasetesttask.repository;

import com.andersenlab.backbasetesttask.model.FilmAwardRatingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmAwardRatingRepository
        extends JpaRepository<FilmAwardRatingModel, Long>, CustomFilmAwardRatingRepository {

    Optional<FilmAwardRatingModel> getFilmAwardRatingModelByFilm_ImdbId(String id);
}
