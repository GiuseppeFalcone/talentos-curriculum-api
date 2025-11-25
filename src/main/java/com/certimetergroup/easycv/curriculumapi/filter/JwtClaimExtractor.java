package com.certimetergroup.easycv.curriculumapi.filter;

import com.certimetergroup.easycv.commons.utility.HttpHeaderUtil;
import com.certimetergroup.easycv.curriculumapi.context.RequestContext;
import com.certimetergroup.easycv.curriculumapi.service.CurriculumService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

@Order(1)
@Component
@RequiredArgsConstructor
public class JwtClaimExtractor extends OncePerRequestFilter {

    private final RequestContext requestContext;
    private final CurriculumService curriculumService;
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            String accessToken = HttpHeaderUtil.sanitizeAccessToken(request.getHeader("Authorization"));

            String[] accessTokenSplit = accessToken.split("\\.");

            byte[] payloadBytes = Base64.getUrlDecoder().decode(accessTokenSplit[1]);

            String payload = new String(payloadBytes, StandardCharsets.UTF_8);

            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> claimsMap = mapper.readValue(payload, Map.class);

            requestContext.setUserId(Long.parseLong((String) claimsMap.get("sub")));

            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
}
