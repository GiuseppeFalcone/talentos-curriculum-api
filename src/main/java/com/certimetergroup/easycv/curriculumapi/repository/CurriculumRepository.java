package com.certimetergroup.easycv.curriculumapi.repository;

import com.certimetergroup.easycv.curriculumapi.model.Curriculum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
    Optional<Curriculum> findByUserId(Long userId);
    Page<Curriculum> findAllByUserIds(Pageable pageable, Set<Long> userIds);
}
