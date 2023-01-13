package ar.edu.unlam.pb1;

import java.util.Scanner;

public class TestVeterinaria {

	private static final int SALIR= 9;
	private static final int CANTIDAD_MAXIMA_ATENCIONES = 5;

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		
		String nombreVeterinaria = "UNLaM-Vet";
		
		mostrarMensaje("Bienvenido a "+nombreVeterinaria);
		
		Veterinaria veterinaria = new Veterinaria(nombreVeterinaria, CANTIDAD_MAXIMA_ATENCIONES);

		int opcion = 0;

		do {
			opcion = seleccionarOpcion(teclado);

			switch (opcion) {
			case 1:
				// Registra una atencion con los datos ingresados por el empleado e informa el resultado
				if(crearAtencion(teclado,veterinaria))
					mostrarMensaje("Se registró la atención exitosamente :-D");
				else
					mostrarMensaje("Lo sentimos, no se pudo registrar la atención :-(");
				
				break;
			case 2:
				// Se solicita el ingreso del id y se muestra la informacion de la atencion
				// encontrada. Si no se encuentra, mostrar un mensaje
				buscarAtencionPorSuID(teclado, veterinaria);
				break;
			case 3:
				// Ingresar la especie de mascota para listar las atenciones que correspondan.
				// Si no hay atenciones indicar lo propio
				listarAtencionesPorEspecieDeterminada(teclado, veterinaria);
				
				break;
			case 4:
				// Se solicita el ingresdo del id de la atencion y se elimina. Indicar el
				// resultado de la operacion
				eliminarUnaAtencionPorId(teclado,veterinaria);
				
				break;
			case 5:
				// Listar las atenciones realizadas y las disponibles
				informarLaCantidadAtencionesRealizadasYDisponibles(veterinaria);
				break;
			case 6:
				// Informar el total de todas las atenciones realizadas. Debe inciar sesión para realizar esta operacion.
				// Si las credenciales son invalidas, indicarlo y volver al menu principal.
				informarMontoTotalDeAtencionesRealizadas(teclado, veterinaria);
				break;
			case 7:
				// Informar por pantalla atenciones ordenadas en forma decreciente. Si no hay atenciones mostrar un mensaje aclaratorio.
				// Si las credenciales son invalidas, indicarlo y volver al menu principal.
				listarAtencionesDescendente(teclado, veterinaria);
				break;
			case SALIR:
				mostrarMensaje("Gracias por utilizar el sistema");		
				break;
			}

		} while (opcion != SALIR);
	
		teclado.close();
	}


	// Informar por pantalla atenciones ordenadas en forma decreciente. Si no hay atenciones mostrar un mensaje aclaratorio.
	// Si las credenciales son invalidas, indicarlo y volver al menu principal.
	
	private static void listarAtencionesDescendente(Scanner teclado, Veterinaria veterinaria) {
		if(inicioSesion(teclado, veterinaria)) {
			
			Atencion atencionesOrdenadas[]= veterinaria.ordenarAtencionesPorMontoDescendente();
			for(int i=0; i<atencionesOrdenadas.length; i++) {
				if(atencionesOrdenadas[i]!=null) {
					mostrarMensaje(atencionesOrdenadas[i].toString());
				}
			}
		}
		
		
		
	}
	//*****************************************************************
	// Informar el total de todas las atenciones realizadas. Debe inciar sesión para realizar esta operacion.
	// Si las credenciales son invalidas, indicarlo y volver al menu principal.
	
	private static void informarMontoTotalDeAtencionesRealizadas(Scanner teclado, Veterinaria veterinaria) {
		
		if(inicioSesion(teclado, veterinaria)) {
			mostrarMensaje("El total de las atenciones realizadas es $" + veterinaria.obtenerTotalDeAtenciones());
		}else {
			mostrarMensaje("El nombre de usuario y/o contraseña son incorrectas.");
		}
		
	}
	//*****************************************************************
	private static boolean inicioSesion(Scanner teclado, Veterinaria veterinaria) {
		
		mostrarMensaje("Ingrese el nombre de usuario: ");
		String userName = teclado.next();
		
		mostrarMensaje("Ingrese la contraseña: ");
		String password = teclado.next();
		
		boolean estadoDeSesion = veterinaria.iniciarSesion(userName, password);
		
		return estadoDeSesion;
	}
	

	//*****************************************************************
	private static void informarLaCantidadAtencionesRealizadasYDisponibles(Veterinaria veterinaria) {
		
		int cantidadDeAtencionesRealizadas = veterinaria.obtenerCantidadDeAtencionesRealizadas();
		int cantidadDeAtencionesDisponibles =veterinaria.obtenerCantidadDeAtencionesDisponibles(cantidadDeAtencionesRealizadas);
		
		mostrarMensaje("La cantidad de atenciones realizadas es: "+ cantidadDeAtencionesRealizadas 
				+ "\nLa cantidad de atenciones disponibles es: " + cantidadDeAtencionesDisponibles);
		
		
	}
	//*****************************************************************
	// Se solicita el ingresdo del id de la atencion y se elimina. Indicar el
	// resultado de la operacion
	
	private static void eliminarUnaAtencionPorId(Scanner teclado, Veterinaria veterinaria) {
		mostrarMensaje("Ingrese el id: ");
		int id = teclado.nextInt();
		
		if(veterinaria.eliminarAtencionPorId(id)) {
			mostrarMensaje("Se eliminó con éxito la atención "+ id);
		}else {
			mostrarMensaje("No se pudo eliminar la atención "+ id);
		}
	
	}
	
	//*****************************************************************
	// Ingresar la especie de mascota para listar las atenciones que correspondan.
	// Si no hay atenciones indicar lo propio
	
	private static void listarAtencionesPorEspecieDeterminada(Scanner teclado, Veterinaria veterinaria) {
		Especie especieElegida = seleccionarEspecie(teclado);
		
		Atencion atencionesPorEspecie[] = veterinaria.listarAtencionesPorEspecieDeMascota(especieElegida);
		
		if(atencionesPorEspecie==null) {
			mostrarMensaje("No hay atenciones de esa especie.");
		}else {
			for(Atencion atencion : atencionesPorEspecie) {
				if(atencion!=null)
					mostrarMensaje(atencion.toString());
			}
		}
		
	}
	//*****************************************************************
	// Se solicita el ingreso del id y se muestra la informacion de la atencion
	// encontrada. Si no se encuentra, mostrar un mensaje
	
	private static void buscarAtencionPorSuID(Scanner teclado, Veterinaria veterinaria) {
		
		mostrarMensaje("Ingrese el id: ");
		int id = teclado.nextInt();
		
		Atencion atencionEncontrada = veterinaria.buscarAtencionPorId(id);
		
		if(atencionEncontrada!=null) {
			mostrarMensaje(atencionEncontrada.toString());
			
		}else {
			mostrarMensaje("No se encontró la atención "+id);
		}
			
		
	}

	/**
	 * Muestra el menu principal y solicita el ingreso de la funcionalidad deseada
	 * 
	 * @param teclado	Para el ingreso de datos
	 * @return opcion seleccionada
	 * */
	private static int seleccionarOpcion(Scanner teclado) {
		
		int opcionSeleccionada = 0;

		mostrarMensaje("\n************************");
		mostrarMensaje("Menu Principal\n");
		mostrarMensaje("1 - Registar una atencion ");
		mostrarMensaje("2 - Buscar atencion por su identificador unico");
		mostrarMensaje("3 - Listar atenciones por una especie determinada");
		mostrarMensaje("4 - Eliminar una atencion por su id");
		mostrarMensaje("5 - Informar la cantidad de atenciones realizadas y las disponibles");
		mostrarMensaje("6 - Infomar el total de todas las atenciones realizadas [Requiere iniciar sesion]");
		mostrarMensaje("7 - Listar atenciones por monto descendente [Requiere iniciar sesion]");
		mostrarMensaje("\n9 - Salir");
		mostrarMensaje("\n************************");
		mostrarMensaje("\nIngrese una opcion");

		opcionSeleccionada = teclado.nextInt();

		return opcionSeleccionada;
	}
	
	/**
	 * Solicita el ingreso de la informacion para crear una atencion y la crea
	 * @param teclado	Para el ingreso de datos
	 * */
	private static boolean crearAtencion(Scanner teclado, Veterinaria veterinaria) {
		String nombreCliente, nombreMascota;
		double monto;
		Especie especieElegida;
		Atencion nuevaAtencion;
		
		mostrarMensaje("Ingrese el nombre del cliente: ");
		nombreCliente = teclado.next();
		
		mostrarMensaje("Ingrese el nombre de la mascota: ");
		nombreMascota = teclado.next();
		
		especieElegida = seleccionarEspecie(teclado);
		
		mostrarMensaje("Ingrese el monto a pagar: ");
		monto = teclado.nextDouble();
		
		

		nuevaAtencion = new Atencion(nombreCliente, nombreMascota,especieElegida, monto);
		
		return veterinaria.registrarAtencion(nuevaAtencion);
		
	}

	//*****************************************************************
	private static Especie seleccionarEspecie(Scanner teclado) {
		int cantidadEspecies;
		int opcionEspecie;
		Especie especieElegida;
		cantidadEspecies= Especie.values().length;
		do {
			for(int i=0; i<cantidadEspecies; i++) {
				System.out.println("["+ (i+1) + "] ."+ Especie.values()[i]);
			}
			mostrarMensaje("Seleccione especie de la mascota: ");
			opcionEspecie = teclado.nextInt();
			
			if(opcionEspecie>cantidadEspecies) {
				mostrarMensaje("Opción incorrecta.");
			}
			
		}while(opcionEspecie<=0 ||opcionEspecie>cantidadEspecies);
		especieElegida = Especie.values()[opcionEspecie-1];
		return especieElegida;
	}
	//*****************************************************************
	private static void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);
	}
	
	
}
