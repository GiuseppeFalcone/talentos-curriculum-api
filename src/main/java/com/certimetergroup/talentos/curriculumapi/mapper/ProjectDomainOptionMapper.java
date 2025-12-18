package com.certimetergroup.talentos.curriculumapi.mapper;

import com.certimetergroup.talentos.commons.response.dto.curriculum.ProjectDomainOptionDto;
import com.certimetergroup.talentos.commons.response.dto.curriculum.create.CreateProjectDomainOptionDto;
import com.certimetergroup.talentos.curriculumapi.model.ProjectDomainOption;
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

    Set<ProjectDomainOption> fromDtoSet(Set<ProjectDomainOptionDto> dtoSet);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "curriculum", ignore = true)
    ProjectDomainOption fromCreateDto(CreateProjectDomainOptionDto dto);

    Set<ProjectDomainOption> fromCreateDtoSet(Set<CreateProjectDomainOptionDto> dtoSet);
}
