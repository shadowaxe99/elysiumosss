package hardware;

import utils.NetworkManager;
import utils.SecurityModule;

public class HardwareInterface {

    private NetworkManager networkManager;
    private SecurityModule securityModule;
    private boolean isConnected;

    public HardwareInterface(NetworkManager networkManager, SecurityModule securityModule) {
        this.networkManager = networkManager;
        this.securityModule = securityModule;
        this.isConnected = false;
    }

    public boolean connectToDevice(String deviceAddress) {
        if (securityModule.validateConnectionRequest(deviceAddress)) {
            isConnected = networkManager.establishConnection(deviceAddress);
            if (isConnected) {
                System.out.println("Device connected successfully.");
            } else {
                System.out.println("Failed to connect to the device.");
            }
        } else {
            System.out.println("Connection request to device is not valid.");
        }
        return isConnected;
    }

    public void disconnectFromDevice(String deviceAddress) {
        if (isConnected && networkManager.terminateConnection(deviceAddress)) {
            isConnected = false;
            System.out.println("Device disconnected successfully.");
        } else {
            System.out.println("Failed to disconnect from the device.");
        }
    }

    public void sendDataToDevice(String data) {
        if (isConnected) {
            networkManager.sendData(data);
        } else {
            System.out.println("No device is connected to send data.");
        }
    }

    public String receiveDataFromDevice() {
        if (isConnected) {
            return networkManager.receiveData();
        } else {
            System.out.println("No device is connected to receive data.");
            return null;
        }
    }

    public boolean isDeviceConnected() {
        return isConnected;
    }
}