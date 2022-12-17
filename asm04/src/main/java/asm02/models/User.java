package asm02.models;

import java.io.Serializable;

/**
 * Class User có Access Modifier là public, nên có thể truy cập từ bất cứ đâu
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    // Khai báo các thuộc tính của User
    private String name;
    private String customerId;

    /** Phương thức khởi tạo */
    public User() {
    }

    /** Getter và Setter của các thuộc tính */
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        // Danh sách mã các tỉnh thành Việt Nam
        String[] PROVINCE_CODE = {"001", "002", "004", "006", "008", "010", "011", "012", "014", "015", "017", "019", "020", "022", "024", "025", "026", "027", "030", "031", "033", "034", "035", "036", "037", "038", "040", "042", "044", "045", "046", "048", "049", "051", "052", "054", "056", "058", "060", "062", "064", "066", "067", "068", "070", "072", "074", "075", "077", "079", "080", "082", "083", "084", "086", "087", "089", "091", "092", "093", "094", "095", "096"};

        boolean isProvinceCodeExisted = false;
        // Tìm mã tỉnh thành trong danh sách mã tỉnh thành
        for (String provinceCode : PROVINCE_CODE) {
            if (provinceCode.equals(customerId.substring(0, 3))) {
                isProvinceCodeExisted = true;
            }
        }
        

        // Chỉ cập nhật khi dữ liệu CCCD mới hợp lệ (độ dài = 12) và là số nguyên từ 0-9
        if (customerId.length() == 12 && customerId.matches("[0-9]+") && isProvinceCodeExisted) {
            this.customerId = customerId;
        } else {
            throw new IllegalArgumentException("So CCCD khong hop le!");

        }
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
