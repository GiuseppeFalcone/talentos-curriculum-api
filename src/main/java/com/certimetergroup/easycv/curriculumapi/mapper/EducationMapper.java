package com.certimetergroup.easycv.curriculumapi.mapper;

import com.certimetergroup.easycv.commons.response.dto.curriculum.EducationDto;
import com.certimetergroup.easycv.commons.response.dto.curriculum.create.CreateEducationDto;
import com.certimetergroup.easycv.curriculumapi.model.Education;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface EducationMapper {
    @Mapping(target = "educationId", source = "id")
    EducationDto toDto(Education education);

    Set<EducationDto> toSetDto(Set<Education> educationSet);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "curriculum", ignore = true)
    Education fromDto(EducationDto educationDto);

    Set<Education> fromDtoSet(Set<EducationDto> educationDtoSet);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "curriculum", ignore = true)
    Education createEntity(CreateEducationDto createEducationDto);

    Set<Education> createEntitySet(Set<CreateEducationDto> educationDtoSet);
}
