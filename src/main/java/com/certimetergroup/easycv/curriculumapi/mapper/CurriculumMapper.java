package com.certimetergroup.easycv.curriculumapi.mapper;

import com.certimetergroup.easycv.commons.response.dto.curriculum.CurriculumDto;
import com.certimetergroup.easycv.commons.response.dto.curriculum.CurriculumLightDto;
import com.certimetergroup.easycv.commons.response.dto.curriculum.create.CreateCurriculumDto;
import com.certimetergroup.easycv.curriculumapi.model.Curriculum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {
        EducationMapper.class,
        ProjectMapper.class,
        ProjectDomainOptionMapper.class
})
public interface CurriculumMapper {
    @Mapping(target = "curriculumId", source = "id")
    CurriculumDto toDto(Curriculum curriculum);

    @Mapping(target = "curriculumId", source = "id")
    @Mapping(target = "numberOfProjects", expression = "java(curriculum.getProjects().size())")
    @Mapping(target = "hasDegree", expression = "java(!curriculum.getEducationHistory().isEmpty())")
    CurriculumLightDto toLightDto(Curriculum curriculum);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "lastModifiedAt", expression = "java(LocalDateTime.now())")
    Curriculum createCurriculum(CreateCurriculumDto createCurriculumDto, Long userId);

    @Mapping(target = "id", source = "curriculumId")
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "lastModifiedAt", expression = "java(LocalDateTime.now())")
    void updateCurriculumFromDto(CurriculumDto curriculumDto, @MappingTarget Curriculum curriculum);
}
