package co.uniquindio.pr2.agenda.controllers;


import co.uniquindio.pr2.agenda.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ListaContactosController {
	
	@FXML
	private TableView<Contacto> tablaContactos;
	@FXML
	private TableColumn<Contacto,String> ColumnaNombre;
	@FXML
	private TableColumn<Contacto,String> ColumnaAlias;
	@FXML
	private TableColumn<Contacto,String> ColumnaDireccion;
	@FXML
	private TableColumn<Contacto,String> ColumnaTelefono;
	@FXML
	private TableColumn<Contacto,String> ColumnaEmail;
	@FXML
	private TableColumn<Contacto,String> ColumnaGrupos;
	@FXML
	private TableColumn<Contacto,String> ColumnaReuniones;
	private Agenda agenda;
	private ObservableList<Contacto> listaContactosProperty=FXCollections.observableArrayList();
	private Stage dialogStage;
	
	/**
	 * Metodo que inicializa la clase controlador
	 */
	@FXML
	private void initialize() {
		
	}
	
	/**
	 * Metodo que actualiza y muestra los datos d elos contactos en la tabla
	 */
	public void actualizarTabla() {
		for (Contacto contacto : agenda.listarContactos()) {
			if (contacto!=null) {
				listaContactosProperty.add(contacto);
			}
		}
		tablaContactos.setItems(listaContactosProperty);
		ColumnaNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
		ColumnaAlias.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAlias()));
		ColumnaDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDireccion()));
		ColumnaTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
		ColumnaEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
		ColumnaGrupos.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getListaGrupos().length));
		ColumnaReuniones.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getListaReuniones().length));
	}
	
	/**
	 * Metodo que cierra la ventana
	 */
	@FXML
	private void volver() {
		dialogStage.close();
	}

	/**
	 * Metodo que obtiene la direccion de la ventana donde aparece la lista de contactos
	 * @param dialogStage Ventana donde aparece la lista de contactos
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

}
