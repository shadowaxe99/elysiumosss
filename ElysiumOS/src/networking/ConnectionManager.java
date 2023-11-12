```java
package networking;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectionManager {

    private String serverAddress;
    private int port;
    private Socket socket;

    public ConnectionManager(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    public boolean establishConnection() {
        try {
            socket = new Socket(serverAddress, port);
            return true;
        } catch (UnknownHostException e) {
            System.err.println("Host unknown: " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection: " + e.getMessage());
            return false;
        }
    }

    public void closeConnection() {
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Error closing the socket: " + e.getMessage());
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    // Placeholder for future methods that handle data transmission
    // and other networking functionalities as per the system's requirements.

    // Additional methods to handle secure data transmission, reconnection strategies,
    // and other advanced networking features can be added here.
}
```