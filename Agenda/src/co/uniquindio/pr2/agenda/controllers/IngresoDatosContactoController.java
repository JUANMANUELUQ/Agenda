package co.uniquindio.pr2.agenda.controllers;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import co.uniquindio.pr2.agenda.model.*;

public class IngresoDatosContactoController {
	
	@FXML
	private Label etiquetaCentral;
	@FXML
	private TextField nombre;
	@FXML
	private TextField alias;
	@FXML
	private TextField direccion;
	@FXML
	private TextField telefono;
	@FXML
	private TextField email;
	@FXML
	private TextField numeroGrupos;
	@FXML
	private TextField numeroReuniones;
	@FXML
	private Button boton;
	private IngresoContactoOpciones opcion;
	private boolean fueBotonPresionado=false;
	private Stage dialogStage;
	private Agenda agenda;
	private Object objeto;
	private Reunion reunion;
	private Grupo grupo;
	
	/**
	 * Metodo constructor
	 */
	public IngresoDatosContactoController() {
		
	}
	
	/**
	 * Metodo que obtiene la direccion de la ventana donde se ingresan datos de un contacto
	 * @param dialogStage Ventana donde se ingresan datos de un contacto
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
	 * Metodo que verifica si se presiono el boton para realizar la accion correspondiente
	 * @return 	Respuesta de que si presiono el boton o no
	 */
	public boolean fueBotonPresionado() {
		return fueBotonPresionado;
	}
	
	/**
	 * Metodo que bloquea las entradas que no son necesarias para la accion correspondiente
	 */
	public void bloquearEntradas() {
		alias.setEditable(false);
		alias.setText("N/A");
		alias.setStyle("-fx-background-color: #aaaaaa;");
		direccion.setEditable(false);
		direccion.setStyle("-fx-background-color: #aaaaaa;");
		direccion.setText("N/A");
		email.setEditable(false);
		email.setStyle("-fx-background-color: #aaaaaa;");
		email.setText("N/A");
		numeroGrupos.setEditable(false);
		numeroGrupos.setStyle("-fx-background-color: #aaaaaa;");
		numeroGrupos.setText("N/A");
		numeroReuniones.setEditable(false);
		numeroReuniones.setStyle("-fx-background-color: #aaaaaa;");
		numeroReuniones.setText("N/A");
	}

	/**
	 * Metodo que organiza la ventana para la accion para la accion de aniadir un contacto
	 */
	public void organizarAniadirContacto() {
		dialogStage.setTitle("A\u00F1adir contacto");
		opcion=IngresoContactoOpciones.ANIADIR;
		etiquetaCentral.setText("Ingrese los datos del contacto que desea a\u00F1adir");
		boton.setText("Guadar");
	}

	/**
	 * Metodo que organiza la ventana para la accion de buscar un contacto
	 */
	public void organizarBuscarContacto() {
		dialogStage.setTitle("Buscar contacto");
		opcion=IngresoContactoOpciones.BUSCAR;
		etiquetaCentral.setText("Ingrese los datos del contacto que desea buscar");
		boton.setText("Buscar");
		bloquearEntradas();
	}

	/**
	 * Metodo que organiza la ventana para la accion de eliminar un contacto
	 */
	public void organizarEliminarContacto() {
		dialogStage.setTitle("Eliminar contacto");
		opcion=IngresoContactoOpciones.ELIMINAR;
		etiquetaCentral.setText("Ingrese los datos");
		boton.setText("Eliminar");
		bloquearEntradas();
	}
	
	/**
	 * Metodo que verifica si los datos introducidos son validos para la accion de aniadir un contacto
	 * @return Respuesta de que si los datos son validos o no
	 */
	public boolean sonDatosValidosAniadir() {
		boolean sonValidos=false;
		String msj="";
		if (nombre.getText()==null || nombre.getText().trim().equals("")) {
			msj+="\n\nEl nombre no debe estar vacio";
		}
		if (alias.getText()==null || alias.getText().trim().equals("")) {
			msj+="\n\nEl alias no debe estar vacio";
		}
		if (direccion.getText()==null || direccion.getText().trim().equals("")) {
			msj+="\n\nLa direccion no debe estar vacia";
		}
		if (telefono.getText()==null || telefono.getText().trim().equals("")) {
			msj+="\n\nEl nombre no debe estar vacio";
		} else if (!telefono.getText().trim().matches("[0-9]+")) {
			msj+="\n\nValor del numero de telefono no valido";
		}
		if (email.getText()==null || email.getText().trim().equals("")) {
			msj+="\n\nEl email no debe estar vacio";
		}
		try {
			if (Integer.parseInt(numeroGrupos.getText())<0) {
				throw new Exception("Valor invalido");
			}
		} catch (Exception e) {
			if (numeroGrupos.getText().equals("")) {
				msj+="\n\nEl numero de grupos no debe estar vacio";
			} else {
				msj+="\n\nEl numero de grupos no es valido";
			}
		}
		try {
			if (Integer.parseInt(numeroReuniones.getText())<0) {
				throw new Exception("Valor invalido");
			}
		} catch (Exception e) {
			if (numeroReuniones.getText().equals("")) {
				msj+="\n\nEl numero de reuniones no debe estar vacio";
			} else {
				msj+="\n\nEl numero de reuniones no es valido";
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
	 * Metodo que verifica si los datos introducidos son validos para las acciones de buscar o eliminar un contacto
	 * @return Respuesta de que si los datos son validos o no
	 */
	public boolean sonDatosValidos() {
		boolean sonValidos=false;
		String msj="";
		if (nombre.getText()==null || nombre.getText().trim().equals("")) {
			msj+="El nombre no debe estar vacio";
		}
		if (telefono.getText()==null || telefono.getText().trim().equals("")) {
			msj+="\n\nEl nombre no debe estar vacio";
		} else if (!telefono.getText().trim().matches("[0-9]+")) {
			msj+="\n\nValor del numero de telefono no valido";
		}
		if (msj.equals("")) {
			sonValidos=true;
		} else {
			JOptionPane.showMessageDialog(null, msj,"Entradas no validas",JOptionPane.ERROR_MESSAGE);
		}
		return sonValidos;
	}
	
	/**
	 * Metodo que aniade un contacto
	 */
	public void aniadirContacto() {
		int numGrupos=Integer.parseInt(numeroGrupos.getText());
		int numReuniones=Integer.parseInt(numeroReuniones.getText());
		Contacto contacto=new Contacto(nombre.getText(),alias.getText(),direccion.getText(),telefono.getText(),email.getText(),numGrupos,numReuniones);
		try {
			agenda.aniadirContacto(contacto);
			fueBotonPresionado=true;
			dialogStage.close();
		} catch (Exception e) {
			if (agenda.agendaLlena()) {
				JOptionPane.showMessageDialog(null, "La agenda esta llena","Agenda llena",JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "El contacto ya existe","Contacto existente",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Metodo que busca un contacto
	 */
	public void buscarContacto() {
		Contacto contacto=new Contacto(nombre.getText(),null,null,telefono.getText(),"",0,0);
		if (agenda.existeContacto(contacto)) {
			JOptionPane.showMessageDialog(null, "El contacto si existe","Contacto existente",JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "El contacto no existe","Contacto inexistente",JOptionPane.INFORMATION_MESSAGE);
		}
		dialogStage.close();
	}

	/**
	 * Metodo que elimina un contacto
	 */
	public void eliminarContacto() {
		Contacto contacto=new Contacto(nombre.getText(),null,null,telefono.getText(),"",0,0);
		try {
			agenda.eliminarContacto(contacto);
			fueBotonPresionado=true;
			dialogStage.close();
		} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "El contacto no existe","Contacto inexistente",JOptionPane.ERROR_MESSAGE);
		}
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
	 * Metodo que una accion al presionar el boton que hace la accion correspondiente
	 */
	@FXML
	private void boton() {
		boolean entradasValidas=false;
		if (opcion==IngresoContactoOpciones.ANIADIR && sonDatosValidosAniadir()) {
			entradasValidas=true;
		} else if ((opcion==IngresoContactoOpciones.BUSCAR || opcion==IngresoContactoOpciones.ELIMINAR) && sonDatosValidos()) {
			entradasValidas=true;
		}
		if (entradasValidas) {
			switch (opcion) {
				case ANIADIR: aniadirContacto(); break;
				case BUSCAR: buscarContacto(); break;
				case ELIMINAR: eliminarContacto(); break;
				case ANIADIR_REUNION: aniadirReunion(); break;
				case ANIADIR_GRUPO: aniadirGrupo();  break;
				case ANIADIR_NUEVO_REUNION: aniadirNuevoReunion(); break;
				case ANIADIR_NUEVO_GRUPO: nuevoGrupo();  break;
			}
		}
	}

	public void organizarAniadirGrupo() {
		dialogStage.setTitle("A\u00F1adir contacto existente a grupo");
		opcion=IngresoContactoOpciones.ANIADIR_GRUPO;
		etiquetaCentral.setText("Ingrese los datos del contacto que desea ingresar al grupo");
		boton.setText("Guardar");
		bloquearEntradas();
	}

	public void organizarNuevoGrupo() {
		dialogStage.setTitle("A\u00F1adir contacto nuevo existente a grupo");
		opcion=IngresoContactoOpciones.ANIADIR_NUEVO_GRUPO;
		etiquetaCentral.setText("Ingrese los datos del contacto que desea ingresar al grupo");
		boton.setText("Guardar");
	}

	public void organizarAniadirReunion() {
		dialogStage.setTitle("A\\u00F1adir contacto existente a reunion");
		opcion=IngresoContactoOpciones.ANIADIR_REUNION;
		etiquetaCentral.setText("Ingrese los datos del contacto que desea ingresar a la reunion");
		boton.setText("Guardar");
		bloquearEntradas();
	}

	public void organizarAniadirNuevoReunion() {
		dialogStage.setTitle("A\\u00F1adir contacto nuevo existente a reunion");
		opcion=IngresoContactoOpciones.ANIADIR_NUEVO_REUNION;
		etiquetaCentral.setText("Ingrese los datos del contacto que desea ingresar a la reunion");
		boton.setText("Guardar");
	}
	
	public void aniadirGrupo() {
		Contacto contacto=new Contacto(nombre.getText(),null,null,telefono.getText(),"",0,0);
		try {
			grupo.agregarContacto(contacto);
			fueBotonPresionado=true;
			dialogStage.close();
		} catch (Exception e) {
			if (grupo.obtenerPosicionDisponible()==-1) {
				JOptionPane.showMessageDialog(null, "El grupo esta llena","Grupo lleno",JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Contacto repetido","Contacto repetido",JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void nuevoGrupo() {
		int numGrupos=Integer.parseInt(numeroGrupos.getText());
		int numReuniones=Integer.parseInt(numeroReuniones.getText());
		Contacto contacto=new Contacto(nombre.getText(),alias.getText(),direccion.getText(),telefono.getText(),email.getText(),numGrupos,numReuniones);
		try {
			grupo.agregarContacto(contacto);
			fueBotonPresionado=true;
			dialogStage.close();
		} catch (Exception e) {
			if (grupo.obtenerPosicionDisponible()==-1) {
				JOptionPane.showMessageDialog(null, "El grupo esta llena","Grupo lleno",JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Contacto repetido","Contacto repetido",JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void aniadirReunion() {
		Contacto contacto=new Contacto(nombre.getText(),null,null,telefono.getText(),"",0,0);
		try {
			reunion.agregarContacto(contacto);
			fueBotonPresionado=true;
			dialogStage.close();
		} catch (Exception e) {
			if (reunion.obtenerPosicionDisponible()==-1) {
				JOptionPane.showMessageDialog(null, "El grupo esta llena","Grupo lleno",JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Contacto repetido","Contacto repetido",JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void aniadirNuevoReunion() {
		int numGrupos=Integer.parseInt(numeroGrupos.getText());
		int numReuniones=Integer.parseInt(numeroReuniones.getText());
		Contacto contacto=new Contacto(nombre.getText(),alias.getText(),direccion.getText(),telefono.getText(),email.getText(),numGrupos,numReuniones);
		try {
			reunion.agregarContacto(contacto);
			fueBotonPresionado=true;
			dialogStage.close();
		} catch (Exception e) {
			if (reunion.obtenerPosicionDisponible()==-1) {
				JOptionPane.showMessageDialog(null, "El grupo esta llena","Grupo lleno",JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Contacto repetido","Contacto repetido",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	

	public void setReunion(Reunion reunion) {
		this.reunion=reunion;
	}
	
	public void setGrupo(Grupo grupo) {
		this.grupo=grupo;
		
	}

}
