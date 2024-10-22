package org.example.models;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class Tenista {
    private int id;
    private String nombre;
    private String pais;
    private int altura;
    private int peso;
    private int puntos;
    private Mano mano;
    private LocalDate fechaNacimiento;
    private LocalDateTime created_at;
    private LocalDateTime update_at;
}

