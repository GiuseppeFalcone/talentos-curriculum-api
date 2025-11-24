package com.certimetergroup.easycv.curriculumapi.repository.specification;

import com.certimetergroup.easycv.curriculumapi.model.Curriculum;
import com.certimetergroup.easycv.curriculumapi.model.ProjectDomainOption;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
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

    public static Specification<Curriculum> hasDomainOptionIds(Set<Long> domainOptionIds) {
        return (root, query, cb) -> {

            if (domainOptionIds == null || domainOptionIds.isEmpty())
                return cb.conjunction();

            Predicate finalPredicate = cb.conjunction();

            for (Long optionId : domainOptionIds) {
                if (query != null) {
                    Subquery<Long> subquery = query.subquery(Long.class);
                    Root<ProjectDomainOption> subRoot = subquery.from(ProjectDomainOption.class);

                    subquery.select(subRoot.get("id"));
                    subquery.where(
                            cb.equal(subRoot.get("curriculum"), root),
                            cb.equal(subRoot.get("domainOptionId"), optionId)
                    );

                    finalPredicate = cb.and(finalPredicate, cb.exists(subquery));
                }
            }

            return finalPredicate;
        };
    }
}
