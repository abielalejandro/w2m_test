package com.rgarcia.w2m.application.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateSuperHero {

    @NotNull
    @NotEmpty
    String name;

    @NotNull
    @NotEmpty
    String realName;

    @NotNull
    @NotEmpty
    String power;
}
