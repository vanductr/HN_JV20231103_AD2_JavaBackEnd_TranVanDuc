package ra.hackathon;

import ra.validation.InputMethods;

import java.util.Stack;

public class ISBNValidator {

    public static void main(String[] args) {
        String isbn;
        while (true) {
            // Bước 1: Lấy số ISBN từ người dùng
            System.out.print("Nhập số ISBN (10 chữ số): ");
            isbn = InputMethods.getString();

            // Bước 2: Kiểm tra xem số ISBN có 10 chữ số không
            if (isbn.length() != 10) {
                System.err.println("Số ISBN phải có đúng 10 chữ số, hãy kiểm tra lại!");
            } else {
                break;
            }
        }

        // Bước 3: Sử dụng Stack để tính tổng theo công thức
        Stack<Integer> digits = new Stack<>();
        for (int i = 0; i < isbn.length(); i++) {
            char digitChar = isbn.charAt(i);
            if (!Character.isDigit(digitChar) && i != 9) {
                System.out.println("Số ISBN chỉ được chứa các chữ số từ 0 đến 9.");
                return;
            }
            digits.push(Character.getNumericValue(digitChar));
        }

        int sum = 0;
        int multiplier = 1;
        while (!digits.isEmpty()) {
            int digit = digits.pop();
            sum += digit * multiplier;
            multiplier++;
        }

        // Bước 4: Kiểm tra điều kiện và đưa ra kết luận
        if (sum % 11 == 0) {
            System.out.println("Số ISBN hợp lệ.");
        } else {
            System.out.println("Số ISBN không hợp lệ.");
        }
    }
}

