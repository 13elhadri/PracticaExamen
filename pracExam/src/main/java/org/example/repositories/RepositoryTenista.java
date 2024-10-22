package org.example.repositories;

import org.example.models.Tenista;

import java.util.List;
import java.util.Optional;

public interface RepositoryTenista {

    List<Tenista> getAll();

    Optional<Tenista> getById(int id);

    Tenista create(Tenista tenista);

    Optional<Tenista> update(int id, Tenista tenista);

    Optional<Tenista> delete(int id);


}
