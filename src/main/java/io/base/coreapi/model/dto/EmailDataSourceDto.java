package io.base.coreapi.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailDataSourceDto {

    private Long id;

    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull(message = "query cannot be null")
    private String query;
    @NotNull(message = "entityName cannot be null")
    private String entityName;
}
