package dominio;


import java.time.LocalDate;
import java.sql.Date;
import java.util.Scanner;

import persistencia.MesaDAO;

public class Reserva {

	private int numMesa;
	private String dniUsuario;
	private int numPersonas;	
	private LocalDate fechaReserva;
	
	
	public int getNumMesa() {
		return numMesa;
	}
	
	
	public void setNumMesa(int numMesa) {
		this.numMesa = numMesa;
	}
	
	
	public String getDniUsuario() {
		return dniUsuario;
	}
	
	
	public void setDniUsuario(String dniUsuario) {
		this.dniUsuario = dniUsuario;
	}
	
	public int getNumPersonas() {
		return numPersonas;
	}
	
	
	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}
	
	public LocalDate getFechaReserva() {
		return fechaReserva;
	}
	
	public void setFechaReserva(LocalDate fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	
	
	public Reserva crearReserva(Usuario usuarioReserva, Mesa mesaReservada) {
		
		Reserva nuevaReserva = new Reserva();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Nombre del reservista: " + usuarioReserva.getNombre());
		nuevaReserva.setDniUsuario(usuarioReserva.getDni());
		
		
		System.out.println();
		nuevaReserva.setNumMesa(mesaReservada.getNumeroMesa());
		
		System.out.println("Numero de personas para reserva");
		nuevaReserva.setNumPersonas(sc.nextInt());
		
		if (nuevaReserva.getNumPersonas() > mesaReservada.getNumeroAsientos()) {
			System.out.println("No se puede reservar una mesa con tan pocos asientos");
			
			System.out.println("Solicite otra, o pida más asientos.");
			
			return null;
		}
		
		System.out.println("Introduzca la fecha de la reserva en el siguiente orden");
		System.out.println("YYYY (enter) MM (enter) DD");
		
		LocalDate fechaReserva = LocalDate.of(sc.nextInt(), sc.nextInt(), sc.nextInt());
		
		
		nuevaReserva.setFechaReserva(fechaReserva);
		
		return nuevaReserva;
	}

	
	public static java.sql.Date castingLocalDateToDate (LocalDate localDate) {		
		return Date.valueOf(localDate);
	}
	
	
	public Reserva actualizarReserva (Reserva reserva) {
		
		Scanner sc = new Scanner(System.in);
		
		int opcion = 0;
		
		do {
			System.out.println("1. Cambiar numero de personas");
			System.out.println("2. Salir");						
			
			opcion = sc.nextInt();
			
			switch (opcion) {
				case 1 -> {
					System.out.println("Cambiar numero de personas de la mesa: ");
					reserva.setNumPersonas(sc.nextInt());
					
					MesaDAO dao = new MesaDAO();
					
					Mesa mesa = new Mesa();
					
					dao.read(reserva.getNumMesa(), mesa);
					
					if (mesa.getNumeroAsientos() <= reserva.getNumPersonas()) {
						System.out.println("No hay tantas sillas disponibles");
						System.out.println("Introduzca menos cantidad de personas");
						
						reserva.setNumPersonas(sc.nextInt());		
					}
				}
				case 2 -> {
					System.out.println("Datos modificados: \n \n");
					System.out.println(reserva);
				}
				default -> {
					System.out.println("Opcion no valida");
				}		
			}
			
		} while (opcion != 2);
		
		
		return reserva;
	}
	
	
	
	@Override
	public String toString() { 
		return "DNI del Reservista: " + dniUsuario + "\n" +
		"Número mesa: " + numMesa + "\n" +
		"Fecha de la reserva: " + fechaReserva + "\n" +
		"Numero asientos " + numPersonas;
		
	}
	
	
	
	
}
