package models;

/**
 * Class User có Access Modifier là public, nên có thể truy cập từ bất cứ đâu
 */
public class User {
    // Khai báo các thuộc tính của User
    private String name;
    private String customerId;

    /** Getter và Setter của các thuộc tính */
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        // Chỉ cập nhật khi dữ liệu CCCD mới hợp lệ (độ dài = 12) và là số nguyên từ 0-9
        if (customerId.length() == 12 && customerId.matches("[0-9]+")) {
            this.customerId = customerId;
        } else {
            throw new IllegalArgumentException("Du lieu CCCD khong hop le!");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
