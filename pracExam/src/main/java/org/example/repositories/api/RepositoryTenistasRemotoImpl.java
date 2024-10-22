package org.example.repositories.api;

import org.example.api.TenistaApiRest;
import org.example.api.createupdatedelete.RequestUCD;
import org.example.mappers.TenistaMappers;
import org.example.models.Tenista;
import org.example.repositories.RepositoryTenista;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class RepositoryTenistasRemotoImpl implements RepositoryTenista {
    private final TenistaApiRest tenistasApiRest;

    public RepositoryTenistasRemotoImpl(TenistaApiRest tenistasApiRest) {
        this.tenistasApiRest = tenistasApiRest;
    }


    @Override
    public List<Tenista> getAll() {
        var call= tenistasApiRest.getAll();

        try {
            var reponse = call.get();
            return reponse.stream()
                    .map(TenistaMappers::toTenistaFromGetAll)
                    .toList();

        }catch (Exception e){
                return List.of();
        }
    }

    @Override
    public Optional<Tenista> getById(int id) {
        var call = tenistasApiRest.getById(id);

        try {
            var reponse = call.get();
            return Optional.of(TenistaMappers.toTenistaFromGetById(reponse));

        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Tenista create(Tenista tenista) {
        RequestUCD requestUCD = TenistaMappers.toRequestFromTenista(tenista);
        var call = tenistasApiRest.create(requestUCD);

        try {
            var reponse = call.get();
            return TenistaMappers.toTenistaFromUCD(reponse);

        }catch (Exception e){
            return Tenista.builder().build();
        }
    }

    @Override
    public Optional<Tenista> update(int id, Tenista tenista) {
        RequestUCD requestUCD = TenistaMappers.toRequestFromTenista(tenista);
        var call = tenistasApiRest.update(id,requestUCD);

        try {
            var reponse = call.get();
            return Optional.of(TenistaMappers.toTenistaFromUCD(reponse));
        }catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Tenista> delete(int id) {
        var call = tenistasApiRest.delete(id);

        try {
            var reponse= call.get();
            return Optional.of(TenistaMappers.toTenistaFromUCD(reponse));

        }catch (Exception e){
            return Optional.empty();
        }
    }
}
