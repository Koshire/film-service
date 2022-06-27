package com.andersenlab.backbasetesttask.mapper;

import com.andersenlab.backbasetesttask.model.FilmAwardRatingModel;
import com.andersenlab.backbasetesttask.model.dto.FilmAwardRatingDto;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Mapper(componentModel = "spring", uses = {IMDbFilmMapper.class})
public interface FilmAwardRatingMapper extends BaseMapper<FilmAwardRatingModel, FilmAwardRatingDto> {

    default BigDecimal mapToCeil(BigDecimal value) {
        return Optional.ofNullable(value)
                .map(bigDecimal -> value.setScale(1, RoundingMode.HALF_UP))
                .orElse(BigDecimal.ZERO);
    }
}
