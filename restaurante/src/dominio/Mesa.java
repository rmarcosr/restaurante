package dominio;


import java.util.Scanner;

public class Mesa {

	private int numeroMesa;
	private String ubicacion;
	private int numeroAsientos;
	
	
	
	public int getNumeroMesa() {
		return numeroMesa;
	}
	
	public void setNumeroMesa(int numeroMesa) {
		this.numeroMesa = numeroMesa;
	}
	
	
	
	public String getUbicacion() {
		return ubicacion;
	}
	
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	
	
	
	public int getNumeroAsientos() {
		return numeroAsientos;
	}
	
	public void setNumeroAsientos(int numeroAsientos) {
		this.numeroAsientos = numeroAsientos;
	}
	
	
	public Mesa() {
		
	}
	
	
	public Mesa agregarMesa (Mesa nuevaMesa) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Numero de la mesa: ");
		nuevaMesa.setNumeroMesa(sc.nextInt());
		
		System.out.println("Numero de sillas ");
		nuevaMesa.setNumeroAsientos(sc.nextInt());
		
		System.out.println("Introduzca su ubicación ");
		nuevaMesa.setUbicacion(sc.next());
			
		return nuevaMesa;
	}
	
	
	public Mesa actualizarMesa (Mesa mesaCambiada) {
		
		Scanner sc = new Scanner(System.in);
		
		int opcion = 0;
		
		do {
			System.out.println("1. Cambiar asientos");
			System.out.println("2. Cambiar ubicacion");
			System.out.println("3. Salir");					
			
			opcion = sc.nextInt();
			
			switch (opcion) {
				case 1 -> {
					System.out.println("Numero de asientos: ");
					mesaCambiada.setNumeroAsientos(sc.nextInt());
				}
				case 2 -> {
					System.out.println("Nueva ubicacion: ");
					mesaCambiada.setUbicacion(sc.next());
				}
				case 3 -> {
					System.out.println("Datos modificados: \n");
					System.out.println(mesaCambiada);
					
				}
				default -> {
					System.out.println("Opcion no valida");
				}		
			}
			
		} while (opcion != 3);
				
		return mesaCambiada;
	}
	
	
	public static int eliminarMesa() {
		Scanner sc = new Scanner(System.in);
		int numeroMesa;
		
		System.out.println("Número de la mesa a eliminar");
		
		numeroMesa = sc.nextInt();
		
		return numeroMesa;
		
	}
	

	
	
	@Override
	public String toString() {

		return "Numero de mesa: " + numeroMesa + "\nAsientos: " + numeroAsientos + "\nUbicacion: " + ubicacion;
	}
	
}
