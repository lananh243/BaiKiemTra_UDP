package BAIKIEMTRA;
import java.util.*;
import java.net.*;
import java.io.*;

public class Client_UDP_SinhVien {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 9999);  
        Scanner sc = new Scanner(System.in);
        DataInputStream din = new DataInputStream(client.getInputStream());
        DataOutputStream dout = new DataOutputStream(client.getOutputStream());
        
        String studentID = "";
        boolean validInput = false;
        
        while (!validInput) {
            try {
                System.out.print("Nhập mã sinh viên: ");
                studentID = sc.nextLine();
                validInput = true;
            } catch (Exception e) {
                System.out.println("Vui lòng nhập mã sinh viên hợp lệ.");
            }
        }
        
        dout.writeUTF(studentID);
        
        int sumEven = din.readInt();  
        String primeNumbers = din.readUTF();  
        
        System.out.println("Tổng các số chẵn trong mã sinh viên: " + sumEven);
        System.out.println("Các số nguyên tố trong mã sinh viên: " + primeNumbers);
        
        client.close();
    }
}
