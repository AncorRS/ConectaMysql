package utilidades;

import java.sql.Connection;
import modelo.Libro;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Conexion {
	@SuppressWarnings("unused")
	private String host;
	@SuppressWarnings("unused")
	private String bd;
	@SuppressWarnings("unused")
	private String usr;
	@SuppressWarnings("unused")
	private String clave;
	private static Connection conexion;

	public static Connection conectar(String host, String bd, String usr, String clave) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + host + "/" + bd + "?useSSL=false";
			conexion = DriverManager.getConnection(url, usr, clave);
		} catch (SQLException e) {
			System.out.println("Error sql -> " + e.getMessage());
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("Error carga del driver -> " + e.getMessage());
		}

		return conexion;
	}

	public static ArrayList<Libro> getAllLibros() {
		ArrayList<Libro> resultado = new ArrayList<Libro>();
		// Statement, PreparedStatement, ResultSet, MetaData
		//String sql = "SELECT * FROM books where book_id=999"; //PARA PROBAR EL "NO HAY DATOS"
		String sql = "SELECT * FROM books";
		try {
			conexion = conectar("localhost:3306", "tienda", "root", "elrincon");
			Statement stmt = conexion.createStatement();
			ResultSet rS = stmt.executeQuery(sql);
			
			/*if (!rS.first()) // no hay registros  //ESTA LINEA NO NOS DEVUUELVE EL 1ER REGISTRO DEL RESULTSET
				return null;*/

			while (rS.next()) {
				Libro libro = new Libro();
				libro.setId(rS.getInt(1));
				libro.setAutor(rS.getString("author"));
				libro.setTitulo(rS.getString(2));
				libro.setPrecio(rS.getDouble("price"));
				libro.setCat_id(rS.getInt(5));
				resultado.add(libro);

			}
			//return resultado;  //posible linea duplicada por error, se ha comentado

		} catch (SQLException e) {
			System.out.println("Error leyendo libros -> " + e.getMessage());
		}
		return resultado;
	}

}
