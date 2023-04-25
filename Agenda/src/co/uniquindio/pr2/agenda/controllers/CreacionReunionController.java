package co.uniquindio.pr2.agenda.controllers;

import javax.swing.JOptionPane;

import co.uniquindio.pr2.agenda.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreacionReunionController {
	
	@FXML
	private TextArea descripcion;
	@FXML
	private TextField fecha;
	@FXML
	private TextField hora;
	@FXML
	private TextField cantContactos;
	private Agenda agenda;
	private Stage dialogStage;
	private boolean fueBotonPresionado=false;
	
	/**
	 * Metodo que obtiene la direccion de la ventana donde se crean las reuniones
	 * @param dialogStage Direccion de ventana
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage=dialogStage;
		descripcion.requestFocus();
	}

	/**
	 * Metodo que obtiene la agenda
	 * @param agenda Agenda
	 */
	public void setAgenda(Agenda agenda) {
		this.agenda=agenda;
	}

	/**
	 * Metodo que retorna si se guardo la reunion
	 * @return Si se guardo la reunion o no
	 */
	public boolean fueGuardadaReunion() {
		return fueBotonPresionado;
	}
	
	/**
	 * Metodo que verifica si los datos ingresados son validos
	 * @return Respuesta de que si los datos son validos o no
	 */
	public boolean sonDatosValidos() {
		boolean sonValidos=false;
		String msj="";
		if (descripcion.getText()==null || descripcion.getText().trim().equals("")) {
			msj+="La descripcion no debe estar vacia";
		}
		if (fecha.getText()==null || fecha.getText().trim().equals("")) {
			msj+="La fecha no debe estar vacia";
		}
		if (hora.getText()==null || hora.getText().trim().equals("")) {
			msj+="La hora no debe estar vacia";
		}
		try {
			if (Integer.parseInt(cantContactos.getText())<1) {
				throw new Exception("Valor invalido");
			}
		} catch (Exception e) {
			if (cantContactos.getText().trim().equals("")) {
				msj+="\n\nLa cantidad de contactos no puede estar vacia";
			} else {
				msj+="\n\nCantidad de contactos no valida";
			}
		}
		if (msj.equals("")) {
			sonValidos=true;
		} else {
			JOptionPane.showMessageDialog(null, msj,"Entradas no validas",JOptionPane.ERROR_MESSAGE);
		}
		return sonValidos;
	}
	
	/**
	 * Metodo que inicializa la clase controlador
	 */
	@FXML
	private void initialize() {
		
	}
	
	/**
	 * Metodo que cierra la ventana
	 */
	@FXML
	private void volver() {
		dialogStage.close();
	}
	
	/**
	 * Metodo que guarda la reunion
	 */
	@FXML
	private void guardar() {
		String descripcion,fecha,hora;
		int cantidadContactos;
		if (sonDatosValidos()) {
			descripcion=this.descripcion.getText();
			fecha=this.fecha.getText();
			hora=this.hora.getText();
			cantidadContactos=Integer.parseInt(cantContactos.getText());
			Reunion reunion=new Reunion(descripcion,fecha,hora,cantidadContactos);
			try {
				agenda.aniadirReunion(reunion);
				fueBotonPresionado=true;
				dialogStage.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "La agenda esta llena","Agenda llena",JOptionPane.WARNING_MESSAGE);
			}
		}
	}

}
