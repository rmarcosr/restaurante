package dominio;

import java.util.Scanner;

public class Usuario {

	private String dni;
	private String nombre;
	private String apellido;
	
	
	
	
	
	/* GETTERS Y SETTERS */
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		if (dni.length() > 9) {
			dni = dni.substring(0, 9);
			this.dni = dni;
		} 		
		this.dni = dni;
	}



	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		if (nombre.length() > 100) {
			nombre = nombre.substring(0, 100);
			this.nombre = nombre;
		} 		
		this.nombre = nombre;
	}
	
	
	
	
	
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		if (apellido.length() > 100) {
			apellido = apellido.substring(0, 100);
			this.apellido = apellido;
		} 		
		this.apellido = apellido;
	}


	
	
	/* CONSTRUCTORES */

	public Usuario() {
		
	}
	
	
	/* METODOS */
	
	
	public Usuario registrarUsuarios (Usuario nuevoUsuario) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Nombre del nuevo usuario: ");
		nuevoUsuario.setNombre(sc.next());
		
		System.out.println("Apellido del nuevo usuario: ");
		nuevoUsuario.setApellido(sc.next());
		
		System.out.println("DNI del nuevo usuario: ");
		nuevoUsuario.setDni(sc.next());
		
		
		return nuevoUsuario;
	}
	
	
	
	public static String iniciarSesion() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("DNI Del usuario: ");
		String dni = sc.next();
		
		if (dni.length() > 9) {
			dni = dni.substring(0, 9);
		}
		
		return dni;
		
		
	}
	
	
	public Usuario actualizarUsuario (Usuario usuarioModificar) {
		
		Scanner sc = new Scanner(System.in);
		
		int opcion = 0;
		
		do {
			System.out.println("1. Cambiar nombre");
			System.out.println("2. Cambiar apellido");
			System.out.println("3. Salir");						// Proximas versiones harán más complejos los datos del usuario.
			
			opcion = sc.nextInt();
			
			switch (opcion) {
				case 1 -> {
					System.out.println("Nuevo nombre: ");
						usuarioModificar.setNombre(sc.next());
				}
				case 2 -> {
					System.out.println("Nuevo apellido: ");
						usuarioModificar.setApellido(sc.next());
				}
				case 3 -> {
					System.out.println("Datos modificados: \n \n");
					System.out.println(usuarioModificar);
				}
				default -> {
					System.out.println("Opcion no valida");
				}		
			}
			
		} while (opcion != 3);
				
		return usuarioModificar;
	}
	
	
	
	
	
	
	@Override
	public String toString() {
	
		return "Nombre: " + nombre + " " + apellido + "\nDNI: " + dni;
	}
	
	
	
	

	
	
	
}
