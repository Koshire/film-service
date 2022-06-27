package com.andersenlab.backbasetesttask.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class IMDbFilmDto {

    @JsonProperty("Title")
    private String title;
    @JsonProperty("imdbID")
    private String imdbId;
    @JsonProperty("Year")
    private String year;
    @JsonProperty("BoxOffice")
    private String boxOffice;
    @JsonProperty("Poster")
    private String poster;
    @JsonProperty("Genre")
    private String genre;
    @JsonProperty("Plot")
    private String plot;
}
