package com.diego.planningpoker.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageDto<T> {

    private List<T> content;
    private long totalElements;
    private int totalPages;
    private int size;
    private int number;
    private Sort sort;
    private boolean first;
    private boolean last;
    private int numberOfElements;
    private Pageable pageable;
    private boolean empty;

    @Data
    public static class Sort {
        private boolean empty;
        private boolean sorted;
        private boolean unsorted;
    }

    @Data
    public static class Pageable {
        private int offset;
        private Sort sort;
        private boolean paged;
        private boolean unpaged;
        private int pageNumber;
        private int pageSize;
    }

}