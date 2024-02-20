package ra.model;

import ra.validation.InputMethods;

public class Catalog {
    private int catalogId;
    private String catalogName;
    private String descriptions;

    // Constructors
    public Catalog() {
    }

    public Catalog(int catalogId, String catalogName, String descriptions) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.descriptions = descriptions;
    }

    // Getter and Setter methods
    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public void inputData(Boolean isAdd) {
        if (isAdd) {
            System.out.print("Nhập Catalog ID: ");
            this.catalogId = InputMethods.getInteger();
        }
        System.out.print("Nhập Catalog Name: ");
        this.catalogName = InputMethods.getString();
        System.out.print("Nhập Descriptions: ");
        this.descriptions = InputMethods.getString();
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "catalogId=" + catalogId +
                ", catalogName='" + catalogName + '\'' +
                ", descriptions='" + descriptions + '\'' +
                '}';
    }
}
