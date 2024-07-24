package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;

import dominio.Reserva;
import dominio.Usuario;


public class ReservaDAO {

	private Connection conexion;
	private final String USUARIO="adminRestaurante";
	private final String PASSWORD="12345";
	private final String MAQUINA="localhost";
	private final String BD = "restaurante";


	public ReservaDAO() {
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

	public void create(Reserva nuevaReserva) {
		
		String sql = "INSERT INTO reservas (dniUsuario, fecha, numMesa, numPersonas) VALUES (?, ?, ?, ?)";
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			
			sentencia.setString(1, nuevaReserva.getDniUsuario());
			sentencia.setDate(2, Reserva.castingLocalDateToDate(nuevaReserva.getFechaReserva()));
			sentencia.setInt(3, nuevaReserva.getNumMesa());
			sentencia.setInt(4, nuevaReserva.getNumPersonas());
	
			
			try {
				sentencia.executeUpdate();
			
				
			} catch (SQLIntegrityConstraintViolationException e) {
				System.out.println("Ya hay una reserva con estos datos.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			e.getErrorCode();
		}
		
		
	}
	

	public Reserva read (String dniReserva, Date fechaReserva, Reserva reserva) {
		
		String sql = "SELECT * FROM reservas WHERE dniUsuario= ? AND fecha=?";
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			
			sentencia.setString(1, dniReserva);
			sentencia.setDate(2, fechaReserva);
			
			
			ResultSet rs = sentencia.executeQuery();
			
			if (rs.next()) {
				String dniBD = rs.getString("dniUsuario");
				Date dateBD = rs.getDate("fecha");
				int numMesaBD = rs.getInt("numMesa");
				int numPersonasBD = rs.getInt("numPersonas");
				
				reserva.setDniUsuario(dniBD);
				reserva.setFechaReserva(dateBD.toLocalDate());
				reserva.setNumMesa(numMesaBD);
				reserva.setNumPersonas(numPersonasBD);
				
				
				return reserva;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			e.getErrorCode();
		}
		return null;
	}
	

	public void update (String dniReserva, Date fechaReserva, Reserva reserva) {
		
		String sql = "UPDATE reservas SET numPersonas = ? WHERE dniUsuario = ? AND fecha = ?";
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			
			sentencia.setInt(1, reserva.getNumMesa());
			sentencia.setString(2, dniReserva);
			sentencia.setDate(3, fechaReserva);
			sentencia.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
			e.getErrorCode();
		}	
	}
	
	
	
	
	public void delete (String dni, Date fecha) {
		
		String sql = "DELETE FROM reservas WHERE dniUsuario = ? AND fecha = ?";
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			
			sentencia.setString(1, dni);
			sentencia.setDate(2, fecha);
			sentencia.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			e.getErrorCode();
		}
		
		
	}
	
	
	
	
	
	public ArrayList<Reserva> listarPorFecha(ArrayList<Reserva> lista, Date fecha){
		
		String sql = "SELECT * FROM reservas WHERE fecha = ?";
		
		Reserva reserva = new Reserva();
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);	
			sentencia.setDate(1, fecha);
						
			ResultSet rs = sentencia.executeQuery();
			
			while (rs.next()) {
				int numMesa = rs.getInt("numMesa");
				String dniUsuario = rs.getString("dniUsuario");
				Date fechaBD = rs.getDate("fecha");
				int numPersonas = rs.getInt("numPersonas");
				
				reserva.setNumMesa(numMesa);
				reserva.setDniUsuario(dniUsuario);
				reserva.setFechaReserva(fechaBD.toLocalDate());
				reserva.setNumPersonas(numPersonas);
				
				
				lista.add(reserva);	
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			e.getErrorCode();
		}
	
		return lista;
	}
	
	
	
	

	
}
