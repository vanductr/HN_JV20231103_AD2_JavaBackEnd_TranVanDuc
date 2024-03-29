package ra.model;

import ra.service.CatalogService;
import ra.validation.InputMethods;

import java.util.List;
import java.util.Scanner;

import static ra.validation.Validate.isValidProductId;

public class Product {
    private String productId;
    private String productName;
    private double productPrice;
    private String description;
    private int stock;
    private Catalog catalog;
    private boolean status;

    // Constructors
    public Product() {
    }

    public Product(String productId, String productName, double productPrice, String description, int stock, Catalog catalog, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.description = description;
        this.stock = stock;
        this.catalog = catalog;
        this.status = status;
    }

    // Getter and Setter methods

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData(Boolean isAdd) {
        // Nhập productId và kiểm tra tính hợp lệ
        if (isAdd) {
            while (true) {
                System.out.print("Nhập productId (bắt đầu bằng 'P' và theo sau là 4 ký tự số): ");
                String id = InputMethods.getString();
                if (isValidProductId(id)) {
                    this.productId = id;
                    break;
                } else {
                    System.out.println("ProductId không hợp lệ. Vui lòng thử lại.");
                }
            }
        }

        // Nhập tên sản phẩm
        System.out.print("Nhập tên sản phẩm: ");
        this.productName = InputMethods.getString();

        // Nhập giá sản phẩm
        System.out.print("Nhập giá sản phẩm: ");
        this.productPrice = InputMethods.getDouble();

        // Nhập mô tả sản phẩm
        System.out.print("Nhập mô tả sản phẩm: ");
        this.description = InputMethods.getString();

        // Nhập số lượng tồn kho
        System.out.print("Nhập số lượng tồn kho: ");
        this.stock = InputMethods.getInteger();

        // Nhập thông tin danh mục
        System.out.println("Danh sách tất cả danh mục:");
        CatalogService catalogService = new CatalogService();
        List<Catalog> catalogList = catalogService.getAll();
        for (Catalog catalog1 : catalogList) {
            System.out.println(catalog1);
            System.out.println("-------------------");
        }
        while (true) {
            System.out.print("Nhập id để chọn danh mục cho sản phẩm: ");
            int cateIdInput = InputMethods.getInteger();
            Catalog catalogP = catalogService.findById(cateIdInput);
            if (catalogP == null) {
                System.err.println("Id không đúng, hãy nhập lại!");
            } else {
                this.catalog = catalogP;
                break;
            }
        }

        // Nhập trạng thái sản phẩm
        System.out.print("Nhập trạng thái sản phẩm (true - Đang mở bán, false - Không bán): ");
        this.status = InputMethods.getBoolean();
    }


    // Method to display Product data
    @Override
    public String toString() {
        return "Product{" +
                "productId: " + productId + '\'' +
                ", Tên Sản phẩm: " + productName + '\'' +
                ", Giá: " + productPrice +
                ", Mô tả: " + description + '\'' +
                ", SL tồn kho: " + stock +
                ", Danh mục Sản phẩm: " + this.catalog.getCatalogName() +
                ", Tình trạng: " + (status ? "Đang Bán" : "Không Bán") +
                '}';
    }
}


