package co.uniquindio.pr2.agenda.model;

import java.io.Serializable;
import java.util.Arrays;

import co.uniquindio.pr2.agenda.exceptions.GrupoException;

public class Grupo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private Contacto[] listaContactos;
	private Categoria categoria;
	
	/**
	 * Metodo constructor
	 * @param nombre Nombre del grupo del grupo
	 * @param numeroContactos Numero de contactos que va a tener el grupo
	 */
	public Grupo(String nombre, int numeroContactos) {
		super();
		this.nombre = nombre;
		this.listaContactos = new Contacto[numeroContactos];
	}
	
	/**
	 * Metodo constructor
	 * @param nombre Nombre del grupo del grupo
	 * @param categoria Categoria del grupo
	 * @param numeroContactos Numero de contactos que va a tener el grupo
	 */
	public Grupo(String nombre,Categoria categoria,int numeroContactos) {
		super();
		this.nombre = nombre;
		this.categoria=categoria;
		this.listaContactos = new Contacto[numeroContactos];
	}

	/**
	 * Metodo constructor
	 */
	public Grupo() {
		super();
	}

	/**
	 * Metodo que obtiene el nombre del grupo
	 * @return Nombre del grupo
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo que cambia nombre del grupo
	 * @param nombre Nuevo nombre del grupo
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo que obtiene la lista de contactos del grupo
	 * @return Lista de contactos del grupo
	 */
	public Contacto[] getListaContactos() {
		return listaContactos;
	}

	/**
	 * Metodo que cambia la lista de contactos del grupo
	 * @param listaContactos Nueva lista de contactos del grupo
	 */
	public void setListaContactos(Contacto[] listaContactos) {
		this.listaContactos = listaContactos;
	}

	/**
	 * Metodo que obtiene la categoria del grupo
	 * @return Categoria del grupo
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * Metodo que cambia la categoria del grupo
	 * @param categoria Nueva categoria del grupo
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * Metodo que obtiene la informacion del grupo
	 * @return Informacion del grupo
	 */
	@Override
	public String toString() {
		return "Grupo [nombre=" + nombre + ", listaContactos=" + Arrays.toString(listaContactos) + "]";
	}

	/**
	 * Metodo que obtiene el codigo hash del grupo 
	 * @return Codigo hash del grupo
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(listaContactos);
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	/**
	 * Metodo que compara el grupo con otro objeto para verificar si son iguales
	 * @param Objeto con el que se va a comparar el grupo
	 * @return Respuesta de que si el objeto es igual al grupo
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grupo other = (Grupo) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	/**
	 * Metodo que obtiene la cantidad de contactos
	 * @return Cantidad de contactos
	 */
	public int obtenerCantContactosGuardados() {
		int cantContactos=0;
		for (int i=0;i<listaContactos.length;i++) {
			if (listaContactos[i]!=null) {
				cantContactos++;
			}
		}
		return cantContactos;
	}
	
	/**
	 * Metodo que obtiene la categoria en forma de String
	 * @return Categoria en forma de String
	 */
	public String obtenerCategoriaString() {
		String categoria="";
		switch(this.categoria) {
			case OFICINA:categoria="Oficina"; break;
			case FIESTA:categoria="Fiesta"; break;
			case FAMILIA:categoria="Familia"; break;
			case AMIGOS:categoria="Amigos"; break;
		}
		return categoria;
	}
	
	/**
	 * Metodo que obtiene los contactos guardados organizados en una cadena
	 * @return Contactos guardados organizados en una cadena
	 */
	public String obtenerContactos() {
		String msj="";
		for (Contacto contacto : listaContactos) {
			if (contacto!=null) {
				msj+="Nombre: "+contacto.getNombre();
				msj+="\nAlias: "+contacto.getAlias();
				msj+="\nDireccion: "+contacto.getDireccion();
				msj+="\nTelefono: "+contacto.getTelefono();
				msj+="\nEmail: "+contacto.getEmail();
				msj+="\n\n";
			}
		}
		if (msj.equals("")) {
			msj="No hay contactos guardados";
		}
		return msj;
	}
	
	/**
	 * Metodo que obtiene la primera posicion disponible para guardar un contacto
	 * @return Primera posicion disponible o -1 en caso de que el grupo este lleno
	 */
	public int obtenerPosicionDisponible() {
		int pos=-1;
		for (int i = 0; i < listaContactos.length; i++) {
			if (listaContactos[i]!=null) {
				pos=i;
				break;
			}
		}
		return pos;
	}
	
	/**
	 * Metodo que verifica si un contacto esta guardado
	 * @param contactoBuscar Contacto que se desea buscar para ver si existe
	 * @return Respuesta de que si el contacto existe o no
	 */
	public boolean existeContacto(Contacto contactoBuscar) {
		boolean respuesta=false;
		for (int i = 0; i < listaContactos.length; i++) {
			if (contactoBuscar.equals(listaContactos[i])) {
				respuesta=true;
				break;
			}
		}
		return respuesta;
	}

	/**
	 * Metodo que que agrega un contacto al grupo
	 * @param contacto Contacto que se va a gregar
	 * @throws GrupoException Error en caso de que el grupo este lleno o el contacto ya haya estado guardado
	 */
	public void agregarContacto(Contacto contacto) throws GrupoException {
		int pos=obtenerPosicionDisponible();
		if (pos==-1) {
			throw new GrupoException("Grupo lleno");
		}
		if (existeContacto(contacto)) {
			throw new GrupoException("Contacto repetido");
		}
		listaContactos[pos]=contacto;
	}
	
}
