package com.andersenlab.backbasetesttask.config;

import com.andersenlab.backbasetesttask.model.PreparedModel;
import com.andersenlab.backbasetesttask.service.FilmFileService;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import java.net.http.HttpClient;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Configuration
public class PreparedConfiguration {

    private static final Duration CONNECTION_TIMEOUT = Duration.of(120, ChronoUnit.SECONDS);

    @Bean
    public PreparedModel getInit(FilmFileService service) {
        boolean answer = service.rewriteAll();
        return new PreparedModel(answer);
    }

    @Bean
    public HttpClient getHttpClient() {
        return HttpClient.newBuilder()
                .connectTimeout(CONNECTION_TIMEOUT)
                .build();
    }
}
