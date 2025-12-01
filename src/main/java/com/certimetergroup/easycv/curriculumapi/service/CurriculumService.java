package com.certimetergroup.easycv.curriculumapi.service;

import com.certimetergroup.easycv.commons.enumeration.ResponseEnum;
import com.certimetergroup.easycv.commons.exception.FailureException;
import com.certimetergroup.easycv.commons.response.dto.curriculum.CurriculumDto;
import com.certimetergroup.easycv.commons.response.dto.curriculum.CurriculumLightDto;
import com.certimetergroup.easycv.commons.response.dto.curriculum.create.CreateCurriculumDto;
import com.certimetergroup.easycv.curriculumapi.context.RequestContext;
import com.certimetergroup.easycv.curriculumapi.mapper.CurriculumMapper;
import com.certimetergroup.easycv.curriculumapi.model.Curriculum;
import com.certimetergroup.easycv.curriculumapi.repository.CurriculumRepository;
import com.certimetergroup.easycv.curriculumapi.repository.specification.CurriculumSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CurriculumService {
    private final CurriculumRepository curriculumRepository;
    private final RequestContext requestContext;
    private final CurriculumMapper curriculumMapper;

    public PagedModel<CurriculumLightDto> getCurriculums(Integer page, Integer pageSize, Set<Long> userIds, Set<Long> domainOptionIds) {
        Pageable paging = PageRequest.of(page - 1, pageSize);

        Specification<Curriculum> spec = Specification.unrestricted();

        if (userIds != null && !userIds.isEmpty())
            spec = spec.and(CurriculumSpecification.hasUserIds(userIds));

        if (domainOptionIds != null && !domainOptionIds.isEmpty())
            spec = spec.and(CurriculumSpecification.hasDomainOptionIds(domainOptionIds));

        return new PagedModel<>(curriculumRepository.findAll(spec, paging).map(curriculumMapper::toLightDto));
    }

    public Optional<CurriculumDto> getCurriculum(Long curriculumId) {
        return curriculumRepository.findById(curriculumId).map(curriculumMapper::toDto);
    }

    @Transactional
    public CurriculumDto addNewCurriculum(CreateCurriculumDto createCurriculumDto) {
        Optional<Curriculum> optionalCurriculum = curriculumRepository.findByUserId(requestContext.getUserId());

        if (optionalCurriculum.isPresent())
            throw new FailureException(ResponseEnum.BAD_REQUEST, "User already has curriculum in db");

        Curriculum curriculum = curriculumMapper.createCurriculum(createCurriculumDto, requestContext.getUserId());

        curriculum.getEducationHistory().forEach(education -> education.setCurriculum(curriculum));

        curriculum.getProjects().forEach(project -> {
            project.setCurriculum(curriculum);
            project.getDomainOptions().forEach(domainOption -> {
                domainOption.setUserId(requestContext.getUserId());
                domainOption.setProject(project);
                domainOption.setCurriculum(curriculum);
            });
        });

        curriculumRepository.save(curriculum);
        return curriculumMapper.toDto(curriculum);
    }

    @Transactional
    public Optional<CurriculumDto> replaceCurriculumData(Long curriculumId, CurriculumDto curriculumDto) {
        if (!curriculumId.equals(curriculumDto.getCurriculumId()))
            throw new FailureException(ResponseEnum.BAD_REQUEST, "Path and Body CurriculumId do not match");

        Curriculum curriculum = curriculumRepository.findById(curriculumId)
                .orElseThrow(() -> new FailureException(ResponseEnum.NOT_FOUND, "No curriculum found with provided id"));

        curriculumMapper.updateCurriculumFromDto(curriculumDto, curriculum);

        curriculum.getEducationHistory().forEach(
                education -> education.setCurriculum(curriculum));

        curriculum.getProjects().forEach(project -> {
            project.setCurriculum(curriculum);

            project.getDomainOptions().forEach(domainOption -> {
                domainOption.setProject(project);
                domainOption.setCurriculum(curriculum);
            });
        });

        if (curriculum.getDomainOptions() != null) {
            curriculum.getDomainOptions().forEach(option -> {
                option.setCurriculum(curriculum);
                option.setProject(null);
            });
        }

        Curriculum updatedCurriculum = curriculumRepository.save(curriculum);
        return Optional.of(curriculumMapper.toDto(updatedCurriculum));
    }

    @Transactional
    public void deleteCurriculum(Long curriculumId) {
        curriculumRepository.deleteById(curriculumId);
    }
}
