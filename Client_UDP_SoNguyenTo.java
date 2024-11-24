package BAIKIEMTRA;

import java.net.*;
import java.util.Scanner;

public class Client_UDP_SoNguyenTo {
    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9999;

            Scanner scanner = new Scanner(System.in);
            int n;

            while (true) {
                try {
                    System.out.print("Nhập số nguyên dương n: ");
                    n = scanner.nextInt();
                    if (n > 0) {
                        break;
                    } else {
                        System.out.println("Vui lòng nhập một số nguyên dương lớn hơn 0.");
                    }
                } catch (Exception e) {
                    System.out.println("Dữ liệu không hợp lệ. Vui lòng nhập lại.");
                    scanner.nextLine(); 
                }
            }

            String sendData = String.valueOf(n);
            byte[] sendBuffer = sendData.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
            client.send(sendPacket);

            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            client.receive(receivePacket);

            String result = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Các số nguyên tố nhỏ hơn " + n + " chia hết cho 5: " + result);

            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
