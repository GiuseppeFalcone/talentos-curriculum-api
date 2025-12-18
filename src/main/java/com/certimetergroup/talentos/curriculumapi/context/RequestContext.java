package com.certimetergroup.talentos.curriculumapi.context;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Data
@Component
@RequestScope
public class RequestContext {
    private Long userId;
}
