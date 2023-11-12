package networking;

import utils.NetworkManager;
import utils.SecurityModule;

public class NetworkController {
    private final NetworkManager networkManager;
    private final SecurityModule securityModule;

    public NetworkController(NetworkManager networkManager, SecurityModule securityModule) {
        this.networkManager = networkManager;
        this.securityModule = securityModule;
    }

    public boolean establishConnection(String url) {
        if (!securityModule.validateConnectionRequest(url)) {
            return false;
        }
        return networkManager.connect(url);
    }

    public boolean sendData(String url, String data) {
        if (!securityModule.validateOutgoingData(data)) {
            return false;
        }
        return networkManager.send(url, data);
    }

    public String receiveData(String url) {
        String data = networkManager.receive(url);
        if (securityModule.validateIncomingData(data)) {
            return data;
        }
        return null;
    }

    public void disconnect(String url) {
        networkManager.disconnect(url);
    }

    public boolean isConnected(String url) {
        return networkManager.isConnected(url);
    }
}