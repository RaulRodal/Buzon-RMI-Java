package buzon;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuzonServer extends UnicastRemoteObject implements BuzonInterface {
    private static final long serialVersionUID = 1L;
    private Map<String, List<String>> buzon = new HashMap<>();

    public BuzonServer() throws RemoteException {
        super();
    }

    @Override
    public synchronized void enviarMensaje(String remitente, String destinatario, String mensaje) throws RemoteException {
        List<String> mensajes = buzon.getOrDefault(destinatario, new ArrayList<>());
        mensajes.add(remitente + ": " + mensaje);
        buzon.put(destinatario, mensajes);
        System.out.println("Mensaje enviado correctamente.");
    }

    @Override
    public synchronized List<String> consultarMensajes(String destinatario) throws RemoteException {
        return buzon.getOrDefault(destinatario, new ArrayList<>());
    }

    public static void main(String[] args) {
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            BuzonServer servidor = new BuzonServer();
            java.rmi.Naming.rebind("BuzonServer", servidor);
            System.out.println("Servidor listo...");
        } catch (Exception e) {
            System.err.println("Error en el servidor: " + e.toString());
            e.printStackTrace();
        }
    }
}
