package com.andersenlab.backbasetesttask.repository.impl;

import com.andersenlab.backbasetesttask.model.FilmAwardRatingModel;
import com.andersenlab.backbasetesttask.repository.CustomFilmAwardRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CustomFilmAwardRatingRepositoryImpl implements CustomFilmAwardRatingRepository {

    public static final int TOP_COUNTER = 10;
    private final EntityManager em;

    @Override
    public List<FilmAwardRatingModel> getTopTenFilms() {
        return em.createNamedQuery("getTopRated", FilmAwardRatingModel.class)
                .setMaxResults(TOP_COUNTER)
                .getResultList()
                .stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
}
