package com.certimetergroup.talentos.curriculumapi.repository;

import com.certimetergroup.talentos.curriculumapi.model.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long>, JpaSpecificationExecutor<Curriculum> {
    Optional<Curriculum> findByUserId(Long userId);

    @Query("SELECT c.id FROM Curriculum c WHERE c.userId = :userId")
    Optional<Long> findIdByUserId(@Param("userId") Long userId);
}
