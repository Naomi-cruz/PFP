/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import Database.DBConnection;
import Database.DBTables;
import Views.Login;
import java.sql.Connection;

/**
 *
 * @author Usuario
 */
public class Main {
    
    public static void main(String[] args) {
        Login loginWindow = new Login();
        
        loginWindow.setVisible(true);
        
        DBConnection dbConnection = new DBConnection();
        DBTables dbTables = new DBTables();
        
        // Intentar establecer la conexión
        if (dbConnection.setConnection()) {
            System.out.println("Conexión exitosa a la base de datos desde el Main.");
            
            // Obtener la conexión
            Connection connection = dbConnection.getConnection();
            
            // Crear las tablas si la conexión es exitosa
            if (connection != null) {
                dbTables.createTables(connection);
                
                // Cerrar la conexión después de crear las tablas
                dbConnection.closeConnection();
            }
        }
        
    }
}
