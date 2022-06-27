package com.andersenlab.backbasetesttask.service.impl;

import com.andersenlab.backbasetesttask.config.error.NotFoundException;
import com.andersenlab.backbasetesttask.mapper.FilmAwardRatingMapper;
import com.andersenlab.backbasetesttask.model.FileFilmModel;
import com.andersenlab.backbasetesttask.model.FilmAwardRatingModel;
import com.andersenlab.backbasetesttask.model.FilmRating;
import com.andersenlab.backbasetesttask.model.IMDbFilmModel;
import com.andersenlab.backbasetesttask.model.dto.FilmAwardRatingDto;
import com.andersenlab.backbasetesttask.repository.FilmAwardRatingRepository;
import com.andersenlab.backbasetesttask.repository.FilmRatingRepository;
import com.andersenlab.backbasetesttask.service.FilmAwardRatingService;
import com.andersenlab.backbasetesttask.service.FilmFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilmAwardRatingServiceImpl implements FilmAwardRatingService {

    private final FilmAwardRatingRepository repository;
    private final FilmRatingRepository ratingRepository;
    private final FilmFileService filmFileService;
    private final IMDbFilmServiceImpl imDbFilmService;
    private final FilmAwardRatingMapper mapper;

    @Override
    @Transactional
    public FilmAwardRatingDto getFilm(String title, String apiKey) {
        IMDbFilmModel imdb = Optional.ofNullable(imDbFilmService.findFilm(apiKey, title))
                .orElseThrow(() -> new NotFoundException("Film not found."));
        return mapper.mapToDto(repository.getFilmAwardRatingModelByFilm_ImdbId(imdb.getImdbId())
                .orElseGet(() -> {
                    FilmAwardRatingModel filmAwardRatingModel = new FilmAwardRatingModel(null, imdb,
                            filmFileService.getByNameAndYear(imdb.getTitle(), imdb.getYear())
                                    .stream()
                                    .findFirst()
                                    .map(FileFilmModel::isWin)
                                    .orElse(false), BigDecimal.ZERO);
                    return repository.save(filmAwardRatingModel);
                }));
    }

    @Override
    @Transactional
    public FilmAwardRatingDto rateFilm(Long id, int rate) {
        ratingRepository.saveAndFlush(new FilmRating(null, id, rate));
        return getById(id);
    }

    @Override
    @Transactional
    public List<FilmAwardRatingDto> getTopTenFilms() {
        return repository.getTopTenFilms()
                .stream().map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    private FilmAwardRatingDto getById(Long id) {
        return mapper.mapToDto(repository.findById(id).orElseThrow(() ->
                new NotFoundException("Film with id: " + id + " not found")));
    }
}
