package com.andersenlab.backbasetesttask.repository;

import com.andersenlab.backbasetesttask.model.FilmRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRatingRepository extends JpaRepository<FilmRating, Long> {

}
