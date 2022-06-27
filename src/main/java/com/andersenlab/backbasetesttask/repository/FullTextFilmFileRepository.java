package com.andersenlab.backbasetesttask.repository;

import com.andersenlab.backbasetesttask.model.FileFilmModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FullTextFilmFileRepository {

    List<FileFilmModel> getByTitleAndYear(String title, String year);
}
