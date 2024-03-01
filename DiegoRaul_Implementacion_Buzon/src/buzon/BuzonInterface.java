package buzon;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BuzonInterface extends Remote {
    void enviarMensaje(String remitente, String destinatario, String mensaje) throws RemoteException;
    List<String> consultarMensajes(String destinatario) throws RemoteException;
}
