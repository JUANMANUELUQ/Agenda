package co.uniquindio.pr2.agenda.controllers;

import javax.swing.JOptionPane;

import co.uniquindio.pr2.agenda.application.Aplicacion;
import co.uniquindio.pr2.agenda.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ListaReunionesController {

	@FXML
	private TableView<Reunion> listaReuniones;
	@FXML
	private TableColumn<Reunion,String> fechaColumna;
	@FXML
	private TableColumn<Reunion,String> horaColumna;
	@FXML
	private TableColumn<Reunion,String> contactosGuardadosColumna;
	@FXML
	private TableColumn<Reunion,String> contactosAsistirColumna;
	private Agenda agenda;
	private Stage dialogStage;
	private VentanaPrincipalController ventanaPrincipal;
	private Aplicacion aplicacion;
	private ObservableList<Reunion> listaReunionesProperty=FXCollections.observableArrayList();
	
	/**
	 * Metodo que obtiene la direccion de la aplicacion principal
	 * @param aplicacion Direccion de la aplicacion principal
	 */
	public void setventanaPrincipal(VentanaPrincipalController ventanaPrincipal) {
		this.ventanaPrincipal=ventanaPrincipal;
	}
	
	/**
	 * Metodo que obtiene la direccion de la ventana donde aparece la lista de reuniones
	 * @param dialogStage Ventana donde aparece la lista de grupos
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
	 * Metodo que actualiza los datos d ela tabla
	 */
	public void actualizarTabla() {
		for (Reunion reunion : agenda.getListaReuniones()) {
			if (reunion!=null) {
				listaReunionesProperty.add(reunion);
			}
		}
		listaReuniones.setItems(listaReunionesProperty);
		fechaColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFecha()));
		horaColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHora()));
		contactosGuardadosColumna.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().obtenerCantContactosGuardados()));
		contactosAsistirColumna.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getListaContactos().length));
		
	}
	
	/**
	 * Metodo que inicializa la clase controlador
	 */
	@FXML
	private void initialize() {
		
	}
	
	/**
	 * Metodo que elimina la reunion seleccionada
	 */
	@FXML
	private void eliminar(){
		int selectedIndex = listaReuniones.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Reunion reunionSeleccionada = listaReuniones.getSelectionModel().getSelectedItem();
			listaReuniones.getItems().remove(selectedIndex);
			agenda.eliminarReunion(reunionSeleccionada);
			ventanaPrincipal.actualizarEspacioLibreReuniones();
		} else {
			JOptionPane.showMessageDialog(null,"Elija una reunion","No hay una reunion seleccionada",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Metodo que muestra la direccion de la reunion
	 */
	@FXML
	private void verDescripcion(){
		int selectedIndex = listaReuniones.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Reunion reunionSeleccionada = listaReuniones.getSelectionModel().getSelectedItem();
			JOptionPane.showMessageDialog(null,reunionSeleccionada.getDescripcion(),"Descripcion",JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null,"Elija una reunion","No hay una reunion seleccionada",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Metodo que muestra los contactos de la reunion seleccionada
	 */
	@FXML
	private void verContactos(){
		int selectedIndex = listaReuniones.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Reunion reunionSeleccionada = listaReuniones.getSelectionModel().getSelectedItem();
			JOptionPane.showMessageDialog(null,reunionSeleccionada.obtenerContactos(),"Contactos",JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null,"Elija un grupo","No hay un grupo seleccionado",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Metodo que cierra la ventana
	 */
	@FXML
	private void volver(){
		dialogStage.close();
	}
	
	/**
	 * Metodo que muestra la ventana para para agregar un contacto nuevo a la reunion seleccionada
	 */
	@FXML
	private void agregarNuevoContacto(){
		int selectedIndex = listaReuniones.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Reunion reunionSeleccionada = listaReuniones.getSelectionModel().getSelectedItem();
			if (aplicacion.mostrarIngresoContacto(IngresoContactoOpciones.ANIADIR_NUEVO_REUNION,reunionSeleccionada)) {
				ventanaPrincipal.actualizarEspacioLibreContactos();
				ventanaPrincipal.actualizarEspacioLibreGrupos();
			}
		} else {
			JOptionPane.showMessageDialog(null,"Elija un grupo","No hay un grupo seleccionado",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Metodo que muestra la ventana para agregar un contacto existente a la reunion seleccionada
	 */
	@FXML
	private void agregarContactoExistente() {
		int selectedIndex = listaReuniones.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Reunion reunionSeleccionada = listaReuniones.getSelectionModel().getSelectedItem();
			if (aplicacion.mostrarIngresoContacto(IngresoContactoOpciones.ANIADIR_EXISTENTE_REUNION,reunionSeleccionada)) {
				ventanaPrincipal.actualizarEspacioLibreContactos();
				ventanaPrincipal.actualizarEspacioLibreGrupos();
			}
		} else {
			JOptionPane.showMessageDialog(null,"Elija un grupo","No hay un grupo seleccionado",JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Metodo que obtiene la direccion de la aplicacion
	 * @param aplicacion Direccion de la aplicacion
	 */
	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion=aplicacion;
		
	}
}
