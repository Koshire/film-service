package com.andersenlab.backbasetesttask.mapper;

import com.andersenlab.backbasetesttask.model.IMDbFilmModel;
import com.andersenlab.backbasetesttask.model.dto.IMDbFilmDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BoxOfficeMapper.class})
public interface IMDbFilmMapper extends BaseMapper<IMDbFilmModel, IMDbFilmDto> {

}
