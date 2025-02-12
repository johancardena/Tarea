package Clases;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;

public class Usuario {
    private final MongoCollection<Document> usuarios;

    public Usuario() {
        MongoDatabase database = ConexionMongoDB.getDatabase();
        this.usuarios = database.getCollection("usuarios");
    }

    public boolean registrarUsuario(String nombre, String email, String password) {
        if (usuarios.find(eq("email", email)).first() != null) {
            return false;
        }

        Document nuevoUsuario = new Document("nombre", nombre)
                .append("email", email)
                .append("password", password); // Guarda la contraseña en texto plano

        usuarios.insertOne(nuevoUsuario);
        return true;
    }

    public boolean loginUsuario(String email, String password) {
        Document usuario = usuarios.find(eq("email", email)).first();

        if (usuario != null && password.equals(usuario.getString("password"))) {
            return true;
        }
        return false;
    }
}
