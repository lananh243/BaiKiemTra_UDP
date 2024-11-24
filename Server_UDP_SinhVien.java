package BAIKIEMTRA;
import java.util.*;
import java.net.*;
import java.io.*;


public class Server_UDP_SinhVien {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);  // Tạo ServerSocket lắng nghe cổng 9999
        System.out.println("Server đang chạy và chờ kết nối...");

        while (true) {
            Socket clientSocket = server.accept();
            System.out.println("Đã kết nối với client!");

            DataInputStream din = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());
            
            String studentID = din.readUTF();
            System.out.println("Mã sinh viên nhận được: " + studentID);
            
            int sumEven = sumEvenDigits(studentID);
            
            String primeNumbers = findPrimeNumbers(studentID);
            
            dout.writeInt(sumEven);
            dout.writeUTF(primeNumbers);
        }
    }

    public static int sumEvenDigits(String studentID) {
        int sum = 0;
        for (int i = 0; i < studentID.length(); i++) {
            char ch = studentID.charAt(i);
            if (Character.isDigit(ch)) {
                int digit = Character.getNumericValue(ch);
                if (digit % 2 == 0) {
                    sum += digit;
                }
            }
        }
        return sum;
    }

    public static String findPrimeNumbers(String studentID) {
        StringBuilder primes = new StringBuilder();
        for (int i = 0; i < studentID.length(); i++) {
            char ch = studentID.charAt(i);
            if (Character.isDigit(ch)) {
                int digit = Character.getNumericValue(ch);
                if (isPrime(digit)) {
                    primes.append(digit).append(" ");
                }
            }
        }
        return primes.toString().trim();
    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
