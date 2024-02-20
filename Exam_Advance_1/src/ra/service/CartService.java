package ra.service;

import ra.model.CartItem;
import ra.model.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CartService {
    private final List<CartItem> cartItems = new ArrayList<>();
    private final ProductService productService = new ProductService();

    public CartService() {
    }

    public void displayAllProducts() {
        List<Product> products = productService.getAll();
        System.out.println("=== Danh sách tất cả sản phẩm:");
        for (Product product : products) {
            System.out.println(product);
            System.out.println("--------------------------");
        }
    }

    public void addProductToCart(String productId) {
        Product product = productService.findById(productId);
        if (product != null && product.getStock() > 0) {
            boolean found = false;
            for (CartItem item : cartItems) {
                if (item.getProduct().getProductId().equals(productId)) {
                    item.setQuantity(item.getQuantity() + 1);
                    found = true;
                    break;
                }
            }
            if (!found) {
                cartItems.add(new CartItem(cartItems.size() + 1, product, product.getProductPrice(), 1));
            }

            // Giảm số lượng tồn kho của sản phẩm
            product.setStock(product.getStock() - 1);

            System.out.println("Sản phẩm đã được thêm vào giỏ hàng.");
        } else {
            System.out.println("Sản phẩm không tồn tại hoặc hết hàng.");
        }
    }


    public void displayCartItems() {
        if (cartItems.isEmpty()) {
            System.out.println("Giỏ hàng của bạn đang trống.");
        } else {
            System.out.println("Danh sách sản phẩm trong giỏ hàng:");
            for (CartItem item : cartItems) {
                System.out.println(item);
                System.out.println("------------------------");
            }
        }
    }

    // Xóa 1 sản phẩm ra khỏi giỏ hàng theo cartItemId
    public void removeProductFromCart(int cartItemId) {
        Iterator<CartItem> iterator = cartItems.iterator();
        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            if (item.getCartItemId() == cartItemId) {
                iterator.remove();
                System.out.println("Đã xóa sản phẩm khỏi giỏ hàng.");
                return;
            }
        }
        System.out.println("Không tìm thấy sản phẩm trong giỏ hàng.");
    }

    // Phương thức xóa toàn bộ sản phẩm trong giỏ hàng
    public void removeAllProductsFromCart() {
        cartItems.clear();
        System.out.println("Đã xóa toàn bộ sản phẩm trong giỏ hàng.");
    }

    // Phương thức thay đổi số lượng sản phẩm trong giỏ hàng
    public void updateCartItemQuantity(int cartItemId, int newQuantity) {
        // Tìm cartItem cần cập nhật số lượng
        CartItem cartItemToUpdate = null;
        for (CartItem cartItem : cartItems) {
            if (cartItem.getCartItemId() == cartItemId) {
                cartItemToUpdate = cartItem;
                break;
            }
        }

        if (cartItemToUpdate != null) {
            // Lấy số lượng hiện tại của sản phẩm trong giỏ hàng
            int currentQuantity = cartItemToUpdate.getQuantity();

            // Tính toán sự thay đổi số lượng
            int quantityChange = newQuantity - currentQuantity;

            // Kiểm tra số lượng tồn kho đủ để cập nhật không
            if (cartItemToUpdate.getProduct().getStock() + quantityChange >= 0) {
                // Cập nhật số lượng sản phẩm trong giỏ hàng
                cartItemToUpdate.setQuantity(newQuantity);

                // Cập nhật số lượng tồn kho tương ứng
                int updatedStock = cartItemToUpdate.getProduct().getStock() - quantityChange;
                cartItemToUpdate.getProduct().setStock(updatedStock);

                System.out.println("Số lượng sản phẩm đã được cập nhật thành công.");
            } else {
                System.out.println("Không đủ hàng trong kho để cập nhật số lượng sản phẩm.");
            }
        } else {
            System.out.println("Không tìm thấy sản phẩm trong giỏ hàng.");
        }
    }
}

