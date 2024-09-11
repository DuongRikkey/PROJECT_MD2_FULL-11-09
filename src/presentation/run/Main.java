package presentation.run;

import business.constants.RoleName;
import business.entity.Users;
import business.features.IUserImpl;
import business.features.impl.UsersImpl;
import business.utils.IOFILE;
import business.utils.InputMethod;
import business.utils.ShopConstanst;
import presentation.admin.MenuAdmin;
import presentation.user.GuestView;
import presentation.user.MenuUser;
import presentation.user.forgotPassword.ForgotPassword;

import java.util.Scanner;

public class Main {
    public static IUserImpl userimpl = new UsersImpl();
    public static Users userLogin=null;
    private static final String RESET = "\033[0m";
    private static final String[] RAINBOW_COLORS = {
            "\033[31m", // Red
            "\033[33m", // Yellow
            "\033[32m", // Green
            "\033[36m", // Cyan
            "\033[34m", // Blue
            "\033[35m", // Magenta
            "\033[37m"  // White
    };
    public static void main(String[] args) {
        Main main = new Main();

        Scanner sc = new Scanner(System.in);
        do {
            final String PURPLE = "\033[35m";  // ANSI code for purple (border)
            final String YELLOW = "\033[33m";  // ANSI code for yellow (text)
            final String RESET = "\033[0m";
            String[] lines = {
                    " __          __     _                            _______        _         ",
                    "\\ \\        / /    | |                          |__   __|      | |        ",
                    " \\ \\  /\\  / /__  | | ___  ___ _ __ ___   ___     | | ___   ___ | |_ ___   ",
                    "  \\ \\/  \\/ / _ \\ | |/ _ \\/ __| '_ ` _ \\ / _ \\    | |/ _ \\ / _ \\| __/ _ \\  ",
                    "   \\  /\\  /  __/ | |  __/\\__ \\ | | | |  __/    | | (_) |  __/| || (_) | ",
                    "    \\/  \\/ \\___| |_|\\___||___/|_| |_|\\___|    |_|\\___/ \\___/ \\__\\___/  "
            };

            for (int i = 0; i < lines.length; i++) {
                // Chọn màu từ mảng màu theo chỉ số
                String color = RAINBOW_COLORS[i % RAINBOW_COLORS.length];
                // In dòng chữ với màu sắc cầu vồng
                System.out.println(color + lines[i] + RESET);
            }

            System.out.println(PURPLE + "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃                                                                           ┃");
            System.out.println("┃" + YELLOW + "                             M A I N  M E N U                              " + PURPLE + "┃");
            System.out.println("┃                                                                           ┃");
            System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
            System.out.println("┃                                                                           ┃");
            System.out.println("┃" + YELLOW + "       1. Visit Shop                 " + PURPLE + "┃" + YELLOW + "        2. Login                     " + PURPLE + "┃");
            System.out.println("┃                                                                           ┃");
            System.out.println("┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃");
            System.out.println("┃                                                                           ┃");
            System.out.println("┃" + YELLOW + "       3. Register                   " + PURPLE + "┃" + YELLOW + "       4. Forgot password            " + PURPLE + "┃");
            System.out.println("┃                                                                           ┃");
            System.out.println("┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃");
            System.out.println("┃                                                                           ┃");
            System.out.println("┃" + YELLOW + "                                 5. Exit                                   " + PURPLE + "┃");
            System.out.println("┃                                                                           ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛" + RESET);
            System.out.print("Lựa chọn của bạn: ");

            int choice = InputMethod.getInteger();
            switch (choice) {
                case 1:
                    GuestView.showGuestView();
                    break;
                case 2: {
                    main.handleLogin(sc);
                    break;
                }
                case 3: {
                    main.handleRegister(sc);
                    break;
                }
                case 4: {
                    ForgotPassword.showForgotPassword(sc);
                    break;
                }
                case 5: {
                    System.exit(0);
                    break;
                }
                default:
                    System.err.println("Vui lòng nhập lại từ 1 -> 5");
            }
        }
        while (true);
    }

    private void showForgotPassword(Scanner sc) {

    }


    private void handleRegister(Scanner sc) {
        Users users = new Users();
        users.inputData(sc, true);
        userimpl.addAndUpdate(users);
    }
    public void handleLogin(Scanner sc) {
        Users users = new Users();
        users.inputLogin(sc);
        users = userimpl.login(users);
        if (users == null) {
            System.err.println("Tên đăng nhập hoặc mật khẩu sai");
            return; // Added to stop further execution if login fails
        }
        if (users.getDeleted()){
            System.err.println("Tài khoản không tồn tại");
            return;
        }
        if (users.getRoleName().equals(RoleName.ROLE_USER) && !users.getStatus()){
            System.err.println("Tài khoản đã bị khóa");
            return;
        }
        if (users.getRoleName().equals(RoleName.ROLE_USER) && users.getDeleted()){
            System.err.println("Tài khoản không tồn tại");
            return;
        }
        userLogin = users;

        if (users.getRoleName().equals(RoleName.ROLE_ADMIN)) {
            MenuAdmin Admin = new MenuAdmin();
            Admin.menuAdmin(sc);
        } else {
            MenuUser User = new MenuUser();
            User.menuUser(sc);
        }


    }
}
