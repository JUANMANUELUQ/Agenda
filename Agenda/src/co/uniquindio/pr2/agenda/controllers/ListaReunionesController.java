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
	
	public void setventanaPrincipal(VentanaPrincipalController ventanaPrincipal) {
		this.ventanaPrincipal=ventanaPrincipal;
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage=dialogStage;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda=agenda;
	}

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
	
	@FXML
	private void volver(){
		dialogStage.close();
	}
	
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
	
	@FXML
	private void agregarContactoExistente() {
		int selectedIndex = listaReuniones.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Reunion reunionSeleccionada = listaReuniones.getSelectionModel().getSelectedItem();
			if (aplicacion.mostrarIngresoContacto(IngresoContactoOpciones.ANIADIR_REUNION,reunionSeleccionada)) {
				ventanaPrincipal.actualizarEspacioLibreContactos();
				ventanaPrincipal.actualizarEspacioLibreGrupos();
			}
		} else {
			JOptionPane.showMessageDialog(null,"Elija un grupo","No hay un grupo seleccionado",JOptionPane.ERROR_MESSAGE);
		}
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion=aplicacion;
		
	}
}
