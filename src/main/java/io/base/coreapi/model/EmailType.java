package io.base.coreapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CORE_BATCH_EMAIL_TYPE")
public class EmailType {

    @Id
    @SequenceGenerator(name = "batch_email_type_seq", sequenceName = "batch_email_type_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "batch_email_type_seq")

    private Long id;
    @Column(unique = true)

    private String name;


}
