package core;

import java.util.HashMap;
import java.util.Map;

public class DeviceManager {
    private Map<String, DeviceConnection> connectedDevices;

    public DeviceManager() {
        this.connectedDevices = new HashMap<>();
    }

    public boolean connectDevice(String deviceId, DeviceConnection deviceConnection) {
        if (deviceConnection == null || deviceId == null || deviceId.isEmpty()) {
            return false;
        }
        connectedDevices.put(deviceId, deviceConnection);
        return true;
    }

    public boolean disconnectDevice(String deviceId) {
        if (deviceId == null || !connectedDevices.containsKey(deviceId)) {
            return false;
        }
        connectedDevices.remove(deviceId);
        return true;
    }

    public DeviceConnection getDeviceConnection(String deviceId) {
        return connectedDevices.get(deviceId);
    }

    public void checkDeviceStatus() {
        for (Map.Entry<String, DeviceConnection> entry : connectedDevices.entrySet()) {
            DeviceConnection connection = entry.getValue();
            if (!connection.isActive()) {
                System.out.println("Device " + entry.getKey() + " is not active.");
            }
        }
    }

    public void sendCommandToDevice(String deviceId, String command) {
        if (connectedDevices.containsKey(deviceId)) {
            DeviceConnection connection = connectedDevices.get(deviceId);
            connection.sendCommand(command);
        } else {
            System.out.println("Device " + deviceId + " not found.");
        }
    }

    public void receiveDataFromDevice(String deviceId, String data) {
        // Process the data received from the device
        System.out.println("Data received from device " + deviceId + ": " + data);
    }

    public static class DeviceConnection {
        private String deviceId;
        private boolean active;

        public DeviceConnection(String deviceId) {
            this.deviceId = deviceId;
            this.active = true;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public void sendCommand(String command) {
            // Send command to the actual device
            System.out.println("Command sent to device " + deviceId + ": " + command);
        }
    }
}