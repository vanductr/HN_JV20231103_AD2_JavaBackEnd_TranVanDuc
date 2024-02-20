package ra.controller;

import ra.service.CartService;
import ra.validation.InputMethods;

public class UserController {
    private static CartService cartService = new CartService();
    public static void userMenu() {
        while (true) {
            System.out.println("**************************MENU-USER**************************");
            System.out.println("1. Xem danh sách sản phẩm");
            System.out.println("2. Thêm vào giỏ hàng");
            System.out.println("3. Xem tất cả sản phẩm trong giỏ hàng");
            System.out.println("4. Thay đổi số lượng sản phẩm trong giỏ hàng");
            System.out.println("5. Xóa 1 sản phẩm trong giỏ hàng");
            System.out.println("6. Xóa toàn bộ sản phẩm trong giỏ hàng");
            System.out.println("7. Quay lại");
            System.out.print("Nhập lựa chọn của bạn: ");
            int choice = InputMethods.getInteger();

            switch (choice) {
                case 1:
                    cartService.displayAllProducts();
                    break;
                case 2:
                    addToCart();
                    break;
                case 3:
                    cartService.displayCartItems();
                    break;
                case 4:
                    updateCartItemQuantityController();
                    break;
                case 5:
                    removeCartItem();
                    break;
                case 6:
                    cartService.removeAllProductsFromCart();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    private static void addToCart() {
        System.out.print("Nhập mã sản phẩm muốn thêm vào giỏ hàng: ");
        String productId = InputMethods.getString();

        cartService.addProductToCart(productId);
    }

    public static void removeCartItem() {
        System.out.print("Nhập mã giỏ hàng(Id Cart) muốn xóa khỏi giỏ hàng: ");
        int cartItemId = InputMethods.getInteger();

        // Gọi phương thức removeProductFromCart từ CartService
        cartService.removeProductFromCart(cartItemId);
    }

    // Phương thức điều hướng cho chức năng thay đổi số lượng sản phẩm trong giỏ hàng
    public static void updateCartItemQuantityController() {
        System.out.print("Nhập cartItemId của sản phẩm muốn thay đổi số lượng: ");
        int cartItemId = InputMethods.getInteger();
        System.out.print("Nhập số lượng mới: ");
        int newQuantity = InputMethods.getInteger();

        cartService.updateCartItemQuantity(cartItemId, newQuantity);
    }
}
