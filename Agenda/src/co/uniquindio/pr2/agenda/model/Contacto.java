package co.uniquindio.pr2.agenda.model;

import java.io.Serializable;
import java.util.Arrays;

public class Contacto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String alias;
	private String direccion;
	private String telefono;
	private String email;
	
	private Grupo[] listaGrupos;
	private Reunion []listaReuniones;

	
	/**
	 * Metodo constructor
	 */
	public Contacto() {
		super();
	}
	
	/**
	 * Metodo constructor
	 * @param nombre  del contacto
	 * @param alias Alias del contacto
	 * @param direccion Direccion del contacto
	 * @param telefono Telefono del contacto
	 * @param email Email del contacto
	 * @param numeroGrupos Numero de grupos que va a tener el contacto
	 * @param numeroReuniones Numero de reuniones que va a tener el contacto
	 */
	public Contacto(String nombre, String alias, String direccion, String telefono, String email, int numeroGrupos,
			int numeroReuniones) {
		super();
		this.nombre = nombre;
		this.alias = alias;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.listaGrupos = new Grupo[numeroGrupos];
		this.listaReuniones = new Reunion[numeroReuniones];
	}
	
	/**
	 * Metodo que obtiene el nombre del contacto
	 * @return Nombre del contacto
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Metodo que cambia el nombre del contacto
	 * @param nombre Nuevo nombre del contacto
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Metodo que obtiene el alias del contacto
	 * @return Alias del contacto
	 */
	public String getAlias() {
		return alias;
	}
	
	/**
	 * Metodo que cambia el alias del contacto
	 * @param alias Nuevo alias del contacto
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	/**
	 * Metodo que obtiene la direccion del contacto
	 * @return Direccion del contacto
	 */
	public String getDireccion() {
		return direccion;
	}
	
	/**
	 * Metodo que cambia la direccion del contacto
	 * @param direccion Nueva direccion del contacto
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	/**
	 * Metodo que obtiene el numero de telefono del contacto
	 * @return Numero de telefono del contacto
	 */
	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * Metodo que cambia el numero de telefono del contacto
	 * @param telefono Nuevo numero de telefono del contacto
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	/**
	 * Metodo que obtiene el email del contacto
	 * @return Email del contacto
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Metodo que cambia email del contacto
	 * @param email Nuevo email del contacto
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Metodo que obtiene la lista de grupos donde se encuentra el contacto
	 * @return Lista de grupos donde se encuentra el contacto
	 */
	public Grupo[] getListaGrupos() {
		return listaGrupos;
	}
	
	/**
	 * Metodo que cambia la lista de grupos donde se encuentra el contacto
	 * @param listaGrupos Nueva lista de grupos donde se encuentra el contacto
	 */
	public void setListaGrupos(Grupo[] listaGrupos) {
		this.listaGrupos = listaGrupos;
	}
	
	/**
	 * Metodo que obtiene la lista de reuniones donde se encuentra el contacto
	 * @return Lista de reuniones donde se encuentra el contacto
	 */
	public Reunion[] getListaReuniones() {
		return listaReuniones;
	}
	
	/**
	 * Metodo que cambia lista de reuniones donde se encuentra el contacto
	 * @param listaReuniones Nueva lista de reuniones donde se encuentra el contacto
	 */
	public void setListaReuniones(Reunion[] listaReuniones) {
		this.listaReuniones = listaReuniones;
	}
	
	/**
	 * Metodo que obtiene el codigo hash del contacto
	 * @return codigo hash del contacto
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		return result;
	}
	
	/**
	 * Metodo que comparar el contacto actual con otro objeto para ver si son iguales
	 * @param Objeto con el que se va a comparar el contacto actual
	 * @return Respuesta de que si el contacto actual y el objeto son iguales
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contacto other = (Contacto) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}
	
	/**
	 * Metodo que obtiene la informacion del contacto
	 * @return Informacion del contacto
	 */
	@Override
	public String toString() {
		return "Contacto [nombre=" + nombre + ", alias=" + alias + ", direccion=" + direccion + ", telefono=" + telefono
				+ ", email=" + email + ", listaGrupos=" + Arrays.toString(listaGrupos) + ", listaReuniones="
				+ Arrays.toString(listaReuniones) + "]";
	}
	
	/**
	 * Metodo que verifica si el un nombre es el mismo que el del contacto
	 * @param nombre Nombre que se desea verificar si es el mismo que el contacto
	 * @return 	Respuesta de que si el nombre del contacto es el mismo con el que se va a comparar o no
	 */
	public boolean verificarNombre(String nombre) {
		boolean respuesta=false;
		if (this.nombre.equals(nombre)) {
			respuesta=true;
		}
		return respuesta;
	}
	
	
	
}
