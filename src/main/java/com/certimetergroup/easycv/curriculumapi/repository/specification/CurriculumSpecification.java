package com.certimetergroup.easycv.curriculumapi.repository.specification;

import com.certimetergroup.easycv.curriculumapi.model.Curriculum;
import com.certimetergroup.easycv.curriculumapi.model.ProjectDomainOption;
import jakarta.persistence.criteria.Join;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CurriculumSpecification {
    public static Specification<Curriculum> hasUserId(Long userId) {
        return (root, query, cb) ->
            cb.equal(root.get("userId"), userId);
    }

    public static Specification<Curriculum> hasUserIds(Set<Long> userIds) {
        return (root, query, cb) ->
            root.get("userId").in(userIds);
    }

    public static Specification<Curriculum> hasDomainId(Long domainId) {
        return (root, query, cb) -> {
            query.distinct(true);
            Join<Curriculum, ProjectDomainOption> optionJoin = root.join("domainOptions");
            return cb.equal(optionJoin.get("domainId"), domainId);
        };
    }

    public static Specification<Curriculum> hasDomainOptionId(Long domainOptionId) {
        return (root, query, cb) -> {
            query.distinct(true);
            Join<Curriculum, ProjectDomainOption> optionJoin = root.join("domainOptions");
            return cb.equal(optionJoin.get("domainOptionId"), domainOptionId);
        };
    }
}
