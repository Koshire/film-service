package com.andersenlab.backbasetesttask.controller;

import com.andersenlab.backbasetesttask.model.dto.FilmAwardRatingDto;
import com.andersenlab.backbasetesttask.model.dto.RateDto;
import com.andersenlab.backbasetesttask.service.FilmAwardRatingService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class FilmsController {

    public static final String TITLE_EXC_MESSAGE = "Title can't be null or empty";
    public static final String APIKEY_EXC_MESSAGE = "ApiKey can't be null or empty";

    private final FilmAwardRatingService service;

    @Operation(summary = "Find film by title in OMDBase, require apiKey (by OMDB api)")
    @GetMapping
    public FilmAwardRatingDto getFilm(@RequestParam("title")
                                       @NotBlank(message = TITLE_EXC_MESSAGE)
                                               String title,
                                       @Valid @RequestHeader(name = "apiKey")
                                       @NotBlank(message = APIKEY_EXC_MESSAGE)
                                               String apiKey) {
        return service.getFilm(title, apiKey);
    }

    @Operation(summary = "Rate film by id from \"find film by title in OMDBase\" request, rate from 1 to 10")
    @PostMapping("/rate")
    public FilmAwardRatingDto rateFilm(@Valid @RequestBody RateDto rateDto) {
        return service.rateFilm(rateDto.getFilmId(), rateDto.getRate());
    }

    @Operation(summary = "Get ten top rated films. Ordered by boxoffice value")
    @GetMapping("/top")
    public List<FilmAwardRatingDto> getTopTenRatedFilms() {
        return service.getTopTenFilms();
    }
}
