package org.example.storages;

import org.example.models.Tenista;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.io.*;
import java.sql.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class StorageCsvImpl implements StorageCsv {
    @Override
    public Flux<Tenista> loadCsv(File file) {
        return Flux.<Tenista>create(e -> {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                reader.lines().skip(1)
                        .forEach(line -> {
                            Tenista tenista = parseline(List.of(line.split(",")));
                            e.next(tenista);
                        });
                e.complete();

            } catch (Exception ex) {
                e.error(ex);
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }

    private Tenista parseline(List<String> linea) {
        return Tenista.builder()
                .id(Integer.getInteger(linea.get(0)))
                .nombre(linea.get(1))
                .pais(linea.get(2))
                .altura(Integer.getInteger(linea.get(3)))
                .peso(Integer.getInteger(linea.get(4)))
                .puntos(Integer.getInteger(linea.get(5)))
                .fechaNacimiento(LocalDate.parse(linea.get(6)))
                .created_at(LocalDateTime.now())
                .update_at(LocalDateTime.now())
                .build();
    }

}
