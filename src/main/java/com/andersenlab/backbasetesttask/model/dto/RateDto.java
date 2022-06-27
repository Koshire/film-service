package com.andersenlab.backbasetesttask.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RateDto {

    public static final String ID_EXC_MESSAGE = "Id can't be null";
    public static final String ID_POS_EXC_MESSAGE = "Id must be positive";
    public static final String RAE_EXC_MESSAGE = "Rate can be only in range from 1 to 10";

    @NotNull(message = ID_EXC_MESSAGE)
    @Positive(message = ID_POS_EXC_MESSAGE)
    private Long filmId;

    @Range(max = 10, min = 1, message = RAE_EXC_MESSAGE)
    private int rate;
}
