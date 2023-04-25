package co.uniquindio.pr2.agenda.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import co.uniquindio.pr2.agenda.exceptions.*;

public class Agenda implements Serializable {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private String nombre;
	private Contacto[] listaContactos;
	private Grupo[] listaGrupos;
	private Reunion[] listaReuniones;
	
	/**
	 * Metodo constructor
	 * @param nombre Nombre de la agenda
	 * @param numeroContactos Numero de contactos que va a tener la agenda
	 * @param numeroGrupos Numero de grupos que va a tener la agenda
	 * @param numeroReuniones Numero de reuniones que va a tener la agenda
	 */
	public Agenda(String nombre, int numeroContactos,int numeroGrupos,int numeroReuniones) {
		super();
		this.nombre = nombre;
		this.listaContactos = new Contacto[numeroContactos];
		this.listaGrupos = new Grupo[numeroGrupos];
		this.listaReuniones = new Reunion[numeroReuniones];
	}

	/**
	 * Metodo constructor
	 */
	public Agenda() {
		super();
	}

	/**
	 * Metodo que obtiene el nombre de la agenda
	 * @return Nombre de la agenda
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo que cambia el nombre de la agenda
	 * @param nombre Nuevo nombre de la agenda
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo que obtiene la lista de contactos de la agenda
	 * @return Lista de contactos de la agenda
	 */
	public Contacto[] getListaContactos() {
		return listaContactos;
	}

	/**
	 * Metodo que cambia la lista de contactos de la agenda
	 * @param listaContactos Nueva lista de contactos de la agenda
	 */
	public void setListaContactos(Contacto[] listaContactos) {
		this.listaContactos = listaContactos;
	}

	/**
	 * Metodo que obtiene la lista de grupos de la agenda
	 * @return Lista de grupos de la agenda
	 */
	public Grupo[] getListaGrupos() {
		return listaGrupos;
	}

	/**
	 * Metodo que cambia la lista de grupos de la agenda
	 * @param listaGrupos Nueva lista de grupos de la agenda
	 */
	public void setListaGrupos(Grupo[] listaGrupos) {
		this.listaGrupos = listaGrupos;
	}

	/**
	 * Metodo que obtiene la lista de reuniones
	 * @return Lista de reuniones
	 */
	public Reunion[] getListaReuniones() {
		return listaReuniones;
	}

	/**
	 * Metodo que cambia la lista de reuniones
	 * @param listaReuniones Nueva lista de reuniones
	 */
	public void setListaReuniones(Reunion[] listaReuniones) {
		this.listaReuniones = listaReuniones;
	}

	/**
	 * Metodo que obtiene la informacion de la cuenta
	 * @return Informacion d ela cuenta
	 */
	@Override
	public String toString() {
		return "Agenda [nombre=" + nombre + "]";
	}

	/**
	 * Metodo que obtiene el codigo hash de la agenda
	 * @return Codigo hash
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	/**
	 * Metodo que comparara esta agenda con un objeto
	 * @param Object Objeto con el que se va a comprarar 
	 * @return Respuesta de que si esta cuenta es equivalente al objeto o no
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agenda other = (Agenda) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	/**
	 * Metodo que aniade un contacto a la agenda
	 * @param newContacto Contacto a agregar
	 * @throws ContactoException Error en caso de que el contacto ya exista o la agenda este llena
	 */
	public void aniadirContacto(Contacto newContacto) throws ContactoException {
		
		Contacto contacto = buscarContacto(newContacto);
		int posDisponible = 0;

		if(contacto != null){
			throw new ContactoException("El contacto ya existe");
		}
		posDisponible = obtenerPosicion();
		
		if(posDisponible == -1){
			throw new ContactoException("Agenda llena");
		}
		listaContactos[posDisponible] = newContacto;
		
	}

	/**
	 * Metodo que obtiene la primer posicion disponible en la lista de contactos de la agenda
	 * @return Posicion disponible en la lista de contactos de la agenda o -1 en caso de que no exista
	 */
	private int obtenerPosicion() {
		int posDisponible=-1;
		for (int i=0;i<listaContactos.length;i++) {
			if (listaContactos[i]==null) {
				posDisponible=i;
			}
		}
		return posDisponible;
	}

	/**
	 * Metodo que busca un contacto en la agenda y lo obtiene
	 * @param newContacto Contacto que se desea buscar y obtener
	 * @return El contacto encontrado o null en caso d eque no este en la agenda
	 */
	private Contacto buscarContacto(Contacto newContacto) {
		Contacto contacto=null;
		for (int i=0;i<listaContactos.length;i++) {
			if (newContacto.equals(listaContactos[i])) {
				contacto=listaContactos[i];
			}
		}
		return contacto;
	}
	
	/**
	 * Metodo que verifica si un contacto existe en la agenda
	 * @param newContacto Contacto que se va a verificar
	 * @return Respuesta de que si el contacto existe en la agenda o no
	 */
	public boolean existeContacto(Contacto newContacto) {
		boolean existe=false;
		for (int i=0;i<listaContactos.length;i++) {
			if (newContacto.equals(listaContactos[i])) {
				existe=true;
			}
		}
		return existe;
	}
	
	/**
	 * Metodo que obtiene una lista de los constactos de la agenda 
	 * @return Lista de los contactos de la agenda
	 */
	public List<Contacto> listarContactos() {
		List<Contacto> contactos=new ArrayList<Contacto>();
		contactos=Arrays.asList(listaContactos);
		return contactos;
	}
	
	/**
	 * Metodoq que busca un contacto por el nombre y obtiene su telefono
	 * @param nombre Nombre del contacto del cual se va a obtener su telefono
	 * @return Telefono del contacto o
	 * @throws ContactoException Error en caso de que el contacto no exista
	 */
	public String buscaContacto(String nombre) throws ContactoException {
		String telefono=null;
		for (int i = 0; i < listaContactos.length; i++) {
			if (listaContactos[i].verificarNombre(nombre)) {
				telefono=listaContactos[i].getTelefono();
			}
		}
		if (telefono==null) {
			throw new ContactoException("El contacto no existe");
		}
		return telefono;
	}
	
	/**
	 * Metodo que obtiene la posicion de la lista en donde se encuentra un contacto
	 * @param contacto Contacto que se desea obtener su posicion
	 * @return Posicion del contacto o -1 en caso de que no este en la agenda
	 */
	public int obtenerPosicionContacto(Contacto contacto) {
		int pos=-1;
		for (int i = 0; i < listaContactos.length; i++) {
			if (contacto.equals(listaContactos[i])) {
				pos=i;
				break;
			}
		}
		return pos;
	}
	
	/**
	 * Metodo que elimina un contacto de la agenda
	 * @param contactoBorrar Contacto que se va a borrar
	 * @throws ContactoException Error en caso de que el contacto no exista
	 */
	public void eliminarContacto(Contacto contactoBorrar) throws ContactoException {
		
		Contacto contacto = buscarContacto(contactoBorrar);
		if (contacto == null) {
			throw new ContactoException("El contacto no existe");
		}
		listaContactos[obtenerPosicionContacto(contactoBorrar)] = null;
	}
	
	/**
	 * Metodo que verifica si la agenda esta llena o no
	 * @return Respuesta de que si la agenda esta llena o no
	 */
	public boolean agendaLlena() {
		boolean respuesta=true;
		for (int i = 0; i < listaContactos.length; i++) {
			if (listaContactos[i]==null) {
				respuesta=false;
				break;
			}
		}
		return respuesta;
	}
	
	/**
	 * Metodo que obtiene la cantidad de huecos libres en la agenda en la parte de contactos
	 * @return Cantidad de huecos libres en la agenda
	 */
	public int huecosLibres() {
		int huecosLibres=0;
		for (int i = 0; i < listaContactos.length; i++) {
			if (listaContactos[i]==null) {
				huecosLibres++;
			}
		}
		return huecosLibres;
	}
	
	/**
	 * Metodo que obtiene la cantidad de huecos libres en la agenda en la parte de grupos
	 * @return Cantidad de huecos libres en la agenda
	 */
	public int huecosLibresGrupos() {
		int huecosLibres=0;
		for (int i = 0; i < listaGrupos.length; i++) {
			if (listaGrupos[i]==null) {
				huecosLibres++;
			}
		}
		return huecosLibres;
	}
	
	/**
	 * Metodo que obtiene la cantidad de huecos libres en la agenda en la parte de reuniones
	 * @return Cantidad de huecos libres en la agenda
	 */
	public int huecosLibresReuniones() {
		int huecosLibres=0;
		for (int i = 0; i < listaReuniones.length; i++) {
			if (listaReuniones[i]==null) {
				huecosLibres++;
			}
		}
		return huecosLibres;
	}
	
	/**
	 * Metodo que obtiene la primera posicion disponible para guardar un grupo
	 * @return Primera posicion donde se puede guardar un grupo o -1 si no hay espacio disponible
	 */
	public int obtenerPosicionDisponibleGrupo() {
		int posicion=-1;
		for (int i=0;i<listaGrupos.length;i++) {
			if (listaGrupos[i]==null) {
				posicion=i;
				break;
			}
		}
		return posicion;
	}
	
	/**
	 * Metodo que verifica si un grupo existe en la agenda
	 * @param grupo Grupo que se va a verificar si existe
	 * @return Respuesta de que si e grupo existe o no
	 */
	public boolean existeGrupo(Grupo grupo) {
		boolean existeGrupo=false;
		for (int i=0;i<listaGrupos.length;i++) {
			if (grupo.equals(listaGrupos[i])) {
				existeGrupo=true;
				break;
			}
		}
		return existeGrupo;
	}
	
	/**
	 * Metodo que aniade un grupo a la agenda
	 * @param grupo Grupo que se va a aniadir
	 * @throws GrupoException Error en caso de que la agenda este llena
	 */
	public void aniadirGrupo(Grupo grupo) throws GrupoException {
		int posicionDisponible=obtenerPosicionDisponibleGrupo();
		if (posicionDisponible==-1) {
			throw new GrupoException("Agenda llena");
		}
		listaGrupos[posicionDisponible]=grupo;
	}
	
	/**
	 * Metodo que obtiene la primera posicion disponible para guardar una reunion
	 * @return Primera posicion donde se puede guardar una reunion o -1 si no hay espacio disponible
	 */
	public int obtenerPosicionDisponibleReunion() {
		int posicion=-1;
		for (int i=0;i<listaReuniones.length;i++) {
			if (listaReuniones[i]==null) {
				posicion=i;
			}
		}
		return posicion;
	}
	
	/**
	 * Metodo que aniade una reunion a la agenda
	 * @param reunion Reunion que se va a aniadir
	 * @throws ReunionException Error en caso de que la agenda este llena
	 */
	public void aniadirReunion(Reunion reunion) throws ReunionException {
		int posicionDisponible=obtenerPosicionDisponibleReunion();
		if (posicionDisponible==-1) {
			throw new ReunionException("Agenda llena");
		}
		listaReuniones[posicionDisponible]=reunion;
	}

	/**
	 * Metodo que elimina una reunion de la agenda
	 * @param reunionSeleccionada Reunion que se va a eliminar
	 */
	public void eliminarReunion(Reunion reunionSeleccionada) {
		for (int i=0;i<listaReuniones.length;i++) {
			if ((""+reunionSeleccionada).equals(""+listaReuniones[i])) {
				listaReuniones[i]=null;
			}
		}
	}
	
	/**
	 * Metodo que elimina un grupo de la agenda
	 * @param grupo Grupo que se va a eliminar
	 */
	public void eliminarGrupo(Grupo grupo) {
		for (int i=0;i<listaGrupos.length;i++) {
			if (grupo.equals(listaGrupos[i])) {
				listaGrupos[i]=null;
			}
		}
	}

	
	
}
