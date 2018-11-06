package control;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Libro;
import utilidades.Conexion;

/**
 * Servlet implementation class Controlador
 */
@WebServlet("/conecta")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String mensaje = "";
	String rutaJSP = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controlador() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String opcion = request.getParameter("opcion");
		//String rutaJSP = "";
		switch (opcion) {
		case "1":
			rutaJSP = "mensaje";
			opcion1(request);
			break;
		case "2":
			//rutaJSP = "libros";
			//ArrayList<Libro> libros = Conexion.getAllLibros();
			//this.getServletContext().setAttribute("libros", libros);
			rutaJSP = (opcion2(request)!=null)?"libros":"mensaje";
			break;
		default:
			break;
		}
		request.getRequestDispatcher("jsp/" + rutaJSP + ".jsp").forward(request, response);
	}
	
	public ArrayList<Libro> opcion2(HttpServletRequest request) {
		ArrayList<Libro> libros = Conexion.getAllLibros();
	     if (libros ==null) {
	    	 //mensaje="No hay datos";
	         request.setAttribute("mensaje", "No hay datos");
	     }
		request.setAttribute("libros", libros);
		
		return libros;		
	}

	public void opcion1(HttpServletRequest request) {

		Connection cnx = Conexion.conectar("localhost:3306", "shop", "root", "puerta");
		if (cnx == null)
			mensaje = "Error de conexión...";
		else
			mensaje = "Conectado con exito...";
		request.setAttribute("mensaje", mensaje);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
