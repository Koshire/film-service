package com.andersenlab.backbasetesttask.mapper;

import com.andersenlab.backbasetesttask.model.IMDbFilmModel;
import com.andersenlab.backbasetesttask.model.dto.IMDbFilmDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static com.andersenlab.backbasetesttask.helper.TestHelper.getDto;
import static com.andersenlab.backbasetesttask.helper.TestHelper.getModel;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class IMDbFilmMapperTest {

    @Autowired
    private IMDbFilmMapper mapper;

    @Test
    public void mapToModel() {
        IMDbFilmModel imDbFilmModel = mapper.mapToModel(getDto("$4"));
        Assertions.assertEquals(getModel(new BigDecimal(4)), imDbFilmModel);
    }

    @Test
    public void mapToDto() {
        IMDbFilmDto imDbFilmModel = mapper.mapToDto(getModel(new BigDecimal(4)));
        Assertions.assertEquals(getDto("$4"), imDbFilmModel);
    }
}