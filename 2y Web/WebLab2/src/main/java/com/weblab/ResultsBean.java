package com.weblab;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ResultsBean implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private List<Result> results;

    public ResultsBean() {
        this.results = new ArrayList<>();
    }

    public void addResult(Result result) {
        this.results.add(result);
    }

    @Data
    @AllArgsConstructor
    public static class Result {
        private String x;
        private String y;
        private String r;
        private boolean isHit;
    }
}
