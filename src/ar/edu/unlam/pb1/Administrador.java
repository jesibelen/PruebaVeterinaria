package ar.edu.unlam.pb1;

public class Administrador {
	private String nombre;
	private String contrasenia;
	
	public Administrador() {
		this.nombre = "admin";
		this.contrasenia = "qwerty";
	}

	public String getNombre() {
		return nombre;
	}

	public String getContrasenia() {
		return contrasenia;
	}
	
}
