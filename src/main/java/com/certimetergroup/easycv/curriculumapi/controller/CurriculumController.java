package com.certimetergroup.easycv.curriculumapi.controller;

import com.certimetergroup.easycv.commons.enumeration.ResponseEnum;
import com.certimetergroup.easycv.commons.response.Response;

import com.certimetergroup.easycv.commons.response.dto.curriculum.CurriculumDto;
import com.certimetergroup.easycv.commons.response.dto.curriculum.CurriculumLightDto;
import com.certimetergroup.easycv.commons.response.dto.curriculum.create.CreateCurriculumDto;
import com.certimetergroup.easycv.curriculumapi.service.CurriculumService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/curriculums")
@Tag(name = "Curriculums", description = "Operations on curriculum data")
@Validated
@RequiredArgsConstructor
public class CurriculumController {

    private final CurriculumService curriculumService;

    @GetMapping
    public ResponseEntity<Response<PagedModel<CurriculumLightDto>>> getCurriculums(
            @RequestParam(defaultValue = "1") @Positive(message = "Page must be > 0") Integer page,
            @RequestParam(defaultValue = "5") @Positive(message = "Page size must be > 0") Integer pageSize,
            @RequestParam(required = false) Set<Long> userIds,
            @RequestParam(required = false) Set<Long> domainOptionIds) {

        return ResponseEntity.ok().body(new Response<>(ResponseEnum.SUCCESS, curriculumService.getCurriculums(page, pageSize, userIds, domainOptionIds)));
    }

    @GetMapping("/{curriculumId}")
    public ResponseEntity<Response<CurriculumDto>> getCurriculum(
            @PathVariable @NotNull(message = "Curriculum Id required") @Positive(message = "Curriculum Id must be > 0") Long curriculumId) {

        Optional<CurriculumDto> optionalCurriculumDto = curriculumService.getCurriculum(curriculumId);
        return optionalCurriculumDto.map(
                        curriculumDto -> ResponseEntity.ok().body(new Response<>(ResponseEnum.SUCCESS, curriculumDto)))
                .orElseGet(() -> ResponseEntity.status(ResponseEnum.NOT_FOUND.httpStatus).body(new Response<>(ResponseEnum.NOT_FOUND)));
    }

    @PostMapping
    public ResponseEntity<Response<CurriculumDto>> addNewCurriculum(
            @RequestBody @NotNull(message = "CreateCurriculumDto required") CreateCurriculumDto createCurriculumDto) {

        return ResponseEntity.ok().body(new Response<>(ResponseEnum.SUCCESS, curriculumService.addNewCurriculum(createCurriculumDto)));
    }

    @PutMapping("/{curriculumId}")
    public ResponseEntity<Response<CurriculumDto>> replaceCurriculumData(
            @PathVariable @NotNull(message = "Curriculum Id required") @Positive(message = "Wrong curriculum id provided") Long curriculumId,
            @RequestBody @NotNull(message = "CurriculumDto required") CurriculumDto curriculumDto) {

        Optional<CurriculumDto> optionalCurriculumDto = curriculumService.replaceCurriculumData(curriculumId, curriculumDto);
        return optionalCurriculumDto.map(
                        dto -> ResponseEntity.ok().body(new Response<>(ResponseEnum.SUCCESS, dto)))
                .orElseGet(() -> ResponseEntity.status(ResponseEnum.NOT_FOUND.httpStatus).body(new Response<>(ResponseEnum.NOT_FOUND)));
    }

    @DeleteMapping("/{curriculumId}")
    public ResponseEntity<Response<Void>> deleteCurriculum(
            @PathVariable @NotNull(message = "Curriculum Id required") @Positive(message = "Wrong curriculum id provided") Long curriculumId) {

        curriculumService.deleteCurriculum(curriculumId);
        return ResponseEntity.ok().body(new Response<>(ResponseEnum.SUCCESS));
    }
}
