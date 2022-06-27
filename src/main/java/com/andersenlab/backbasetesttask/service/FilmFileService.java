package com.andersenlab.backbasetesttask.service;

import com.andersenlab.backbasetesttask.model.FileFilmModel;

import java.util.List;

public interface FilmFileService {

    boolean rewriteAll();

    List<FileFilmModel> getByNameAndYear(String name, String year);
}
