package presentacion;

import java.util.Scanner;

public class InterfazUsuario {

	
	public static void menu () {
		
		System.out.println("********************");
		System.out.println("Opciones de Usuario:");
		System.out.println("\t 1. Registrar nuevo usuario.");
		System.out.println("\t 2. Iniciar sesión.");
		System.out.println("\t 3. Modificar usuario.");
		System.out.println("\t 4. Eliminar usuario.");
		System.out.println("********************");
		System.out.println("Opciones de Mesa:");
		System.out.println("\t 5. Registrar nueva mesa.");
		System.out.println("\t 6. Modificar mesa.");
		System.out.println("\t 7. Eliminar mesa.");
		System.out.println("********************");
		System.out.println("Opciones de Reserva:");
		System.out.println("\t 8. Registrar nueva reserva.");
		System.out.println("\t 9. Modificar reserva.");
		System.out.println("\t 10. Eliminar reserva.");
		System.out.println("\t 11. Ver reservas por fecha.");
		System.out.println("********************");
		System.out.println("\t 12. Salir.");
	}
	
	
	
	public static void saltoLinea() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Pulse intro para continuar...");
		sc.nextLine();
		
	}
	
	
	
	
}
