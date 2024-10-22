package org.example.api.getById;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGetById {
    @Getter
    @JsonProperty("id")
    private int id;

    @Getter
    @JsonProperty("nombre")
    private String nombre;

    @Getter
    @JsonProperty("pais")
    private String pais;

    @Getter
    @JsonProperty("altura")
    private int altura;

    @Getter
    @JsonProperty("peso")
    private int peso;

    @Getter
    @JsonProperty("puntos")
    private int puntos;

    @Getter
    @JsonProperty("mano")
    private String mano;

    @JsonProperty("fecha_nacimiento")
    private LocalDate fecha_nacimiento;

    @JsonProperty("create_at")
    private LocalDateTime create_at;

    @JsonProperty("update_at")
    private LocalDateTime update_at;

    public LocalDate getFechaNacimiento() {
        return fecha_nacimiento;
    }

    public LocalDateTime getCreateAt() {
        return create_at;
    }

    public LocalDateTime getUpdateAt() {
        return update_at;
    }
}
