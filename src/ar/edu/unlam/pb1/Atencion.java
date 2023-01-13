package ar.edu.unlam.pb1;

public class Atencion {
	private int id;
	private static int cantidadDeAtencion;
	private String nombreCliente;
	private String nombreMascota;
	private Especie especieMascota; 
	private double monto;
	
	
	public Atencion(String nombreCliente, String nombreMascota, Especie especieMascota, double monto) {
		this.cantidadDeAtencion++;
		this.nombreCliente = nombreCliente;
		this.nombreMascota = nombreMascota;
		this.especieMascota = especieMascota;
		this.monto = monto;
		this.id = cantidadDeAtencion;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getNombreMascota() {
		return nombreMascota;
	}

	public void setNombreMascota(String nombreMascota) {
		this.nombreMascota = nombreMascota;
	}

	public Especie getEspecieMascota() {
		return especieMascota;
	}

	public void setEspecieMascota(Especie especieMascota) {
		this.especieMascota = especieMascota;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}


	@Override
	public String toString() {
		return "ID: "+id+"\n" +
				"Nombre del cliente: " + nombreCliente + "\n" +
				"Nombre de la mascota: " + nombreMascota + "\n" +
				"Especie de la mascota: " + especieMascota + "\n" +
				"Monto:" + monto + "\n" 
				+ "**************************************";
				
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
