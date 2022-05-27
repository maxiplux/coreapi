package io.base.coreapi.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CronExpressionDto {
    @PositiveOrZero
    @Size(min = 2022, max = 2099, message = "Max Value number 2099")
    @PositiveOrZero
    @NotNull(message = "Year cannot be null")
    private Integer year;


    @PositiveOrZero
    @Size(min = 1, max = 12, message = "Max Value number 12")
    @PositiveOrZero
    @NotNull(message = "Month cannot be null")
    private Integer month;

    @Size(min = 1, max = 31, message = "Max Value number 31")
    @PositiveOrZero
    @NotNull(message = "Day cannot be null")
    private Integer day;

    @Size(min = 0, max = 23, message = "Max Value number 23")
    @PositiveOrZero
    @NotNull(message = "Hour cannot be null")
    private Integer hour;


    @Size(min = 0, max = 59, message = "Max Value number 59")
    @PositiveOrZero
    @NotNull(message = "Minute cannot be null")
    private Integer minute;
}
