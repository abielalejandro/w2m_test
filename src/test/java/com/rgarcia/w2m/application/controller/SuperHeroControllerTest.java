package com.rgarcia.w2m.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rgarcia.w2m.application.dto.request.CreateSuperHero;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class SuperHeroControllerTest {
    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {}

    @Test
    void whenGetHeroById() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/superheroes/1")
                        .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status().isOk());
    }

    @Test
    void whenGetHeroByIdThrowNotFound()  throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/superheroes/99999")
                                .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenCreateHero()  throws Exception {
        CreateSuperHero post = new CreateSuperHero(
                "Flash","Barry","Velocidad"
        );

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/superheroes")
                                .header("content-type","application/json")
                                .with(
                                        SecurityMockMvcRequestPostProcessors
                                                .user("admin")
                                                .authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))
                        ).content(
                                objectMapper.writeValueAsString(post)
                        )
                )
                .andExpect(status().isOk());
    }

    @Test
    void whenCreateHeroForbiden()  throws Exception {
        CreateSuperHero post = new CreateSuperHero(
                "Flash","Barry","Velocidad"
        );

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/superheroes")
                                .header("content-type","application/json")
                                .with(
                                        SecurityMockMvcRequestPostProcessors
                                                .user("user")
                        ).content(
                                objectMapper.writeValueAsString(post)
                        )
                )
                .andExpect(status().isForbidden());
    }


    @Test
    void whenUpdateHero()  throws Exception {
        CreateSuperHero post = new CreateSuperHero(
                "Flash","Barry","Velocidad"
        );

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/v1/superheroes/1")
                                .header("content-type","application/json")
                                .with(
                                        SecurityMockMvcRequestPostProcessors
                                                .user("admin")
                                                .authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))
                        ).content(
                                objectMapper.writeValueAsString(post)
                        )
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Flash"));
    }

    @Test
    void whenUpdateHeroNotFound()  throws Exception {
        CreateSuperHero post = new CreateSuperHero(
                "Flash","Barry","Velocidad"
        );

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/v1/superheroes/9999")
                                .header("content-type","application/json")
                                .with(
                                        SecurityMockMvcRequestPostProcessors
                                                .user("admin")
                                                .authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))
                                ).content(
                                        objectMapper.writeValueAsString(post)
                                )
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void whenDeleteHero() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/api/v1/superheroes/3")
                                .with(
                                        SecurityMockMvcRequestPostProcessors
                                                .user("admin")
                                                .authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))
                                )
                )
                .andExpect(status().isOk());

    }

    @Test
    void whenDeleteHeroNotFound() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/api/v1/superheroes/9999")
                                .with(
                                        SecurityMockMvcRequestPostProcessors
                                                .user("admin")
                                                .authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))
                                )
                )
                .andExpect(status().isNotFound());

    }

    @Test
    void whenSearch() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/superheroes")
                                .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status().isOk());
    }

    @Test
    void whenSearchWithFilterByName() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/superheroes?q=Batman")
                                .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.total").value(1));
    }

    @Test
    void whenSearchWithFilterByNameNotFound() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/superheroes?q=Arrow")
                                .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.total").value(0));
    }
}