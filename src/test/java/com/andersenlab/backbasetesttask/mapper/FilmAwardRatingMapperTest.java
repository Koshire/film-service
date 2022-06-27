package com.andersenlab.backbasetesttask.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FilmAwardRatingMapperTest {

    @Autowired
    private FilmAwardRatingMapper mapper;

    @ParameterizedTest
    @MethodSource("getArgsApply")
    void mapToCeil(BigDecimal from, BigDecimal exp) {
        Assertions.assertEquals(exp, mapper.mapToCeil(from));
    }

    @Test
    void mapToCeilWrong() {
        Assertions.assertEquals(BigDecimal.ZERO, mapper.mapToCeil(null));
    }

    private static Stream<Arguments> getArgsApply() {
        return Stream.of(
                Arguments.of(new BigDecimal("4.66"), new BigDecimal("4.7")),
                Arguments.of(new BigDecimal("4.91"), new BigDecimal("4.9")),
                Arguments.of(new BigDecimal("4.44"), new BigDecimal("4.4")),
                Arguments.of(new BigDecimal("9999.75"), new BigDecimal("9999.8"))
        );
    }
}