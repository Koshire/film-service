package com.andersenlab.backbasetesttask.controller;

import com.andersenlab.backbasetesttask.model.dto.FilmAwardRatingDto;
import com.andersenlab.backbasetesttask.service.FilmAwardRatingService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.andersenlab.backbasetesttask.helper.TestHelper.API_KEY_TEST;
import static com.andersenlab.backbasetesttask.helper.TestHelper.BODY_MVC;
import static com.andersenlab.backbasetesttask.helper.TestHelper.TITLE;
import static com.andersenlab.backbasetesttask.helper.TestHelper.getAwardDto;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = FilmsController.class)
@AutoConfigureMockMvc
class DataControllerTest {

    public static final String TITLE_EXC_BODY = "{\"message\":\"getFilm.title: Title can't be null or empty\"}";
    public static final String APIKEY_EXC_BODY = "{\"message\":\"getFilm.apiKey: ApiKey can't be null or empty\"}";
    public static final String RATE_EXC_BODY = "{\"message\":\"Rate can be only in range from 1 to 10\",\"field\":\"rate\"}";
    public static final String ID_EXC_BODY = "\"message\":\"Id must be positive\",\"field\":\"filmId\"";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmAwardRatingService service;

    @Test
    public void checkGetFilm() throws Exception {
        checkGetFilm(TITLE, API_KEY_TEST, BODY_MVC, status().isOk());
    }

    @Test
    public void checkGetFilmEmptyTitle() throws Exception {
        checkGetFilm("", API_KEY_TEST, TITLE_EXC_BODY, status().isBadRequest());
    }

    @Test
    public void checkGetFilmEmptyApiKey() throws Exception {
        checkGetFilm(TITLE, "", APIKEY_EXC_BODY, status().isBadRequest());
    }

    @Test
    public void checkRateFilm() throws Exception {
        checkRateFilm("{\"filmId\": 1, \"rate\": 9}", BODY_MVC, status().isOk());
    }

    @Test
    public void checkRateFilmWrongRate() throws Exception {
        checkRateFilm("{\"filmId\": 1, \"rate\": -1}", RATE_EXC_BODY, status().isBadRequest());
    }

    @Test
    public void checkRateFilmWrongId() throws Exception {
        checkRateFilm("{\"filmId\": -1, \"rate\": 1}", ID_EXC_BODY, status().isBadRequest());
    }

    @Test
    public void checkGetTopFilm() throws Exception {
        List<FilmAwardRatingDto> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(getAwardDto());
        }
        when(service.getTopTenFilms()).thenReturn(list);
        String contentAsString = this.mockMvc.perform(get("/top"))
                .andDo(print()).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        List<FilmAwardRatingDto> filmAwardRatingDtos = mapper.readValue(contentAsString, new TypeReference<>() {});
        Assertions.assertEquals(10, filmAwardRatingDtos.size());
    }

    private void checkRateFilm(String body, String message, ResultMatcher result) throws Exception {
        when(service.rateFilm((long) 1, 9)).thenReturn(getAwardDto());
        this.mockMvc.perform(post("/rate")
                        .characterEncoding(StandardCharsets.UTF_8.name())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(result)
                .andExpect(content().string(containsString(message)));
    }

    private void checkGetFilm(String title, String apiKey, String message, ResultMatcher result) throws Exception {
        when(service.getFilm(TITLE, API_KEY_TEST)).thenReturn(getAwardDto());
        this.mockMvc.perform(get("/")
                        .param("title", title)
                        .header("apiKey", apiKey))
                .andDo(print()).andExpect(result)
                .andExpect(content().string(containsString(message)));
    }
}