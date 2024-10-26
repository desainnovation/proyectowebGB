package com.unu.proyectoWebGB.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

import com.unu.proyectoWebGB.beans.Autor;
import com.unu.proyectoWebGB.models.AutoresModel;

/**
 * Servlet implementation class AutoresController
 */
public class AutoresController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AutoresModel modelo = new AutoresModel();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AutoresController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("op") == null) {
			listar(request, response);
			return;
		}
		String operacion = request.getParameter("op");
		switch (operacion) {

		case "listar":
			listar(request, response);
			break;

		case "nuevo":
			request.getRequestDispatcher("/autores/nuevoAutor.jsp").forward(request, response);
			break;
		case "insertar":
			insertar(request, response);
			break;
		case "obtener":
			obtener(request, response);
			break;

		}
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		request.setAttribute("listaAutores", modelo.listarAutores());
		/*
		Iterator<Autor> it = modelo.listarAutores().iterator();
		while(it.hasNext()) {
			Autor a = it.next();
			System.out.println(a.getIdAutor()+" "
			+a.getNacionalidad()+" "
			+a.getNombre());
		}*/
		
		request.getRequestDispatcher("/autores/listaAutores.jsp").forward(request, response);
	}

	private void insertar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		try{
			
			Autor miAutor =  new Autor();
			miAutor.setNombre(request.getParameter("nombre"));
			miAutor.setNacionalidad(request.getParameter("nacionalidad"));
			if(modelo.insertarAutor(miAutor)>0) {
				request.getSession().setAttribute("exito",
						"autor registrado exitosamente");							
			}
			else {
				request.getSession().setAttribute("fracaso",
						"autor no registrado");				
			}
			
			response.sendRedirect(request.getContextPath()
					+"/AutoresController?op=listar");	
						
		}catch (Exception ex) {
			// 
			ex.getStackTrace();
		}
		
	}
	
	private void obtener(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			String id = request.getParameter("id");
			Autor miAutor = 
					modelo.obtenerAutor(Integer.parseInt(id));
			if(miAutor!= null) {
				request.setAttribute("autor", miAutor);
				request.getRequestDispatcher("/autores/editarAutor.jsp").forward(request, response);
			}
			else {
				response.sendRedirect(request.getContextPath()+"/error404.jsp");
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
