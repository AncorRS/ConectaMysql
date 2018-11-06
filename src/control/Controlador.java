package control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;

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
			
		case "login":	
			login(request);
			
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

	public void login(HttpServletRequest request) {
		
		System.out.println("entra cheLogin");
		try {
			Connection cnx = Conexion.conectar("localhost:3306", "tienda", "root", "elrincon");
			PreparedStatement ps = (PreparedStatement) cnx.prepareStatement("SELECT * FROM usuarios WHERE usuario=? AND clave=?");
			ps.setString(1, request.getParameter("usu"));
			ps.setString(2, request.getParameter("con"));
			ResultSet rs = ps.executeQuery();
			if(rs.first()) {
				rutaJSP = "AccesoCorrecto";
			}else {
				rutaJSP = "UsuarioIncorrecto";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	public void opcion1(HttpServletRequest request) {

		Connection cnx = Conexion.conectar("localhost:3306", "tienda", "root", "elrincon");
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
