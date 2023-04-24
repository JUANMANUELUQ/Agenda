package co.uniquindio.pr2.agenda.controllers;

import java.util.List;

import javax.swing.JOptionPane;

import co.uniquindio.pr2.agenda.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BusquedaTelefonoController {
	
	@FXML
	private TextField nombre;
	@FXML
	private Label telefono;
	private Stage dialogStage;
	private Agenda agenda;
	
	/**
	 * Metodo constructor
	 */
	public BusquedaTelefonoController() {
		
	}
	
	/**
	 * Metodo que obtiene la direccion de la ventana donde se buscan los telefono por nombre
	 * @param dialogStage Direccion de ventana
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage=dialogStage;
	}
	
	/**
	 * Metodo que obtiene la agenda
	 * @param agenda Agenda
	 */
	public void setAgenda(Agenda agenda) {
		this.agenda=agenda;
	}
	
	/**
	 * Metodo que inicializa la clase controlador
	 */
	@FXML
	private void initialize() {
		
	}
	
	/**
	 * Metodo que busca un contacto por el nombre
	 */
	@FXML
	private void buscar() {
		List<Contacto> listaContactos=agenda.listarContactos();
		boolean nombreEncontrado=false;
		boolean nombreVacio=true;
		if (nombre.getText()==null || nombre.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "El nombre no debe estar vacio","Nombre vacio",JOptionPane.WARNING_MESSAGE);
		} else {
			nombreVacio=false;
			for (Contacto contacto : listaContactos) {
				if (contacto!=null && nombre.getText().equals(contacto.getNombre())) {
					telefono.setText(contacto.getTelefono());
					nombreEncontrado=true;
				}
			}
		}
		if (!nombreVacio && !nombreEncontrado) {
			JOptionPane.showMessageDialog(null, "No existe un contacto con ese nombre","Nombre no encontrado",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/**
	 * Metodo que cierra la ventana
	 */
	@FXML
	private void volver() {
		dialogStage.close();
	}

}
