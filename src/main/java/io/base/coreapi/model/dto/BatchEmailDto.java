package io.base.coreapi.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatchEmailDto {


    private Long id;


    @NotBlank
    private String name;
    @NotBlank
    private String subject;


    private Boolean testMode;

    @NotNull
    private Long emailTypeId;

    @NotNull
    private Long emailDataSourceId;


    private List<String> testEmails;

    @NotNull
    private LocalDateTime start;
    @NotNull
    private LocalDateTime end;

    private LocalDateTime lastExecution;


    private LocalDate cronDate;

    private LocalTime cronTime;

    @NotBlank
    private String template;


    private List<EmailImagePathDto> imagesDto;
}
