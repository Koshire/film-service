package com.andersenlab.backbasetesttask.helper;

import com.andersenlab.backbasetesttask.model.IMDbFilmModel;
import com.andersenlab.backbasetesttask.model.dto.FilmAwardRatingDto;
import com.andersenlab.backbasetesttask.model.dto.IMDbFilmDto;

import java.math.BigDecimal;

public class TestHelper {

    public static final String POSTER = "https://m.media-amazon.com/images/M/MV5BZjBmOTE5MTEtZDcwZC00MzRkLTgzMjYtZjU4YWE5YzM5ZDM5XkEyXkFqcGdeQXVyMzg1ODEwNQ@@._V1_SX300.jpg";
    public static final String GENRE = "Drama, Romance, War";
    public static final String PLOT = "Two young men, one rich, one middle class, who are in love with the same woman, become fighter pilots in World War I.";
    public static final String BODY = "{\"Title\":\"Wings\",\"Year\":\"1927\",\"Rated\":\"PG-13\",\"Released\":\"05 Jan 1929\",\"Runtime\":\"144 min\",\"Genre\":\"Drama, Romance, War\",\"Director\":\"William A. Wellman, Harry d'Abbadie d'Arrast\",\"Writer\":\"John Monk Saunders, Hope Loring, Louis D. Lighton\",\"Actors\":\"Clara Bow, Charles 'Buddy' Rogers, Richard Arlen\",\"Plot\":\"Two young men, one rich, one middle class, who are in love with the same woman, become fighter pilots in World War I.\",\"Language\":\"English\",\"Country\":\"United States\",\"Awards\":\"Won 2 Oscars. 7 wins & 4 nominations total\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BZjBmOTE5MTEtZDcwZC00MzRkLTgzMjYtZjU4YWE5YzM5ZDM5XkEyXkFqcGdeQXVyMzg1ODEwNQ@@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"7.6/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"93%\"}],\"Metascore\":\"N/A\",\"imdbRating\":\"7.6\",\"imdbVotes\":\"12,818\",\"imdbID\":\"tt0018578\",\"Type\":\"movie\",\"DVD\":\"24 Jul 2007\",\"BoxOffice\":\"N/A\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"}";
    public static final String BODY_MVC = "{\"id\":1,\"film\":{\"Title\":\"Wings\",\"imdbID\":\"tt0018578\",\"Year\":\"1927\",\"BoxOffice\":\"N/A\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BZjBmOTE5MTEtZDcwZC00MzRkLTgzMjYtZjU4YWE5YzM5ZDM5XkEyXkFqcGdeQXVyMzg1ODEwNQ@@._V1_SX300.jpg\",\"Genre\":\"Drama, Romance, War\",\"Plot\":\"Two young men, one rich, one middle class, who are in love with the same woman, become fighter pilots in World War I.\"},\"win\":true,\"rate\":1}";
    public static final String IMDB_ID = "tt0018578";
    public static final String YEAR = "1927";
    public static final String BOX_OFFICE = "N/A";
    public static final String TITLE = "Wings";
    public static final String API_KEY_TEST = "sssssss";

    public static IMDbFilmDto getDto(String boxOffice) {
        return new IMDbFilmDto(TITLE, IMDB_ID, YEAR, boxOffice, POSTER, GENRE, PLOT);
    }

    public static IMDbFilmModel getModel(BigDecimal boxOffice) {
        return new IMDbFilmModel(IMDB_ID, TITLE, YEAR, boxOffice, POSTER, GENRE, PLOT);
    }

    public static FilmAwardRatingDto getAwardDto() {
        return new FilmAwardRatingDto(1L, getDto(BOX_OFFICE), true, BigDecimal.ONE);
    }
}
