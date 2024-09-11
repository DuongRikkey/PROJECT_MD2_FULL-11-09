package presentation.user.forgotPassword;

import business.entity.Users;
import business.features.impl.UsersImpl;

import java.util.Scanner;

public class ForgotPassword {
    public  static UsersImpl usersImpl = new UsersImpl();
    public static void showForgotPassword(Scanner sc) {
        System.out.print("Nhập tài khoản của bạn: ");
        String username = sc.nextLine();
        System.out.print("Nhập email của bạn: ");
        String email = sc.nextLine();

        // Tìm người dùng theo tài khoản và email
        Users user = UsersImpl.findByUsernameAndEmail(username, email);

        if (user != null) {
            // Hiển thị mật khẩu trên console
            System.out.println("Mật khẩu của bạn là: " + user.getPassword());
        } else {
            System.err.println("Tài khoản hoặc email không đúng.");
        }
    }
}
