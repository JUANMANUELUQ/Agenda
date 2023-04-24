package co.uniquindio.pr2.agenda.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Persistencia {
      public static void serialize(String nombreArchivo, Object data) {
      FileOutputStream fos = null;
      ObjectOutputStream oos = null;
       try {
       fos = new FileOutputStream(nombreArchivo);
       oos = new ObjectOutputStream(fos);
       
       oos.writeObject(data);
       oos.close();
       fos.close();
      } catch(Exception e) {
       e.printStackTrace();
      }
     }
     
     public static Object deserialize(String nombreArchivo) {
          Object data = null;
          try {
           FileInputStream fis = new FileInputStream(nombreArchivo);
           ObjectInputStream ois = new ObjectInputStream(fis);
           data =  ois.readObject();
           ois.close();
           fis.close();
          } catch(Exception e) {
           e.printStackTrace();
          }
          return data;
         }
     
     public static Agenda realizarCarga(String ruta) 
     {  
    	 Agenda  p=null;
    	 Agenda ventana1=null;
      File fOrig;
      fOrig = new File(ruta);
      if(fOrig.exists ( ))
       {
           p= (Agenda) Persistencia.deserialize(ruta) ;
           ventana1=p;
       }
      
      return ventana1;
     }

     /*public static void guardarArchivo(String ruta,  VentanaPrincipal ventana1) throws Exception
     {
     Persistencia.serialize(ruta, ventana1);
     }*/
     
     
}