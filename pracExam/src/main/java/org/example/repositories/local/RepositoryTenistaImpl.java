package org.example.repositories.local;

import org.example.database.DataBaseConnection;
import org.example.models.Mano;
import org.example.models.Tenista;
import org.example.repositories.RepositoryTenista;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositoryTenistaImpl implements RepositoryTenista {
    private final Logger logger = LoggerFactory.getLogger(RepositoryTenistaImpl.class);
    private final DataBaseConnection db ;

    public RepositoryTenistaImpl(DataBaseConnection db) {
        this.db = db;
    }


    @Override
    public List<Tenista> getAll() {
        logger.debug("Obteniendo tenistas");
        List<Tenista> tenistas = new ArrayList<>();
        String query = "SELECT * FROM Tenista";

        db.use(connection -> {
            try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    Tenista tenista = mapTenistaQuery(resultSet);
                    tenistas.add(tenista);
                }
            }catch (Exception e){
                logger.error("Error Obteniendo tenistas" +e);
            }
            return tenistas;
        });

        return tenistas;
    }

    private Tenista mapTenistaQuery(ResultSet resultSet) throws SQLException {
        return Tenista.builder()
                .id(resultSet.getInt("id"))
                .nombre(resultSet.getString("nombre"))
                .pais(resultSet.getString("pais"))
                .altura(resultSet.getInt("altura"))
                .peso(resultSet.getInt("peso"))
                .puntos(resultSet.getInt("puntos"))
                .mano(Mano.valueOf(resultSet.getString("mano")))
                .fechaNacimiento(LocalDate.parse(resultSet.getString("fecha_nacimiento")))
                .created_at(LocalDateTime.parse(resultSet.getString("create_at")))
                .update_at(LocalDateTime.parse(resultSet.getString("update_at")))
                .build();
    }

    @Override
    public Optional<Tenista> getById(int id) {
        logger.debug("Obteniendo cliente por id " + id);
        String query = "SELECT * FROM Tenista WHERE id = ?";

        db.use(connection -> {
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setObject(1,id);
                try (ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()){
                        return Optional.of(mapTenistaQuery(resultSet));
                    }
                }
                return Optional.empty();
            }catch (Exception e){
                logger.error("Error Obteniendo tenista " + id + e);
                return Optional.empty();
            }
        }
        );
        return Optional.empty();
    }

    @Override
    public Tenista create(Tenista tenista) {
        logger.debug("Guardando tenista" + tenista.getNombre() );
        String query = "INSERT INTO Tenista(id,nombre,pais,altura,peso,puntos,mano,fecha_nacimiento,create_at,update_at) VALUES (?,?,?,?,?,?,?,?,?,?)";

        db.use(connection -> {
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setObject(1,tenista.getId());
                statement.setObject(2,tenista.getNombre());
                statement.setObject(3,tenista.getPais());
                statement.setObject(4,tenista.getAltura());
                statement.setObject(5,tenista.getPeso());
                statement.setObject(6,tenista.getPuntos());
                statement.setObject(7,tenista.getMano());
                statement.setObject(8,tenista.getFechaNacimiento());
                statement.setObject(9,tenista.getCreated_at());
                statement.setObject(10,tenista.getUpdate_at());

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0){
                    return tenista;
                }else {
                    return Optional.empty();
                }
            }catch (Exception e){
                logger.error("Error guardando tenista " + tenista.getNombre() + e);

            }
            return tenista;
        }
        );
        return tenista;
    }

    @Override
    public Optional<Tenista> update(int id, Tenista tenista) {
        logger.debug("Actualizando tenista con id " + id);
        String query = "UPDATE Tenista SET nombre = ?, pais = ?, altura = ?, peso = ?, puntos = ?, mano = ?, fecha_nacimiento = ?, update_at = ? WHERE id = ?";

        return db.use(connection -> {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tenista.getNombre());
                statement.setString(2, tenista.getPais());
                statement.setInt(3, tenista.getAltura());
                statement.setInt(4, tenista.getPeso());
                statement.setInt(5, tenista.getPuntos());
                statement.setString(6, tenista.getMano().name());  // Enum Mano como String
                statement.setString(7, tenista.getFechaNacimiento().toString());  // LocalDate como String
                statement.setString(8, tenista.getUpdate_at().toString());  // Actualizamos la columna `update_at`
                statement.setInt(9, id);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    return Optional.of(tenista);
                } else {
                    return Optional.empty();
                }
            } catch (Exception e) {
                logger.error("Error actualizando tenista con id " + id + ": " + e);
                return Optional.empty();
            }
        });
    }

    @Override
    public Optional<Tenista> delete(int id) {
        logger.debug("Eliminando tenista con id " + id);
        String query = "DELETE FROM Tenista WHERE id = ?";

        return db.use(connection -> {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    return Optional.empty();  // Devolvemos un objeto vacío o puedes devolver `null`
                } else {
                    return Optional.empty();
                }
            } catch (Exception e) {
                logger.error("Error eliminando tenista con id " + id + ": " + e);
                return Optional.empty();
            }
        });
    }

}
