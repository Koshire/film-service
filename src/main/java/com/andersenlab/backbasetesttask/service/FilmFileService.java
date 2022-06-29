package com.andersenlab.backbasetesttask.service;

import com.andersenlab.backbasetesttask.model.FileFilmModel;

import java.util.List;

public interface FilmFileService {

    /**
     * Method rewrites table with films from file with awards. Reads file with awards and
     * filters lines by "Best picture". Then gets Year, Text and Win? values and puts them in
     * the DB with index in fulltext search.
     *
     * @return boolean true if everything is ok
     */
    boolean rewriteAll();

    /**
     * Fulltext search from DB table with data from the file with awards.
     * Performed with the help of LUCENE indexes
     *
     * @param name - Film title
     * @param year - Film year
     * @return  - list {@link FileFilmModel} as search result
     */
    List<FileFilmModel> getByNameAndYear(String name, String year);
}
