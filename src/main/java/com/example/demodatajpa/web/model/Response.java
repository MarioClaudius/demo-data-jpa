package com.example.demodatajpa.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private Integer status;     //status code (200, 400, 500)
    private T data;
    private Map<String, List<String>> errors;
    private Pagination pagination;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Pagination{
        private Integer page;
        private Long size;
        private Long totalItems;
    }
}
