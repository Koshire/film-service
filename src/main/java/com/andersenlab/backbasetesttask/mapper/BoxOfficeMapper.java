package com.andersenlab.backbasetesttask.mapper;

import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface BoxOfficeMapper extends BaseMapper<BigDecimal, String> {

    @Override
    default BigDecimal mapToModel(String dto) {
        return Optional.ofNullable(dto)
                .filter(s -> !s.isBlank())
                .map(s -> s.replaceAll("[^\\d]", ""))
                .filter(s -> s.matches("\\d+"))
                .map(BigDecimal::new)
                .orElse(null);
    }

    @Override
    default String mapToDto(BigDecimal model) {
        return Optional.ofNullable(model)
                .map(bigDecimal -> NumberFormat.getCurrencyInstance(Locale.US).format(bigDecimal))
                .map(s -> s.replaceAll("\\.\\d\\d$", ""))
                .orElse("N/A");
    }
}
