package com.certimetergroup.easycv.curriculumapi.repository;

import com.certimetergroup.easycv.curriculumapi.model.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long>, JpaSpecificationExecutor<Curriculum> {
    Optional<Curriculum> findByUserId(Long userId);

    Optional<Long> findIdByUserId(Long userId);
}
