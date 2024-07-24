package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import dominio.Usuario;



public class UsuarioDAO {

	private Connection conexion;
	private final String USUARIO="adminRestaurante";
	private final String PASSWORD="12345";
	private final String MAQUINA="localhost";
	private final String BD = "restaurante";


	public UsuarioDAO() {
		conexion = conectarConBD();
	}

	
	private Connection conectarConBD() {
		Connection con = null;
		
		String url = "jdbc:mysql://"+ MAQUINA + "/" + BD;
		try {
			con = DriverManager.getConnection(url, USUARIO, PASSWORD);
			//System.out.println("Se ha establecido conexion");
			
		} catch (SQLException e) {
			//System.out.println("Error al conectar con la BD");
		}
		
		return con;
	}

	public void create(Usuario nuevoUsuario) {
		
		String sql = "INSERT INTO usuario (nombre, apellido, dni) VALUES (?, ?, ?)";
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			
			sentencia.setString(1, nuevoUsuario.getNombre());
			sentencia.setString(2, nuevoUsuario.getApellido());
			sentencia.setString(3, nuevoUsuario.getDni());
	
			
			try {
				sentencia.executeUpdate();
			
				
			} catch (SQLIntegrityConstraintViolationException e) {
				System.out.println("El DNI ya se encuentra en nuestra base de datos");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			e.getErrorCode();
		}
		
		
	}
	

	public Usuario read (String dniBuscar, Usuario usuarioBuscado) {
		
		String sql = "SELECT * FROM usuario WHERE dni= ?";
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			
			sentencia.setString(1, dniBuscar);
			
			ResultSet rs = sentencia.executeQuery();
			
			if (rs.next()) {
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellido");
				String dni = rs.getString("dni");
				
				
				usuarioBuscado.setApellido(apellidos);
				usuarioBuscado.setDni(dni);
				usuarioBuscado.setNombre(nombre);
			
				
				
				return usuarioBuscado;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			e.getErrorCode();
		}
		return null;
	}
	
	
	
	public void update (Usuario usuarioCambiado) {
		
		String sql = "UPDATE usuario SET nombre = ?, apellido = ? WHERE dni = ? ";
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			
			sentencia.setString(1, usuarioCambiado.getNombre());
			sentencia.setString(2, usuarioCambiado.getApellido());
			sentencia.setString(3, usuarioCambiado.getDni());
			sentencia.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
			e.getErrorCode();
		}	
	}
	
	
	
	public void delete (String dni) {
		
		String sql = "DELETE FROM usuario WHERE dni = ?";
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			
			sentencia.setString(1, dni);			
			sentencia.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			e.getErrorCode();
		}
		
		
	}
	
	

	
	
	
	
	
}

