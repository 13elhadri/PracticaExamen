package org.example.service;

import org.example.api.TenistaApiRest;
import org.example.cache.Cache;
import org.example.models.Tenista;
import org.example.repositories.RepositoryTenista;
import org.example.storages.StorageCsv;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class TenistaServiceImpl implements TenistaService{
    private final RepositoryTenista repo;
    private final TenistaApiRest api;
    private final StorageCsv storage;
    private final Cache cache;

    public TenistaServiceImpl(RepositoryTenista repo, TenistaApiRest api, StorageCsv storage, Cache cache) {
        this.repo = repo;
        this.api = api;
        this.storage = storage;
        this.cache = cache;
    }

    @Override
    public List<Tenista> getAll() {
        return repo.getAll();
    }

    @Override
    public Tenista getById(int id) {
        Optional<Tenista> _tenista= Optional.empty();
        return null;
    }

    @Override
    public Tenista create(Tenista tenista) {
        return null;
    }

    @Override
    public Tenista update(int id, Tenista tenista) {
        return null;
    }

    @Override
    public Tenista delete(int id) {
        return null;
    }

    @Override
    public void loadCsv(File file) {
        storage.loadCsv(file).flatMap(tenista -> Mono.fromCallable(() -> {
            // Guardar el tenista en el repositorio
            repo.create(tenista);
            // Retornar el tenista guardado para seguir el flujo
            return tenista;
        }));
    }

    @Override
    public List<Tenista> getAllApi() {
        return List.of();
    }

    @Override
    public Tenista getByIdApi(int id) {
        return null;
    }

    @Override
    public Tenista createApi(Tenista tenista) {
        return null;
    }

    @Override
    public Tenista updateApi(int id, Tenista tenista) {
        return null;
    }

    @Override
    public Tenista deleteApi(int id) {
        return null;
    }
}
