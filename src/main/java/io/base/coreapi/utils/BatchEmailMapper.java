package io.base.coreapi.utils;


import io.base.coreapi.model.BatchEmail;
import io.base.coreapi.model.dto.BatchEmailDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BatchEmailMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)


    @Mappings({
        @Mapping(target = "emailType.id", source = "emailTypeId"),
        @Mapping(target = "emailDataSource.id", source = "emailDataSourceId"),


        @Mapping(target = "emailName", source = "name"),
        @Mapping(target = "emailStart", source = "start"),
        @Mapping(target = "emailEnd", source = "end"),
        @Mapping(target = "templatePath", ignore = true),
        //@Mapping(target="images", ignore = true),
    })
    void updateBatchEmailFromBatchEmailDto(BatchEmailDto dto, @MappingTarget BatchEmail entity);

    @Mappings({
        @Mapping(source = "emailType.id", target = "emailTypeId"),
        @Mapping(source = "emailDataSource.id", target = "emailDataSourceId"),

        @Mapping(source = "emailName", target = "name"),
        @Mapping(source = "emailStart", target = "start"),
        @Mapping(source = "emailEnd", target = "end"),

        //@Mapping(source="testEmails", target="testEmails"),
        @Mapping(target = "template", ignore = true),
        @Mapping(target = "testEmails", ignore = true),
        @Mapping(target = "imagesDto", ignore = true),

    })
    BatchEmailDto batchEmailToBatchEmailDto(BatchEmail goal);

    @Mappings({
        @Mapping(target = "emailType.id", source = "emailTypeId"),
        @Mapping(target = "emailDataSource.id", source = "emailDataSourceId"),


        @Mapping(target = "emailName", source = "name"),
        @Mapping(target = "emailStart", source = "start"),
        @Mapping(target = "emailEnd", source = "end"),

        @Mapping(target = "templatePath", ignore = true),

        //@Mapping(target="images", ignore = true),

    })
    BatchEmail batchEmailDtoToBatchEmail(BatchEmailDto goal);
}

