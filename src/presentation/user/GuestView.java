package presentation.user;

import business.features.impl.productImpl.User.ProductFeaturesForUser;
import business.utils.Colors;
import business.utils.InputMethod;


import java.awt.*;

public class GuestView {

    public static void showGuestView() {
        boolean isLoop=true;

        do {
            System.out.println(Colors.PURPLE +"┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓" + Colors.RESET);
            System.out.println(Colors.PURPLE +"┃                           ┃                             ┃" + Colors.RESET);
            System.out.println(Colors.PURPLE +"┃        1. Products        ┃          2. Thoát           ┃" +Colors.RESET);
            System.out.println(Colors.PURPLE+ "┃                           ┃                             ┃" + Colors.RESET);
            System.out.println(Colors.PURPLE+ "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛" + Colors.RESET);
            System.out.println("Mời bạn lựa chọn");
            int choice = InputMethod.getInteger();
            switch (choice) {
                case 1:{
                    ProductFeaturesForUser.displayAllProductsUser();
                    break;
                }
                case 2:{
                    isLoop=false;
                    break;
                }
                default:
                    System.err.println("Lựa chọn 1 hoặc 2;");

            }
        } while (isLoop);
    }
}
