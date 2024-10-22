package org.example.service;

import org.example.models.Tenista;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

public interface TenistaService {

    List<Tenista> getAll();

    Tenista getById(int id);

    Tenista create(Tenista tenista);

    Tenista update(int id, Tenista tenista);

    Tenista delete(int id);

    void loadCsv(File file);

    List<Tenista> getAllApi();

    Tenista getByIdApi(int id);

    Tenista createApi(Tenista tenista);

    Tenista updateApi(int id, Tenista tenista);

    Tenista deleteApi(int id);

}
