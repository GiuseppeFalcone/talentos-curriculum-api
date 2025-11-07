package com.certimetergroup.easycv.curriculumapi.service;

import com.certimetergroup.easycv.commons.enumeration.ResponseEnum;
import com.certimetergroup.easycv.commons.enumeration.UserRoleEnum;
import com.certimetergroup.easycv.commons.exception.FailureException;
import com.certimetergroup.easycv.curriculumapi.context.RequestContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    private final CurriculumService curriculumService;
    private final RequestContext requestContext;

    public void checkGetCurriculums() {
        if (requestContext.getUserRole().equals(UserRoleEnum.EMPLOYEE))
            throw new FailureException(ResponseEnum.UNAUTHORIZED);
    }

    public void checkPutPatch(Long userIdFromDto, Long curriculumId) {
        boolean isManager = requestContext.getUserRole().equals(UserRoleEnum.MANAGER);
        boolean isEmployee = requestContext.getUserRole().equals(UserRoleEnum.EMPLOYEE);
        boolean userIdMatch = requestContext.getUserId().equals(userIdFromDto);
        boolean curriculumIdMatch = requestContext.getCurriculumId().equals(curriculumId);

        if ((isManager || isEmployee) && (!userIdMatch || !curriculumIdMatch))
            throw new FailureException(ResponseEnum.UNAUTHORIZED);
    }

    public void checkDelete(Long curriculumId) {
        boolean isManager = requestContext.getUserRole().equals(UserRoleEnum.MANAGER);
        boolean isEmployee = requestContext.getUserRole().equals(UserRoleEnum.EMPLOYEE);
        boolean curriculumIdMatch = requestContext.getCurriculumId().equals(curriculumId);

        if (isManager || isEmployee && !curriculumIdMatch)
            throw new FailureException(ResponseEnum.UNAUTHORIZED);
    }
}
