package co.uniquindio.pr2.agenda.controllers;

import javax.swing.JOptionPane;

import co.uniquindio.pr2.agenda.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreacionGrupoController {
	
	@FXML
	private TextField nombre;
	@FXML
	private ComboBox categoria;
	private Stage dialogStage;
	private Agenda agenda;
	private boolean fueGuardado=false;
	
	/**
	 * Metodo que obtiene la agenda
	 * @param agenda Agenda
	 */
	public void setAgenda(Agenda agenda) {
		this.agenda=agenda;
	}
	
	/**
	 * Metodo que obtiene la direccion de la ventana donde se crean los grupos
	 * @param dialogStage Direccion de ventana
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage=dialogStage;
	}
	
	/**
	 * Metodo que retorna la respuesta de que si el grupo se guardo o no
	 * @return Si el grupo se guardo o no
	 */
	public boolean fueGuardadoGrupo() {
		return fueGuardado;
	}
	
	/**
	 * Metodo que verifica si los datos ingresados son validos
	 * @return Respuesta de que si los datos son validos o no
	 */
	public boolean sonDatosValidos() {
		boolean sonValidos=false;
		String msj="";
		if (nombre.getText()==null || nombre.getText().trim().equals("")) {
			msj+="El nombre del grupo no debe estar vacio";
		}
		if (categoria.getValue()==null || ((String) categoria.getValue()).trim().equals("")) {
			msj+="\n\nDebe seleccionar una categoria";
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
		ObservableList<String> categoriaGrupo=FXCollections.observableArrayList();
		categoriaGrupo.add("Oficina");
		categoriaGrupo.add("Fiesta");
		categoriaGrupo.add("Amigos");
		categoriaGrupo.add("Familia");
		categoria.setItems(categoriaGrupo);
	}
	
	/**
	 * Metodo que cierra la ventana
	 */
	@FXML
	private void volver() {
		dialogStage.close();
	}
	
	/**
	 * Metodo que guarda el grupo
	 */
	@FXML
	private void guardar() {
		String nombre;
		Categoria categoria=null;
		if (sonDatosValidos()) {
			try {
				nombre=this.nombre.getText();
				switch ((String)this.categoria.getValue()) {
					case "Oficina": categoria=Categoria.OFICINA; break;
					case "Fiesta": categoria=Categoria.FIESTA; break;
					case "Amigos": categoria=Categoria.AMIGOS; break;
					case "Familia": categoria=Categoria.FAMILIA; break;
				}
				Grupo grupo=new Grupo(nombre,categoria,10);
				if (agenda.existeGrupo(grupo)) {
					JOptionPane.showMessageDialog(null, "El grupo ya existe","Grupo ya existente",JOptionPane.WARNING_MESSAGE);
				} else {
					agenda.aniadirGrupo(grupo);
					fueGuardado=true;
					dialogStage.close();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "La agenda esta llena","Agenda llena",JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
}
