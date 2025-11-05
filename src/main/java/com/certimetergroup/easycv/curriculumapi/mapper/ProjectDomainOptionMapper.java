package com.certimetergroup.easycv.curriculumapi.mapper;

import com.certimetergroup.easycv.commons.response.dto.curriculum.ProjectDomainOptionDto;
import com.certimetergroup.easycv.curriculumapi.model.ProjectDomainOption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProjectDomainOptionMapper {
    @Mapping(target = "projectDomainId", source = "id")
    ProjectDomainOptionDto toDto(ProjectDomainOption projectDomainOption);

    Set<ProjectDomainOptionDto> toSetDto(Set<ProjectDomainOption> domainOptionSet);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "curriculum", ignore = true)
    ProjectDomainOption fromDto(ProjectDomainOptionDto dto);

    Set<ProjectDomainOption> fromDtoSet(Set<ProjectDomainOptionDto> dtoList);
}
