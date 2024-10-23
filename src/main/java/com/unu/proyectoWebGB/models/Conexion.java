package com.unu.proyectoWebGB.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private String url = 
			"jdbc:mysql://localhost:3306/bibliotecapoo2";
	private String usuario="root";
	private String contrasena="123456";
	protected Connection conexion;
	
	public void abrirConexion() {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(
					url,usuario,contrasena);
			System.out.println("La conexion a la base de datos fue exitosa");
		}catch (ClassNotFoundException | SQLException e) {
			//System.out.println("error: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void cerrarConexion() {
		try {
			
			if(conexion!= null && !conexion.isClosed()) {
				conexion.close();
				System.out.println("Conexion cerrada");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
