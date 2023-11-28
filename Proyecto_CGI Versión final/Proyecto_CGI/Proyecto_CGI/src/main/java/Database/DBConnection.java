/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author TeamPiña
 */
public class DBConnection {
    private Connection connection;
    private String databaseName = "CGIDataBase"; // Nombre de la base de datos

    public boolean setConnection() {
        try {
            // Cargar el driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión con el servidor MySQL
            String url = "jdbc:mysql://localhost:3306/";
           // String url = "jdbc:mysql://localhost:3306/cgidatabase";
            connection = DriverManager.getConnection(url, "root", "Sami*3010");
            createDatabaseIfNotExists();
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el driver JDBC: " + e.getMessage());
        } catch (SQLException error) {
            System.out.println("Error al conectar a la base de datos: " + error.getMessage());
        }
        return false;
    }


    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if(connection != null) {
                connection.close();
                System.out.println("Conexión cerrada correctamente.");
            }
        } catch (SQLException error) {
            System.out.println("Error al cerrar la conexión: " + error.getMessage());
        }
    }

    private void createDatabaseIfNotExists() {
        try {
            if (connection != null) {
                Statement statement = connection.createStatement();

                // Crear la base de datos si no existe
                String createDBQuery = "CREATE DATABASE IF NOT EXISTS " + databaseName;
                statement.executeUpdate(createDBQuery);

                System.out.println("Base de datos creada exitosamente.");
            } else {
                System.out.println("No hay conexión a la base de datos.");
            }
        } catch (SQLException error) {
            System.out.println("Error al crear la base de datos: " + error.getMessage());
        }
    }
}