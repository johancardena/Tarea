package Clases;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class Alquilerr {
    private final MongoCollection<Document> alquileres;

    public Alquilerr() {
        ConexionMongoDB MongoDBConnection;
        MongoDatabase database = ConexionMongoDB.getDatabase();
        this.alquileres = database.getCollection("alquileres");
    }

    // Agregar un nuevo alquiler
    public boolean agregarAlquiler(String cliente, String vehiculo, String fechaInicio, String fechaFin) {
        Document nuevoAlquiler = new Document("cliente", cliente)
                .append("vehiculo", vehiculo)
                .append("fechaInicio", fechaInicio)
                .append("fechaFin", fechaFin);
        alquileres.insertOne(nuevoAlquiler);
        return true;
    }

    // Eliminar un alquiler basado en el cliente
    public boolean eliminarAlquiler(String cliente) {
        return alquileres.deleteOne(eq("cliente", cliente)).getDeletedCount() > 0;
    }

    // Obtener todos los alquileres
    public List<Alquiler> obtenerAlquileres() {
        List<Alquiler> lista = new ArrayList<>();
        for (Document doc : alquileres.find()) {
            lista.add(new Alquiler(
                    doc.getString("cliente"),
                    doc.getString("vehiculo"),
                    doc.getString("fechaInicio"),
                    doc.getString("fechaFin")
            ));
        }
        return lista;
    }

    // Actualizar un alquiler basado en el cliente
    public boolean actualizarAlquiler(Alquiler alquilerSeleccionado) {
        // Crear un filtro para buscar el alquiler por cliente
        Document filtro = new Document("cliente", alquilerSeleccionado.getCliente());

        // Crear un documento con los nuevos valores a actualizar
        Document nuevosDatos = new Document("vehiculo", alquilerSeleccionado.getVehiculo())
                .append("fechaInicio", alquilerSeleccionado.getFechaInicio())
                .append("fechaFin", alquilerSeleccionado.getFechaFin());

        // Realizar la actualización
        return alquileres.updateOne(filtro, new Document("$set", nuevosDatos)).getModifiedCount() > 0;
    }
}
