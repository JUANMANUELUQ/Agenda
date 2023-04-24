package co.uniquindio.pr2.agenda.controllers;

import javax.swing.JOptionPane;

import co.uniquindio.pr2.agenda.model.*;
import co.uniquindio.pr2.agenda.application.Aplicacion;
import co.uniquindio.pr2.agenda.controllers.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ListaGruposController {
	
	@FXML
	private TableView<Grupo> listaGrupos;
	@FXML
	private TableColumn<Grupo,String> nombreColumna;
	@FXML
	private TableColumn<Grupo,String> categoriaColumna;
	@FXML
	private TableColumn<Grupo,String> contactosGuardadosColumna;
	private Agenda agenda;
	private Stage dialogStage;
	private VentanaPrincipalController ventanaPrincipal;
	private Aplicacion aplicacion;
	private ObservableList<Grupo> listaGruposProperty=FXCollections.observableArrayList();
	
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
		for (Grupo grupo : agenda.getListaGrupos()) {
			if (grupo!=null) {
				listaGruposProperty.add(grupo);
			}
		}
		listaGrupos.setItems(listaGruposProperty);
		nombreColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
		categoriaColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerCategoriaString()));
		contactosGuardadosColumna.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().obtenerCantContactosGuardados()));
	}
	
	/**
	 * Metodo que inicializa la clase controlador
	 */
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	private void eliminar(){
		int selectedIndex = listaGrupos.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Grupo grupoSeleccionado = listaGrupos.getSelectionModel().getSelectedItem();
			listaGrupos.getItems().remove(selectedIndex);
			agenda.eliminarGrupo(grupoSeleccionado);
			ventanaPrincipal.actualizarEspacioLibreGrupos();
		} else {
			JOptionPane.showMessageDialog(null,"Elija un grupo","No hay un grupo seleccionado",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@FXML
	private void verContactos(){
		int selectedIndex = listaGrupos.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Grupo grupoSeleccionado = listaGrupos.getSelectionModel().getSelectedItem();
			JOptionPane.showMessageDialog(null,grupoSeleccionado.obtenerContactos(),"Contactos",JOptionPane.ERROR_MESSAGE);
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
		int selectedIndex = listaGrupos.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Grupo grupoSeleccionado = listaGrupos.getSelectionModel().getSelectedItem();
			if (aplicacion.mostrarIngresoContacto(IngresoContactoOpciones.ANIADIR_NUEVO_GRUPO,grupoSeleccionado)) {
				ventanaPrincipal.actualizarEspacioLibreContactos();
				ventanaPrincipal.actualizarEspacioLibreGrupos();
			}
		} else {
			JOptionPane.showMessageDialog(null,"Elija un grupo","No hay un grupo seleccionado",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@FXML
	private void agregarContactoExistente() {
		int selectedIndex = listaGrupos.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Grupo grupoSeleccionado = listaGrupos.getSelectionModel().getSelectedItem();
			if (aplicacion.mostrarIngresoContacto(IngresoContactoOpciones.ANIADIR_GRUPO,grupoSeleccionado)) {
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
