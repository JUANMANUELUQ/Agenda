package co.uniquindio.pr2.agenda.controllers;

import co.uniquindio.pr2.agenda.application.Aplicacion;
import co.uniquindio.pr2.agenda.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class VentanaPrincipalController {
	
	@FXML
	private Label cantHuecosContactos;
	@FXML
	private Label etiquetaAgendaLLenaContactos;
	@FXML
	private Label cantHuecosGrupos;
	@FXML
	private Label etiquetaAgendaLLenaGrupos;
	@FXML
	private Label cantHuecosReuniones;
	@FXML
	private Label etiquetaAgendaLLenaReuniones;
	private Aplicacion aplicacion;
	private Agenda agenda;
	
	/**
	 * Metodo que obtiene la agenda
	 * @param agenda Agenda
	 */
	public void setAgenda(Agenda agenda) {
		this.agenda=agenda;
	}
	
	/**
	 * Metodo que actualiza las etiquetas que indican cuanto es el espacio libre para meter contactos
	 */
	public void actualizarEspacioLibreContactos() {
		cantHuecosContactos.setText(""+agenda.huecosLibres());
		if (agenda.agendaLlena()) {
			etiquetaAgendaLLenaContactos.setText("(Agenda llena)");
		} else {
			etiquetaAgendaLLenaContactos.setText("");
		}
		Persistencia.serialize("src/archivo.dat", agenda);
	}
	
	public void actualizarEspacioLibreGrupos() {
		cantHuecosGrupos.setText(""+agenda.huecosLibresGrupos());
		if (agenda.huecosLibresGrupos()==0) {
			etiquetaAgendaLLenaGrupos.setText("(Agenda llena)");
		} else {
			etiquetaAgendaLLenaGrupos.setText("");
		}
		Persistencia.serialize("src/archivo.dat", agenda);
	}
	
	public void actualizarEspacioLibreReuniones() {
		cantHuecosReuniones.setText(""+agenda.huecosLibresReuniones());
		if (agenda.huecosLibresReuniones()==0) {
			etiquetaAgendaLLenaReuniones.setText("(Agenda llena)");
		} else {
			etiquetaAgendaLLenaReuniones.setText("");
		}
		Persistencia.serialize("src/archivo.dat", agenda);
	}

	/**
	 * Metodo que obtiene la aplicacion principal
	 * @param aplicacion Aplicacion principal
	 */
	public void setAplicacionPrincipal(Aplicacion aplicacion) {
		this.aplicacion=aplicacion;
	}
	
	/**
	 * Metodo que inicializa la clase controlador
	 */
	@FXML
	private void initialize() {
		
	}
	
	/**
	 * Metodo que abre la ventana para que se aniada un contacto
	 */
	@FXML
	private void aniadirContacto() {
		if (aplicacion.mostrarIngresoContacto(IngresoContactoOpciones.ANIADIR)) {
			actualizarEspacioLibreContactos();
		}
	}
	
	/**
	 * Metodo que abre la ventana para que se busque un contacto
	 */
	@FXML
	private void buscarContacto() {
		if (aplicacion.mostrarIngresoContacto(IngresoContactoOpciones.BUSCAR)) {
			actualizarEspacioLibreContactos();
		}
	}
	
	/**
	 * Metodo que abre la ventana para que se busque un telefono por el nombre de un contacto
	 */
	@FXML
	private void buscarTelefonoContacto() {
		aplicacion.mostrarBusquedaTelefono();
	}
	
	/**
	 * Metodo que abre la ventana para que se elimine un contacto
	 */
	@FXML
	private void eliminarContacto() {
		if (aplicacion.mostrarIngresoContacto(IngresoContactoOpciones.ELIMINAR)) {
			actualizarEspacioLibreContactos();
		}
	}
	
	/**
	 * Metodo que abre la ventana para se muestre todos los contactos
	 */
	@FXML
	private void listarContactos() {
		aplicacion.mostrarListaContactos();
	}
	
	@FXML
	private void aniadirGrupo() {
		if (aplicacion.mostrarCreacionGrupo()) {
			actualizarEspacioLibreGrupos();
		}
	}
	
	@FXML
	private void aniadirReunion() {
		if (aplicacion.mostrarCreacionReunion()) {
			actualizarEspacioLibreReuniones();
		}
	}
	
	@FXML
	private void grupos() {
		aplicacion.mostrarListaGrupos(this);
	}
	
	@FXML
	private void reuniones() {
		aplicacion.mostrarListaReuniones(this);
	}
	
}
