package org.example.storages;

import org.example.models.Tenista;
import reactor.core.publisher.Flux;

import java.io.File;

public interface StorageCsv {

    Flux<Tenista> loadCsv(File file);
}
