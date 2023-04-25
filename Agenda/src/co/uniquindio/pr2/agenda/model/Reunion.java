package co.uniquindio.pr2.agenda.model;

import java.io.Serializable;
import java.util.Arrays;

import co.uniquindio.pr2.agenda.exceptions.GrupoException;
import co.uniquindio.pr2.agenda.exceptions.ReunionException;

public class Reunion implements IReunion, Serializable{

	private String descripcion;
	private String fecha;
	private String hora;
	Contacto[] listaContactos;
	
	/**
	 * Metodo constructor
	 */
	public Reunion() {
		super();
	}

	/**
	 * Metodo constructor
	 * @param descripcion Descripcion del grupo
	 * @param fecha Fecha del grupo
	 * @param hora Hora del grupo
	 * @param listaContactos Lista de los contactos del grupo
	 */
	public Reunion(String descripcion, String fecha, String hora, Contacto[] listaContactos) {
		super();
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.hora = hora;
		this.listaContactos = listaContactos;
	}
	
	/**
	 * Metodo constructor
	 * @param descripcion Descripcion del grupo
	 * @param fecha Fecha del grupo
	 * @param hora Hora del grupo
	 * @param cantidadContactos Cantidad de contactos que van a asistir a la reunion
	 */
	public Reunion(String descripcion, String fecha, String hora, int cantidadContactos) {
		super();
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.hora = hora;
		this.listaContactos = new Contacto[cantidadContactos];
	}

	/**
	 * Metodo que obtiene la descripcion de la reunion
	 * @return Descripcion de la reunion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Metodo que cambia la descripcion de la reunion
	 * @param descripcion Nueva descripcion de la reunion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Metodo que obtiene fecha de la reunion
	 * @return Fecha de la reunion
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Metodo que cambia la fecha de la reunion
	 * @param fecha Nueva fecha de la reunion
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Metodo que obtiene la hora de la reunion
	 * @return Hora de la reunion
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Metodo que cambia la hora de la reunion
	 * @param hora Nueva hora de la reunion
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * Metodo que obtiene la lista de contactos
	 * @return Lista de contactos
	 */
	public Contacto[] getListaContactos() {
		return listaContactos;
	}

	/**
	 * Metodo que cambia la lista de contactos
	 * @param listaContactos Nueva lista de contactos
	 */
	public void setListaContactos(Contacto[] listaContactos) {
		this.listaContactos = listaContactos;
	}

	/**
	 * Metodo que obtiene la informacion de la reunion
	 * @return Informacion de la reunion
	 */
	@Override
	public String toString() {
		return "Reunion [descripcion=" + descripcion + ", fecha=" + fecha + ", hora=" + hora + ", listaContactos="
				+ Arrays.toString(listaContactos) + "]";
	}

	/**
	 * Metodo que obtiene el codigo hash de la reunion
	 * @return Codigo hash de la reunion
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((hora == null) ? 0 : hora.hashCode());
		return result;
	}

	/**
	 * Metodo que compara esta reunion con otro objeto para verificar si son iguales
	 * @param Obj Objeto con el que se va a comparar
	 * @return Si el objeto con el que se va a comparar es igual o no
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reunion other = (Reunion) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (hora == null) {
			if (other.hora != null)
				return false;
		} else if (!hora.equals(other.hora))
			return false;
		return true;
	}

	/**
	 * Metodo que agenda una reunion
	 */
	@Override
	public void agendar() {
		
		System.out.println("agendado");
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
	 * @return Primera posicion disponible o -1 en caso de que la reunion este llena
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
			if ((""+contactoBuscar).equals((""+listaContactos[i]))) {
				respuesta=true;
				break;
			}
		}
		return respuesta;
	}

	/**
	 * Metodo que que agrega un contacto a la reunion
	 * @param contacto Contacto que se va a gregar
	 * @throws GrupoException Error en caso de que la reunion este llena o el contacto ya haya estado guardado
	 */
	public void agregarContacto(Contacto contacto) throws ReunionException {
		int pos=obtenerPosicionDisponible();
		if (pos==-1) {
			throw new ReunionException("Grupo lleno");
		}
		if (existeContacto(contacto)) {
			throw new ReunionException("Contacto repetido");
		}
		listaContactos[pos]=contacto;
	}
	
}
