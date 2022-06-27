package com.andersenlab.backbasetesttask.repository;

import com.andersenlab.backbasetesttask.model.FileFilmModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmFileRepository extends JpaRepository<FileFilmModel, Long>, FullTextFilmFileRepository {

}
