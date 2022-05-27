package io.base.coreapi.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailImagePathDto {


    @NotBlank
    private String name;
    @NotBlank
    private String content;

}
