package org.example.mappers;

import org.example.api.createupdatedelete.RequestUCD;
import org.example.api.createupdatedelete.ResponseUCD;
import org.example.api.getAll.ResponseGetAll;
import org.example.api.getById.ResponseGetById;
import org.example.models.Mano;
import org.example.models.Tenista;

public class TenistaMappers {

    public static Tenista toTenistaFromUCD(ResponseUCD responseUCD){
        return Tenista.builder()
                .id(responseUCD.getId())
                .nombre(responseUCD.getNombre())
                .pais(responseUCD.getPais())
                .altura(responseUCD.getAltura())
                .peso(responseUCD.getPeso())
                .puntos(responseUCD.getPuntos())
                .mano(Mano.valueOf(responseUCD.getMano()))
                .fechaNacimiento(responseUCD.getFechaNacimiento())
                .created_at(responseUCD.getCreateAt())
                .update_at(responseUCD.getUpdateAt())
                .build();
    }

    public static Tenista toTenistaFromGetAll(ResponseGetAll responseGetAll){
        return Tenista.builder()
                .id(responseGetAll.getId())
                .nombre(responseGetAll.getNombre())
                .pais(responseGetAll.getPais())
                .altura(responseGetAll.getAltura())
                .peso(responseGetAll.getPeso())
                .puntos(responseGetAll.getPuntos())
                .mano(Mano.valueOf(responseGetAll.getMano()))
                .fechaNacimiento(responseGetAll.getFechaNacimiento())
                .created_at(responseGetAll.getCreateAt())
                .update_at(responseGetAll.getUpdateAt())
                .build();
    }

    public static Tenista toTenistaFromGetById(ResponseGetById responseGetById){
        return Tenista.builder()
                .id(responseGetById.getId())
                .nombre(responseGetById.getNombre())
                .pais(responseGetById.getPais())
                .altura(responseGetById.getAltura())
                .peso(responseGetById.getPeso())
                .puntos(responseGetById.getPuntos())
                .mano(Mano.valueOf(responseGetById.getMano()))
                .fechaNacimiento(responseGetById.getFechaNacimiento())
                .created_at(responseGetById.getCreateAt())
                .update_at(responseGetById.getUpdateAt())
                .build();
    }

    public static RequestUCD toRequestFromTenista(Tenista responseGetById){
        return RequestUCD.builder()
                .id(responseGetById.getId())
                .nombre(responseGetById.getNombre())
                .pais(responseGetById.getPais())
                .altura(responseGetById.getAltura())
                .peso(responseGetById.getPeso())
                .puntos(responseGetById.getPuntos())
                .mano(responseGetById.getMano().name())
                .fecha_nacimiento(responseGetById.getFechaNacimiento())
                .create_at(responseGetById.getCreated_at())
                .update_at(responseGetById.getUpdate_at())
                .build();
    }



}
