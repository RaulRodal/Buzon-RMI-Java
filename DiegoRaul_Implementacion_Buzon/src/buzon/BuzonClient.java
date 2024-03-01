package buzon;

import java.rmi.Naming;
import java.util.List;

import javax.swing.JOptionPane;

public class BuzonClient {
	 public static void main(String[] args) {
		 int response = -1;
		 do {
		 String[] options = new String[] {"Enviar mensaje", "Revisar buzón"};
		    response = JOptionPane.showOptionDialog(null, "¿Desea revisar su buzon o enviar un mensaje?", "Selecciona",
		        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
		        null, options, options[0]);

	        String nombreUsuario = JOptionPane.showInputDialog("Introduzca su nombre de usuario");

	        try {
	            BuzonInterface servidor = (BuzonInterface) Naming.lookup("rmi://localhost/BuzonServer");

	            switch (response) {
	                case 0:
	                    String destinatario = JOptionPane.showInputDialog("Introduzca el nombre de usuario DESTINATARIO");;
	                    String mensaje = JOptionPane.showInputDialog("Introduzca mensaje");;
	                    servidor.enviarMensaje(nombreUsuario, destinatario, mensaje);
	                    break;
	                case 1:
	                    List<String> mensajes = servidor.consultarMensajes(nombreUsuario);
	                    if (mensajes.isEmpty()) {
	                    	JOptionPane.showMessageDialog(null, "No hay mensajes.");
	                    } else {
	                        for (String msg : mensajes) {
	                        	JOptionPane.showMessageDialog(null, msg);
	                        }
	                    }
	                    break;
	                default:
	                	JOptionPane.showMessageDialog(null, "Operación no válida.");
	                    break;
	            }
	        } catch (Exception e) {
	            System.err.println("Error en el cliente: " + e.toString());
	            e.printStackTrace();
	        }
		 }while (response == 0 || response == 1);
	    }
	}
