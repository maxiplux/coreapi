package io.base.coreapi.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EmailTypeDto {


    private Long id;
    @NotNull(message = "Name cannot be null")
    private String name;


}
