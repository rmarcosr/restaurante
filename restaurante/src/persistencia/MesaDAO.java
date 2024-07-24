package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

import dominio.Mesa;


public class MesaDAO {

	private Connection conexion;
	private final String USUARIO="adminRestaurante";
	private final String PASSWORD="12345";
	private final String MAQUINA="localhost";
	private final String BD = "restaurante";


	public MesaDAO() {
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

	public void create(Mesa nuevaMesa) {
		
		String sql = "INSERT INTO mesa (numMesa, ubicacion, numAsientos) VALUES (?, ?, ?)";
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			
			sentencia.setInt(1, nuevaMesa.getNumeroMesa());
			sentencia.setString(2, nuevaMesa.getUbicacion());
			sentencia.setInt(3, nuevaMesa.getNumeroAsientos());
	
			
			try {
				sentencia.executeUpdate();
			
				
			} catch (SQLIntegrityConstraintViolationException e) {
				System.out.println("El numero de mesa ya se encuentra ocupado.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			e.getErrorCode();
		}
		
		
	}
	

	public Mesa read (int numMesa, Mesa mesa) {
		Scanner sc = new Scanner(System.in);
		
		
		String sql = "SELECT * FROM mesa WHERE numMesa= ?";
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			
			sentencia.setInt(1, numMesa);
			
			ResultSet rs = sentencia.executeQuery();
			
			if (rs.next()) {
				int numMesaBD = rs.getInt("numMesa");
				String ubicacion = rs.getString("ubicacion");
				int numAsientos = rs.getInt("numAsientos");
				
				mesa.setNumeroAsientos(numAsientos);
				mesa.setNumeroMesa(numMesaBD);
				mesa.setUbicacion(ubicacion);
				
				return mesa;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			e.getErrorCode();
		}
		return null;
	}
	
	
	
	public void update (Mesa mesaCambiada) {
		
		String sql = "UPDATE mesa SET numAsientos = ?, ubicacion = ? WHERE numMesa = ? ";
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			
			sentencia.setInt(1, mesaCambiada.getNumeroAsientos());
			sentencia.setString(2, mesaCambiada.getUbicacion());
			sentencia.setInt(3, mesaCambiada.getNumeroMesa());
			sentencia.executeUpdate();
			
		
				
		} catch (SQLException e) {
			e.printStackTrace();
			e.getErrorCode();
		}	
	}
	

	
	public void delete (int numeroMesa) {
		
		String sql = "DELETE FROM mesa WHERE numMesa = ?";
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			
			sentencia.setInt(1, numeroMesa);			
			sentencia.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			e.getErrorCode();
		}
		
		
	}
	
}
