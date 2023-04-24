package co.uniquindio.pr2.agenda.application;

import java.io.IOException;
import java.io.Serializable;

import javax.swing.JOptionPane;

import co.uniquindio.pr2.agenda.controllers.*;
import co.uniquindio.pr2.agenda.model.*;
import co.uniquindio.pr2.agenda.views.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Aplicacion extends Application implements Serializable{
	
	/*
	 * 
	 * 
	 * 
	 * Buen dia profe
	 * 
	 * 
	 * No alcance a documentar el codigo entero 
	 * debido a que tuve que hacer otros trabajos de otras materias, aparte de que 
	 * al vivir en Montenegro, me toma un buen tiempo llegar desde donde vivo a Armenia y viceversa, tambien tuve problemas con javaFX
	 * 
	 * 
	 */
	
	private Stage primaryStage;
	private AnchorPane rootLayout;
	
	private Agenda agenda;
	
	/**
	 * Metodo que guarda la agenda
	 * @param agenda Agenda a guardar
	 */
	public void setAgenda(Agenda agenda) {
		this.agenda=agenda;
	}
	
	/**
	 * Metodo que empieza a ejecutar la aplicacion
	 */
	@Override
	public void start(Stage primaryStage) {
		Agenda ventana1;
		ventana1=Persistencia.realizarCarga("src/archivo.dat") ;
        this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Agenda");
		initRootLayout();
		if(ventana1!=null)
        {
        	agenda=ventana1;
        	mostrarVentanaPrincipal();
        }
		
	}
	
	/**
	 * Inicia la ventana principal
	 */
	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/uniquindio/pr2/agenda/views/CreacionAgenda.fxml"));
			rootLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			CreacionAgendaController controlador = loader.getController();
			controlador.setAplicacionPrincipal(this);
			controlador.setVentana(primaryStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que muestra la ventana principal
	 */
	public void mostrarVentanaPrincipal() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/uniquindio/pr2/agenda/views/VentanaPrincipal.fxml"));
			rootLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			VentanaPrincipalController controller = loader.getController();
			controller.setAplicacionPrincipal(this);
			controller.setAgenda(agenda);
			controller.actualizarEspacioLibreContactos();
			controller.actualizarEspacioLibreGrupos();
			controller.actualizarEspacioLibreReuniones();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que muestra la ventana donde se ingresan datos d eun contacto
	 * @param accion Accion que se desea hacer con los datos
	 * @return Respuesta de que si se realizo la accion correspondiente o no
	 */
	public boolean mostrarIngresoContacto(IngresoContactoOpciones accion) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/uniquindio/pr2/agenda/views/IngresoDatosContacto.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			IngresoDatosContactoController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setAgenda(agenda);
			switch (accion) {
				case ANIADIR_GRUPO: controller.organizarAniadirGrupo(); break;
				case ANIADIR_NUEVO_GRUPO: controller.organizarNuevoGrupo(); break;
				case ANIADIR_REUNION: controller.organizarAniadirReunion(); break;
				case ANIADIR_NUEVO_REUNION: controller.organizarAniadirNuevoReunion(); break;
			}
			dialogStage.showAndWait();
			return controller.fueBotonPresionado();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Metodo que muestra la ventana donde se ingresan datos d eun contacto
	 * @param accion Accion que se desea hacer con los datos{
	 * @param 
	 * @return Respuesta de que si se realizo la accion correspondiente o no
	 */
	public boolean mostrarIngresoContacto(IngresoContactoOpciones accion,Reunion reunion) {
		try {
			JOptionPane.showMessageDialog(null, "Funcion aun en desarrollo");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/uniquindio/pr2/agenda/views/IngresoDatosContacto.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			IngresoDatosContactoController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setAgenda(agenda);
			controller.setReunion(reunion);
			switch (accion) {
				case ANIADIR_REUNION: controller.organizarAniadirReunion(); break;
				case ANIADIR_NUEVO_REUNION: controller.organizarAniadirNuevoReunion(); break;
			}
			dialogStage.showAndWait();
			return controller.fueBotonPresionado();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Metodo que muestra la ventana donde se ingresan datos d eun contacto
	 * @param accion Accion que se desea hacer con los datos{
	 * @param 
	 * @return Respuesta de que si se realizo la accion correspondiente o no
	 */
	public boolean mostrarIngresoContacto(IngresoContactoOpciones accion,Grupo grupo) {
		try {
			JOptionPane.showMessageDialog(null, "Funcion aun en desarrollo");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/uniquindio/pr2/agenda/views/IngresoDatosContacto.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			IngresoDatosContactoController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setAgenda(agenda);
			controller.setGrupo(grupo);
			switch (accion) {
				case ANIADIR_GRUPO: controller.organizarAniadirGrupo(); break;
				case ANIADIR_NUEVO_GRUPO: controller.organizarNuevoGrupo(); break;
			}
			dialogStage.showAndWait();
			return controller.fueBotonPresionado();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Metodo que muestra la ventana donde se busca un telefono por el nombre
	 */
	public void mostrarBusquedaTelefono() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/uniquindio/pr2/agenda/views/BusquedaTelefono.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Buscar contacto");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			BusquedaTelefonoController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setAgenda(agenda);
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que muestra la ventana donde lista los contactos guardados
	 */
	public void mostrarListaContactos() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/uniquindio/pr2/agenda/views/ListaContactos.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Contactos");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			ListaContactosController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setAgenda(agenda);
			controller.actualizarTabla();
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean mostrarCreacionGrupo() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/uniquindio/pr2/agenda/views/CreacionGrupo.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Contactos");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			CreacionGrupoController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setAgenda(agenda);
			dialogStage.showAndWait();
			return controller.fueGuardadoGrupo();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean mostrarCreacionReunion() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/uniquindio/pr2/agenda/views/CreacionReunion.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Contactos");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			CreacionReunionController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setAgenda(agenda);
			dialogStage.showAndWait();
			return controller.fueGuardadaReunion();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void mostrarListaGrupos(VentanaPrincipalController ventanaPrincipal) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/uniquindio/pr2/agenda/views/ListaGrupos.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Grupos");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			ListaGruposController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setventanaPrincipal(ventanaPrincipal);
			controller.setAgenda(agenda);
			controller.actualizarTabla();
			controller.setAplicacion(this);
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void mostrarListaReuniones(VentanaPrincipalController ventanaPrincipal) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/uniquindio/pr2/agenda/views/ListaReuniones.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Reuniones");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			ListaReunionesController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setventanaPrincipal(ventanaPrincipal);
			controller.setAgenda(agenda);
			controller.actualizarTabla();
			controller.setAplicacion(this);
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
