package hardware;

import utils.NetworkManager;
import utils.SecurityModule;

public class IoTIntegration {

    private NetworkManager networkManager;
    private SecurityModule securityModule;
    private boolean isConnected;

    public IoTIntegration(NetworkManager networkManager, SecurityModule securityModule) {
        this.networkManager = networkManager;
        this.securityModule = securityModule;
        this.isConnected = false;
    }

    public boolean connectToDevice(String deviceId, String authToken) {
        if (securityModule.validateToken(authToken)) {
            isConnected = networkManager.establishConnection(deviceId);
            if (isConnected) {
                System.out.println("Device connected successfully: " + deviceId);
            } else {
                System.out.println("Failed to connect to device: " + deviceId);
            }
        } else {
            System.out.println("Invalid authentication token for device connection.");
            isConnected = false;
        }
        return isConnected;
    }

    public void disconnectFromDevice(String deviceId) {
        if (isConnected) {
            networkManager.terminateConnection(deviceId);
            isConnected = false;
            System.out.println("Device disconnected successfully: " + deviceId);
        } else {
            System.out.println("No device connected to disconnect.");
        }
    }

    public void sendDataToDevice(String deviceId, String data) {
        if (isConnected) {
            networkManager.sendData(deviceId, data);
            System.out.println("Data sent to device: " + deviceId);
        } else {
            System.out.println("No device connected to send data.");
        }
    }

    public String receiveDataFromDevice(String deviceId) {
        if (isConnected) {
            String data = networkManager.receiveData(deviceId);
            System.out.println("Data received from device: " + deviceId);
            return data;
        } else {
            System.out.println("No device connected to receive data.");
            return null;
        }
    }

    public boolean isDeviceConnected() {
        return isConnected;
    }
}