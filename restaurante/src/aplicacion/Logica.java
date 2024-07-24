package aplicacion;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import dominio.Mesa;
import dominio.Reserva;
import dominio.Usuario;
import persistencia.MesaDAO;
import persistencia.ReservaDAO;
import persistencia.UsuarioDAO;
import presentacion.InterfazUsuario;

public class Logica {

	public static void opcion (int opcion, Usuario usuarioIniciado) {
		Scanner sc = new Scanner(System.in);
		
		
		do {
			InterfazUsuario.menu();
			opcion = sc.nextInt();
		
			switch (opcion) {
		

				case 1 -> {
					Usuario nuevoUsuario = new Usuario();
					nuevoUsuario.registrarUsuarios(nuevoUsuario);
					
					UsuarioDAO dao = new UsuarioDAO();
					
					dao.create(nuevoUsuario);
					System.out.println("\n \n \n");
					System.out.println(nuevoUsuario);
					
					InterfazUsuario.saltoLinea();
			
				}
				
				case 2 -> {
					
					String dni = Usuario.iniciarSesion();
					
					UsuarioDAO dao = new UsuarioDAO();
					
					usuarioIniciado = new Usuario();
					
					usuarioIniciado = (dao.read(dni, usuarioIniciado));
					
					System.out.println("Hola "+ usuarioIniciado.getNombre() + " bienvenido/a.");
					
					InterfazUsuario.saltoLinea();
				}
				
				case 3 -> {
					
					if (usuarioIniciado == null) {
						System.out.println("Se debe iniciar sesión primero");
					
					} else {
						UsuarioDAO dao = new UsuarioDAO();
						
						usuarioIniciado = usuarioIniciado.actualizarUsuario(usuarioIniciado);
						
						dao.update(usuarioIniciado);
					}
					
					InterfazUsuario.saltoLinea();
					
					
				}
				
				case 4 -> {
					
					if (usuarioIniciado == null) {
						System.out.println("Se debe iniciar sesión primero");
					
					} else {
						
						UsuarioDAO dao = new UsuarioDAO();
						
						System.out.println("Realmente quiere dar de baja a su usuario Y/N");
						String respuesta = sc.next().toUpperCase();
						Boolean operacion = false;
						
						
						
						if (respuesta.equals("Y"))  {
							
							dao.delete(usuarioIniciado.getDni());
							
							System.out.println("Se ha dado de baja a su usuario");
							
							usuarioIniciado = null;
							
							operacion = true;
							
						} 
						
						if (respuesta.equals("N")) {
							
							System.out.println("Se ha cancelado la operación");
							
							operacion = true;
						} 
						
						if (operacion == false) {
							System.out.println("No se ha introducido una opción valida");
						}	
					}
					
					InterfazUsuario.saltoLinea();
				}
				
				case 5 ->{
					
					Mesa nuevaMesa = new Mesa();
					
					nuevaMesa.agregarMesa(nuevaMesa);
					
					MesaDAO dao = new MesaDAO();
					
					dao.create(nuevaMesa);
					System.out.println("\n \n \n");
					System.out.println(nuevaMesa);
					
					InterfazUsuario.saltoLinea();
					
				}
				
				case 6 -> {
					
					Mesa mesaMoficada = new Mesa();
					MesaDAO dao = new MesaDAO();
					
					System.out.println("Numero de la mesa a modificar.");
					
					int numeroMesa = sc.nextInt();
					
					dao.read(numeroMesa, mesaMoficada);
					
					if (mesaMoficada.getNumeroMesa() == 0) {
						System.out.println("La mesa no existe");
					
					} else {
						
						mesaMoficada.actualizarMesa(mesaMoficada);
						dao.update(mesaMoficada);	
					}
								
					InterfazUsuario.saltoLinea();
				}
				
				case 7 -> {
					
					MesaDAO dao = new MesaDAO();
					
					dao.delete(Mesa.eliminarMesa());
					
					System.out.println("Se ha eliminado la mesa indicada.");
					
					InterfazUsuario.saltoLinea();
				}
				
				case 8 -> {
					if (usuarioIniciado == null) {
						System.out.println("Se debe iniciar sesión primero");
					
					} else {
						Reserva nuevaReserva = new Reserva();
						
						Mesa mesaReserva = new Mesa();
						
						MesaDAO dao = new MesaDAO();
						
						System.out.println("Seleccione el número de mesa");
						
						dao.read(sc.nextInt(), mesaReserva);
						
						if (mesaReserva != null) {
							
							nuevaReserva = nuevaReserva.crearReserva(usuarioIniciado, mesaReserva);
							
							if (nuevaReserva == null) {
								
								System.out.println("No se puede solicitar una reserva para la fecha y mesa indicada");
								
							} else {
								
								System.out.println(nuevaReserva);
								
								ReservaDAO reservaDao = new ReservaDAO();
								
								reservaDao.create(nuevaReserva);
							}
						}		
					}
					InterfazUsuario.saltoLinea();		
				}
				
				case 9 -> {
					if (usuarioIniciado == null) {
						System.out.println("Se debe iniciar sesión primero");
					
					} else {
						System.out.println("Introduzca la fecha de la reserva en el siguiente orden");
						System.out.println("YYYY (enter) MM (enter) DD");
						
						LocalDate fechaReserva = LocalDate.of(sc.nextInt(), sc.nextInt(), sc.nextInt());
							
						Date fechaSQL = Reserva.castingLocalDateToDate(fechaReserva);
						
						Reserva reserva = new Reserva();
						
						ReservaDAO dao = new ReservaDAO();
						
						reserva = dao.read(usuarioIniciado.getDni(), fechaSQL, reserva);
						
						reserva = reserva.actualizarReserva(reserva);
						
						dao.update(usuarioIniciado.getDni(), fechaSQL, reserva);
					}
				InterfazUsuario.saltoLinea();	
				}
				
				
				
				
				case 10 -> {
					if (usuarioIniciado == null) {
						System.out.println("Se debe iniciar sesión primero");
					
					} else {
						System.out.println("Introduzca la fecha de la reserva en el siguiente orden");
						System.out.println("YYYY (enter) MM (enter) DD");
						
						LocalDate fechaReserva = LocalDate.of(sc.nextInt(), sc.nextInt(), sc.nextInt());
							
						Date fechaSQL = Reserva.castingLocalDateToDate(fechaReserva);
						
						ReservaDAO dao = new ReservaDAO();
						
						dao.delete(usuarioIniciado.getDni(), fechaSQL);
						
						System.out.println("Se ha eliminado su reserva el día: ");
						System.out.println(fechaReserva); ;
						}
					
					InterfazUsuario.saltoLinea();	
					}
					
					
				
				
				case 11 -> {
					
					ArrayList<Reserva> listaReserva = new ArrayList<Reserva>();
					
					ReservaDAO dao = new ReservaDAO();
					
					System.out.println("Introduzca la fecha de la reserva en el siguiente orden");
					System.out.println("YYYY (enter) MM (enter) DD");
					
					LocalDate fechaReserva = LocalDate.of(sc.nextInt(), sc.nextInt(), sc.nextInt());
						
					Date fechaSQL = Reserva.castingLocalDateToDate(fechaReserva);
					
					
					dao.listarPorFecha(listaReserva, fechaSQL);
				
					for (Reserva reservaArrayList : listaReserva) {
						System.out.println("\n");
						System.out.println(reservaArrayList);
						System.out.println("\n");
					}	
				}
				
				case 12 -> {
					System.out.println("Adios");			
				}
				

				
				
		
				default -> {
					System.out.println("Opcion no valida");
				}
			}
			
		} while (opcion != 12);
	}
	
	
	
	
}
