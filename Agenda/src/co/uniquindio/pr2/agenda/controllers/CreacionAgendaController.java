package co.uniquindio.pr2.agenda.controllers;

import javax.swing.JOptionPane;

import co.uniquindio.pr2.agenda.application.Aplicacion;
import co.uniquindio.pr2.agenda.model.Agenda;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreacionAgendaController {
	
	@FXML
	private TextField nombreAgenda;
	@FXML
	private TextField cantidadContactos;
	@FXML
	private TextField cantidadGrupos;
	@FXML
	private TextField cantidadReuniones;
	private Aplicacion aplicacionPrincipal;
	private Stage primaryStage;
	
	/**
	 * Metodo constructor
	 */
	public CreacionAgendaController() {
		
	}
	
	/**
	 * Metodo que obtiene la aplicacion principal
	 * @param aplicacion Aplicacion principal
	 */
	public void setAplicacionPrincipal(Aplicacion aplicacion) {
		aplicacionPrincipal=aplicacion;
		nombreAgenda.requestFocus();
	}
	
	/**
	 * Metodo que verifica si los datos ingresados son validos
	 * @return Respuesta de que si los datos son validos o no
	 */
	public boolean sonDatosValidos() {
		boolean sonValidos=false;
		String msj="";
		if (nombreAgenda.getText()==null || nombreAgenda.getText().trim().equals("")) {
			msj+="El nombre de la agenda no puede estar vacio";
		}
		try {
			if (Integer.parseInt(cantidadContactos.getText())<0) {
				throw new Exception("Valor invalido");
			}
		} catch (Exception e) {
			if (cantidadContactos.getText().trim().equals("")) {
				msj+="\n\nLa cantidad de contactos no puede estar vacia";
			} else {
				msj+="\n\nCantidad de contactos no valida";
			}
		}
		try {
			if (Integer.parseInt(cantidadGrupos.getText())<0) {
				throw new Exception("Valor invalido");
			}
		} catch (Exception e) {
			if (cantidadGrupos.getText().trim().equals("")) {
				msj+="\n\nLa cantidad de grupos no puede estar vacia";
			} else {
				msj+="\n\nCantidad de grupos no valida";
			}
		}
		try {
			if (Integer.parseInt(cantidadReuniones.getText())<0) {
				throw new Exception("Valor invalido");
			}
		} catch (Exception e) {
			if (cantidadReuniones.getText().trim().equals("")) {
				msj+="\n\nLa cantidad de reuniones no puede estar vacia";
			} else {
				msj+="\n\nCantidad de reuniones no valida";
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
	 * Metodo crea la agenda y continua con la siguiente parte de la aplicacion
	 */
	@FXML
	private void continuar() {
		if (sonDatosValidos()) {
			String nombre=nombreAgenda.getText();
			int numContactos=Integer.parseInt(cantidadContactos.getText());
			int numGrupos=Integer.parseInt(cantidadGrupos.getText());
			int numReuniones=Integer.parseInt(cantidadReuniones.getText());
			Agenda agenda=new Agenda(nombre,numContactos,numGrupos,numReuniones);
			aplicacionPrincipal.setAgenda(agenda);
			primaryStage.close();
			aplicacionPrincipal.mostrarVentanaPrincipal();
		}
	}

	/**
	 * Metodo que obtiene la direccion de la ventana donde se crea la agenda
	 * @param primaryStage Direccion de la ventana donde se crea la agenda
	 */
	public void setVentana(Stage primaryStage) {
		this.primaryStage=primaryStage;
	}

}
