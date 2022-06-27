package com.andersenlab.backbasetesttask.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

class BoxOfficeMapperTest {

    private final BoxOfficeMapper mapper = new BoxOfficeMapper() {};

    @ParameterizedTest
    @MethodSource("getArgsApply")
    public void checkMapToModel(String dto) {
        Assertions.assertEquals(mapper.mapToModel(dto), new BigDecimal("9000000000"));
    }

    @ParameterizedTest
    @MethodSource("getArgsNullOrWrong")
    public void checkWrong(String dto) {
        Assertions.assertNull(mapper.mapToModel(dto));
    }

    @Test
    public void checkMapToDto() {
        String value = mapper.mapToDto(new BigDecimal("9000000000"));
        Assertions.assertEquals("$9,000,000,000", value);
    }

    @Test
    public void checkMapToDtoWrong() {
        String value = mapper.mapToDto(null);
        Assertions.assertEquals("N/A", value);
    }

    private static Stream<Arguments> getArgsNullOrWrong() {
        return Stream.of(
               Arguments.of((String) null),
               Arguments.of("N/A"),
               Arguments.of(""),
               Arguments.of("UNDEFINED")
        );
    }

    private static Stream<Arguments> getArgsApply() {
        return Stream.of(
                Arguments.of("$90.00.000,000"),
                Arguments.of("$90.000,000,00"),
                Arguments.of("$90.00.000000"),
                Arguments.of("9000000000")
        );
    }
}