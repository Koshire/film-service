package com.andersenlab.backbasetesttask.service;

import com.andersenlab.backbasetesttask.model.IMDbFilmModel;

public interface IMDbFilmService {

    IMDbFilmModel findFilm(String apiKey, String title);
}
