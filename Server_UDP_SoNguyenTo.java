package BAIKIEMTRA;

import java.net.*;

public class Server_UDP_SoNguyenTo {
    public static void main(String[] args) {
        try {
            DatagramSocket server = new DatagramSocket(9999);
            System.out.println("Server đang chạy...");

            while (true) {
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                server.receive(receivePacket);

                String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
                int n = Integer.parseInt(receivedData);

                StringBuilder result = new StringBuilder();
                for (int i = 5; i < n; i++) {
                    if (i % 5 == 0 && isPrime(i)) {
                        result.append(i).append(" ");
                    }
                }

                String resultData = result.length() > 0 ? result.toString().trim() : "Không có số nào thỏa mãn.";
                byte[] sendBuffer = resultData.getBytes();
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
                server.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
