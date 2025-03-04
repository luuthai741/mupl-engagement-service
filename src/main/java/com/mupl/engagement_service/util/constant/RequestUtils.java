package com.mupl.engagement_service.util.constant;

import com.mupl.engagement_service.exception.BadRequestException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public class RequestUtils {
    public static Pageable getPageable(HttpServletRequest request, String defaultSortBy, String defaultSortOrder, Class<?> clazz) {
        int page = Integer.parseInt(StringUtils.isBlank(request.getParameter("page")) ? "1" : request.getParameter("page"));
        int size = Integer.parseInt(StringUtils.isBlank(request.getParameter("size")) ? "10" : request.getParameter("size"));
        String sortBy = getSortBy(defaultSortBy, request, clazz);
        Sort.Direction sortOrder = getSortDirection(defaultSortOrder, request);
        return PageRequest.of(page - 1, size, sortOrder, sortBy);
    }

    private static String getSortBy(String sortBy, HttpServletRequest request, Class<?> clazz) {
        sortBy = StringUtils.isBlank(request.getParameter("sortBy")) ? sortBy : request.getParameter("sortBy");
        try {
            checkFieldNameToSort(clazz, sortBy);
            return sortBy;
        } catch (NoSuchFieldException e) {
            throw new BadRequestException("Not found sort by: " + sortBy);
        }
    }

    private static Sort.Direction getSortDirection(String sortBy, HttpServletRequest request) {
        String direction = StringUtils.isNotBlank(sortBy) ? sortBy : request.getParameter("sortOrder");
        if (StringUtils.equalsAny(direction, "ASC", "DESC")) {
            return Sort.Direction.fromString(direction);
        }
        return Sort.Direction.ASC;
    }

    private static void checkFieldNameToSort(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        clazz.getDeclaredField(fieldName);
    }
}
