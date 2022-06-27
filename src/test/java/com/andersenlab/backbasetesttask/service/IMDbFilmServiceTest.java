package com.andersenlab.backbasetesttask.service;

import com.andersenlab.backbasetesttask.mapper.IMDbFilmMapper;
import com.andersenlab.backbasetesttask.model.IMDbFilmModel;
import com.andersenlab.backbasetesttask.model.dto.IMDbFilmDto;
import com.andersenlab.backbasetesttask.service.impl.IMDbFilmServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import static com.andersenlab.backbasetesttask.helper.TestHelper.API_KEY_TEST;
import static com.andersenlab.backbasetesttask.helper.TestHelper.BODY;
import static com.andersenlab.backbasetesttask.helper.TestHelper.BOX_OFFICE;
import static com.andersenlab.backbasetesttask.helper.TestHelper.GENRE;
import static com.andersenlab.backbasetesttask.helper.TestHelper.IMDB_ID;
import static com.andersenlab.backbasetesttask.helper.TestHelper.PLOT;
import static com.andersenlab.backbasetesttask.helper.TestHelper.POSTER;
import static com.andersenlab.backbasetesttask.helper.TestHelper.TITLE;
import static com.andersenlab.backbasetesttask.helper.TestHelper.YEAR;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IMDbFilmServiceTest {

    @Mock
    private HttpClient client;

    @Mock
    private IMDbFilmMapper imDbFilmMapper;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private IMDbFilmServiceImpl imDbFilmService;

    @Test
    void findFilmPositive() throws IOException, InterruptedException {
        HttpResponse httpResponse = mock(HttpResponse.class);
        when(client.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn(BODY);

        IMDbFilmDto imDbFilmDto = new IMDbFilmDto(TITLE,
                IMDB_ID,
                YEAR,
                BOX_OFFICE,
                POSTER,
                GENRE,
                PLOT);

        when(objectMapper.readValue(BODY, IMDbFilmDto.class)).thenReturn(imDbFilmDto);
        IMDbFilmModel imDbFilmModel = new IMDbFilmModel(IMDB_ID, TITLE, YEAR, null, POSTER, GENRE, PLOT);
        when(imDbFilmMapper.mapToModel(imDbFilmDto)).thenReturn(imDbFilmModel);

        IMDbFilmModel film = imDbFilmService.findFilm(API_KEY_TEST, TITLE);
        Assertions.assertNotNull(film);
    }

    @Test
    void findFilmNegative() throws IOException, InterruptedException {
        HttpResponse httpResponse = mock(HttpResponse.class);
        when(client.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(200);

        IMDbFilmModel film = imDbFilmService.findFilm(API_KEY_TEST, TITLE);
        Assertions.assertNull(film);
    }
}