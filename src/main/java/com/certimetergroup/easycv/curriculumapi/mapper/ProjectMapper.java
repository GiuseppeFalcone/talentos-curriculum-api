package com.certimetergroup.easycv.curriculumapi.mapper;

import com.certimetergroup.easycv.commons.response.dto.curriculum.ProjectDto;
import com.certimetergroup.easycv.commons.response.dto.curriculum.create.CreateProjectDto;
import com.certimetergroup.easycv.curriculumapi.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring", uses = { ProjectDomainOptionMapper.class })
public interface ProjectMapper {
    @Mapping(target = "projectId", source = "id")
    ProjectDto toDto(Project project);

    Set<ProjectDto> toSetDto(Set<Project> projectSet);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "curriculum", ignore = true) // Parent link must be set manually
    Project fromDto(ProjectDto dto);

    Set<Project> fromDtoSet(Set<ProjectDto> dtoList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "curriculum", ignore = true)
    Project fromCreateDto(CreateProjectDto dto);
}
