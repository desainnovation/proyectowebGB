package com.unu.proyectoWebGB.models;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unu.proyectoWebGB.beans.*;

public class AutoresModel extends Conexion{

	CallableStatement cs;
	ResultSet rs;
	/*
	public List<Autor> listarAutores(){
		
		ArrayList<Autor> autores = new ArrayList<>();
		autores.add(new Autor(1,"García Marquez", "Colombiana"));
		autores.add(new Autor(2,"Borges", "Argentina"));
		autores.add(new Autor(3,"Allende", "Chilena"));
		return autores;
	}*/
	
	public List<Autor> listarAutores() throws SQLException{
	
		try {
			
			
			List<Autor> lista = new ArrayList<>();
			String sql = "CALL sp_listarAutor()";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while(rs.next()) {
				Autor autor = new Autor();
				autor.setIdAutor(rs.getInt("idautor"));
				autor.setNombre(rs.getString("nombre"));
				autor.setNacionalidad(rs.getString("nacionalidad"));
				lista.add(autor);
			}
			this.cerrarConexion();
			return lista;
			
		}catch (Exception e) {
			e.printStackTrace();
			this.cerrarConexion();
			return null;
		}
		
	}
	
	
}
