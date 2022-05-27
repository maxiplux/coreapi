package io.base.coreapi.model;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CORE_BATCH_EMAIL")
@TypeDefs({
    @TypeDef(
        name = "string-array",
        typeClass = StringArrayType.class
    )
})
public class BatchEmail {

    @Id
    @SequenceGenerator(name = "batch_email_seq", sequenceName = "batch_email_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "batch_email_seq")
    private Long id;

    @Column(unique = true, name = "email_name")
    private String emailName;
    private String subject;

    private Boolean testMode;

    @ManyToOne
    @JoinColumn(name = "batch_email_id")
    private EmailType emailType;


    @ManyToOne
    @JoinColumn(name = "batch_data_source_id")
    private EmailDataSource emailDataSource;

//    @Type(type = "string-array")
//    @Column(
//        name = "test_emails",
//        columnDefinition = "text[]"
//    )
//    private List<String> testEmails;

    private LocalDateTime emailStart;
    private LocalDateTime emailEnd;

    private LocalDateTime lastExecution;

    private String templatePath;

    private LocalDate cronDate;

    private LocalTime cronTime;

    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "batchEmail")
    //private Collection<EmailImagePath> images = new ArrayList<>();

//    @Type(type = "string-array")
//    @Column(
//        name = "images",
//        columnDefinition = "text[]"
//    )
    //private List<String> images;


}
