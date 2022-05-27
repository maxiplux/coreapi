package io.base.coreapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CORE_BATCH_EMAIL_DATA_SOURCE")
public class EmailDataSource {

    @Id
    @SequenceGenerator(name = "batch_email_data_source_seq", sequenceName = "batch_email_data_source_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "batch_email_data_source_seq")

    private Long id;
    @Column(unique = true)
    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull(message = "query cannot be null")
    private String query;
    @NotNull(message = "entityName cannot be null")
    private String entityName;


}
