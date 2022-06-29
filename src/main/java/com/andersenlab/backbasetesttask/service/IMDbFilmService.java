package com.andersenlab.backbasetesttask.service;

import com.andersenlab.backbasetesttask.config.error.NotFoundException;
import com.andersenlab.backbasetesttask.model.IMDbFilmModel;

public interface IMDbFilmService {

    /**
     * Find film in OMDB API.
     *
     * @param apiKey - Apikey by OMDB API
     * @param title - Film title
     * @return - {@link IMDbFilmModel} with data for UI presenting. Can be null if OMDB api exception
     * occures and {@link NotFoundException} if the response from api with the film not found
     */
    IMDbFilmModel findFilm(String apiKey, String title);
}
