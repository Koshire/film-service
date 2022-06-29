package com.andersenlab.backbasetesttask.service.impl;

import com.andersenlab.backbasetesttask.config.error.NotFoundException;
import com.andersenlab.backbasetesttask.mapper.IMDbFilmMapper;
import com.andersenlab.backbasetesttask.model.IMDbFilmModel;
import com.andersenlab.backbasetesttask.model.dto.IMDbFilmDto;
import com.andersenlab.backbasetesttask.service.IMDbFilmService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.util.Optional.ofNullable;

@Slf4j
@Service
@RequiredArgsConstructor
public class IMDbFilmServiceImpl implements IMDbFilmService {

    public static final String APIKEY_PARAM = "apikey";
    public static final String TITLE_PARAM = "t";

    @Value("${omdb.api.url:}")
    private String baseUrl;

    private final HttpClient client;
    private final ObjectMapper mapper;
    private final IMDbFilmMapper imDbFilmMapper;

    @Override
    public IMDbFilmModel findFilm(String apiKey, String title) {
        String url = UriComponentsBuilder
                .fromHttpUrl(baseUrl)
                .queryParam(APIKEY_PARAM, apiKey)
                .queryParam(TITLE_PARAM, title)
                .toUriString();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            boolean isResponseOk = ofNullable(response)
                    .map(HttpResponse::statusCode)
                    .map(HttpStatus::resolve)
                    .map(HttpStatus::is2xxSuccessful)
                    .orElse(false);
            if (isResponseOk) {
                checkForErrorMessage(response);
                return imDbFilmMapper.mapToModel(response.body().isBlank()
                        ? null
                        : mapper.readValue(response.body(), IMDbFilmDto.class));
            }
        } catch (Exception e) {
            log.warn("Can't get film with title: " + title, e);
        }
        return null;
    }

    private void checkForErrorMessage(HttpResponse<String> response) {
        if (response.body().matches("\\{\"Response\":.+\"Error\":.+}")) {
            throw new NotFoundException(response.body());
        }
    }
}
