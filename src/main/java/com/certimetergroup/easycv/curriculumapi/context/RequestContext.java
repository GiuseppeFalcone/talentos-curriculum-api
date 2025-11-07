package com.certimetergroup.easycv.curriculumapi.context;

import com.certimetergroup.easycv.commons.enumeration.UserRoleEnum;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Data
@Builder
@Component
@RequestScope
public class RequestContext {
    private Long userId;
    private Long curriculumId;
    private UserRoleEnum userRole;
}
