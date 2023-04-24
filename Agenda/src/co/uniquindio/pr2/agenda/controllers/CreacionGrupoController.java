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
	
	public void setAgenda(Agenda agenda) {
		this.agenda=agenda;
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage=dialogStage;
	}
	
	public boolean fueGuardadoGrupo() {
		return fueGuardado;
	}
	
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
	
	@FXML
	private void initialize() {
		ObservableList<String> categoriaGrupo=FXCollections.observableArrayList();
		categoriaGrupo.add("Oficina");
		categoriaGrupo.add("Fiesta");
		categoriaGrupo.add("Amigos");
		categoriaGrupo.add("Familia");
		categoria.setItems(categoriaGrupo);
	}
	
	@FXML
	private void volver() {
		dialogStage.close();
	}
	
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
