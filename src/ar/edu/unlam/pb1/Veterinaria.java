package ar.edu.unlam.pb1;

import java.util.Arrays;

public class Veterinaria {
	private Administrador admin = new Administrador();
	private String nombre;
	private Atencion[] atenciones;
	private final int CANTIDAD_MAXIMA_DE_ATENCIONES;	
	
	
	
	public Veterinaria(String nombreVeterinaria, int cantidadMaximaAtenciones) {
		this.nombre = nombreVeterinaria;
		this.CANTIDAD_MAXIMA_DE_ATENCIONES = cantidadMaximaAtenciones;
		this.atenciones = new Atencion[this.CANTIDAD_MAXIMA_DE_ATENCIONES];
	}

	/**
	 * Agrega una atencion al arreglo de atenciones
	 * 
	 * @param atencion Atencion	que se agregara
	 * @return true en caso de exito
	 * */
	public boolean registrarAtencion(Atencion atencion) {
		for(int i=0;i< this.atenciones.length; i++) {
			if(this.atenciones[i]==null && atencion !=null) {
				this.atenciones[i] = atencion;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Obtiene una atencion por su identificador
	 * 
	 * @param id	Identificador de la atencion
	 * @return atencion o null en caso de no encontrarse
	 * */
	public Atencion buscarAtencionPorId(int id) {
		for(int i=0; i<this.atenciones.length; i++) {
			if(this.atenciones[i]!=null && this.atenciones[i].getId() == id) {
				return this.atenciones[i];
			}
		}
		return null;
	}
	
	/**
	 * Obtiene atenciones filtradas por la especie de la mascota
	 * 
	 * @return array de atenciones
	 * */
	public Atencion[] listarAtencionesPorEspecieDeMascota(Especie especieMascota){
		
		Atencion listaPorMascota[] = new Atencion[this.CANTIDAD_MAXIMA_DE_ATENCIONES];
		int contador =0;
		
		for(int i=0; i<this.atenciones.length; i++) {
			if(this.atenciones[i]!=null && this.atenciones[i].getEspecieMascota().equals(especieMascota)) {
				listaPorMascota[contador] = this.atenciones[i];
				contador++;	
			}
			
		}
		
		return listaPorMascota;
	}
	
	/**
	 * Elimina una atencion por su identificador
	 * 
	 * @param id 	Identificador de la atencion
	 * @return true en caso de exito
	 * */
	
	/* Aclaracion mia: Tomo como criterio que una atención eliminada será una atencion realizada si isRealizada()
	 * es igual a true entonces lo guardo en la lista de atenciones realizada.
	 * Sino igualmente que se pueda borrar, en caso si el dueño y su mascota no se presentaron en su turno.
	 * */
	public boolean eliminarAtencionPorId(int id) {
		Atencion atencionEncontrada = this.buscarAtencionPorId(id);

		if(atencionEncontrada!=null) {
			for(int i=0; i< this.atenciones.length; i++) {
				if(this.atenciones[i]!=null && this.atenciones[i].equals(atencionEncontrada)) {
					this.atenciones[i]=null;
					return true;
				}
			}
		}

		return false;
	}
	
	/**
	 * Obtiene la cantidad de atenciones realizadas hasta el momento
	 * 
	 * @return cantidad de atenciones
	 * */
	public int obtenerCantidadDeAtencionesRealizadas() {
		int contadorDeAtencionesRealizadas = 0;
		
		for(int i=0; i<this.atenciones.length; i++) {
			if(this.atenciones[i]!=null)
				contadorDeAtencionesRealizadas++;
				
		}
		return contadorDeAtencionesRealizadas;
	}
	
	/**
	 * Obtiene la cantidad de atenciones disponibles
	 * 
	 * @return atenciones
	 * */
	public int obtenerCantidadDeAtencionesDisponibles(int atencionesRealizadas) {
		return this.CANTIDAD_MAXIMA_DE_ATENCIONES - atencionesRealizadas;
	}
	
	/**
	 * Calcula y devuelve el total de todas las atenciones realizadas
	 * 
	 * @return monto total
	 * */
	public double obtenerTotalDeAtenciones() {
		double montoTotal=0;
		
		for(int i=0; i<this.atenciones.length; i++) {
			
			if(this.atenciones[i]!=null) {
				montoTotal+= this.atenciones[i].getMonto();
				
			}
		}
		return montoTotal;
	}
	
	/**
	 * Obtiene atenciones ordenadas por monto descendente
	 * 
	 * @return atenciones
	 * */
	
	public Atencion[] ordenarAtencionesPorMontoDescendente() {
		for(int i=0; i<this.atenciones.length; i++) {
			for(int j=0; j<this.atenciones.length-1; j++) {
				if(this.atenciones[j]!=null && this.atenciones[j+1]!=null && this.atenciones[j].getMonto() < this.atenciones[j+1].getMonto()) {
					Atencion backUp = this.atenciones[j];
					this.atenciones[j]= this.atenciones[j+1];
					this.atenciones[j+1]= backUp;
					
				}
			}
		}
		return this.atenciones;
	}
	
	/**
	 * Valida las credenciales proporcionadas
	 * 
	 * @param nombreUsuario		Nombre de usuario del administrador
	 * @param contrasenia		Contrasenia del administrador
	 * @return verdadero en caso de exito
	 * */
	public boolean iniciarSesion(String nombreUsuario, String contrasenia) {
		return this.admin.getNombre().equals(nombreUsuario) && this.admin.getContrasenia().equals(contrasenia);
		
	}

	
}