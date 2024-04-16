package com.rgarcia.w2m.common.dto;

import lombok.Data;

@Data
public class SearchSuperHeroParameters {
    int page;
    int limit;
    String query;
}
