
import java.util.ArrayList;
import java.util.Scanner;

public class asm01 {

    public static class Province {
        int id;
        String name;
        String code;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        // Phương thức thêm tỉnh
        public Province(int id, String name, String code) {
            super();
            this.id = id;
            this.name = name;
            this.code = code;
        }

        // Khởi tạo mảng tỉnh thành phố và thêm vào dữ liệu
        public static ArrayList<Province> initProvince() {
            ArrayList<Province> listProvince = new ArrayList<Province>();
            listProvince.add(new Province(1, "Ha Noi", "001"));
            listProvince.add(new Province(2, "Ha Giang", "002"));
            listProvince.add(new Province(3, "Cao Bang", "004"));
            listProvince.add(new Province(4, "Bac Kan", "006"));
            listProvince.add(new Province(5, "Tuyen Quang", "008"));
            listProvince.add(new Province(6, "Lao Cai", "010"));
            listProvince.add(new Province(7, "Dien Bien", "011"));
            listProvince.add(new Province(8, "Lai Chau", "012"));
            listProvince.add(new Province(9, "Son La", "014"));
            listProvince.add(new Province(10, "Yen Bai", "015"));
            listProvince.add(new Province(11, "Hoa Binh", "017"));
            listProvince.add(new Province(12, "Thai Nguyen", "019"));
            listProvince.add(new Province(13, "Lang Son", "020"));
            listProvince.add(new Province(14, "Quang Ninh", "022"));
            listProvince.add(new Province(15, "Bac Giang", "024"));
            listProvince.add(new Province(16, "Phu Tho", "025"));
            listProvince.add(new Province(17, "Vinh Phuc", "026"));
            listProvince.add(new Province(18, "Bac Ninh", "027"));
            listProvince.add(new Province(19, "Hai Duong", "030"));
            listProvince.add(new Province(20, "Hai Phong", "031"));
            listProvince.add(new Province(21, "Hung Yen", "033"));
            listProvince.add(new Province(22, "Thai Binh", "034"));
            listProvince.add(new Province(23, "Ha Nam", "035"));
            listProvince.add(new Province(24, "Nam Dinh", "036"));
            listProvince.add(new Province(25, "Ninh Binh", "037"));
            listProvince.add(new Province(26, "Thanh Hoa", "038"));
            listProvince.add(new Province(27, "Nghe An", "040"));
            listProvince.add(new Province(28, "Ha Tinh", "042"));
            listProvince.add(new Province(29, "Quang Binh", "044"));
            listProvince.add(new Province(30, "Quang Tri", "045"));
            listProvince.add(new Province(31, "Thua Thien Hue", "046"));
            listProvince.add(new Province(32, "Da Nang", "048"));
            listProvince.add(new Province(33, "Quang Nam", "049"));
            listProvince.add(new Province(34, "Quang Ngai", "051"));
            listProvince.add(new Province(35, "Binh Dinh", "052"));
            listProvince.add(new Province(36, "Phu Yen", "054"));
            listProvince.add(new Province(37, "Khanh Hoa", "056"));
            listProvince.add(new Province(38, "Ninh Thuan", "058"));
            listProvince.add(new Province(39, "Binh Thuan", "060"));
            listProvince.add(new Province(40, "Kon Tum", "062"));
            listProvince.add(new Province(41, "Gia Lai", "064"));
            listProvince.add(new Province(42, "Dak Lak", "066"));
            listProvince.add(new Province(43, "Dak Nong", "067"));
            listProvince.add(new Province(44, "Lam Dong", "068"));
            listProvince.add(new Province(45, "Binh Phuoc", "070"));
            listProvince.add(new Province(46, "Tay Ninh", "072"));
            listProvince.add(new Province(47, "Binh Duong", "074"));
            listProvince.add(new Province(48, "Dong Nai", "075"));
            listProvince.add(new Province(49, "Ba Ria - Vung Tau", "077"));
            listProvince.add(new Province(50, "TP. Ho Chi Minh", "079"));
            listProvince.add(new Province(51, "Long An", "080"));
            listProvince.add(new Province(52, "Tien Giang", "082"));
            listProvince.add(new Province(53, "Ben Tre", "083"));
            listProvince.add(new Province(54, "Tra Vinh", "084"));
            listProvince.add(new Province(55, "Vinh Long", "086"));
            listProvince.add(new Province(56, "Dong Thap", "087"));
            listProvince.add(new Province(57, "An Giang", "089"));
            listProvince.add(new Province(58, "Kien Giang", "091"));
            listProvince.add(new Province(59, "Can Tho", "092"));
            listProvince.add(new Province(60, "Hau Giang", "093"));
            listProvince.add(new Province(61, "Soc Trang", "094"));
            listProvince.add(new Province(62, "Bac Lieu", "095"));
            listProvince.add(new Province(63, "Ca Mau", "096"));
            return listProvince;
        }
    }

    // Kiểm tra định dạng của CCCD
    public static boolean checkCCCD(String cccd) {
        // Độ dài chuỗi phải là 12 kí tự và là số từ 0-9
        if (cccd.length() != 12) {
            return false;
        } else {
            for (int i = 0; i < cccd.length(); i++) {
                if (cccd.charAt(i) < '0' || cccd.charAt(i) > '9') {
                    return false;
                }
            }
        }
        return true;
    }

    // Kiểm tra mã tỉnh
    public static String getProvince(String cccd) {
        // Lấy 3 kí tự đầu tiên của chuỗi cccd
        String provinceCode = cccd.substring(0, 3);
        // Duyệt danh sách tỉnh thành
        for (Province province : Province.initProvince()) {
            // Nếu mã tỉnh thành trùng với mã tỉnh thành trong chuỗi cccd
            if (province.getCode().equals(provinceCode)) {
                // Trả về tên tỉnh thành
                return province.getName();
            }
        }
        return null;
    }

    // Kiểm tra giới tính
    public static String getSex(String cccd) {
        // Lấy 1 kí tự thứ 4 trong chuỗi cccd
        String sexCode = cccd.substring(3, 4);
        // Nếu là số chẵn thì là nam
        if (Integer.parseInt(sexCode) % 2 == 0) {
            return "Nam";
        } else {
            return "Nữ";
        }
    }

    // Kiểm tra năm sinh
    public static String getYear(String cccd) {
        // Lấy 2 kí tự thứ 5 và 6 trong chuỗi cccd
        String yearCode = cccd.substring(4, 6);
        // Lấy 1 kí tự thứ 4 trong chuỗi cccd
        String sexCode = cccd.substring(3, 4);
        if (sexCode.equals("0") || sexCode.equals("1")) {
            return "19" + yearCode;
        } else if (sexCode.equals("2") || sexCode.equals("3")) {
            return "20" + yearCode;
        } else if (sexCode.equals("4") || sexCode.equals("5")) {
            return "21" + yearCode;
        } else if (sexCode.equals("6") || sexCode.equals("7")) {
            return "22" + yearCode;
        } else if (sexCode.equals("8") || sexCode.equals("9")) {
            return "23" + yearCode;
        }
        return null;
    }

    // Kiểm tra số ngẫu nhiên
    public static String getNumber(String cccd) {
        // Lấy 6 kí tự thứ 7 đến 12 trong chuỗi cccd
        return cccd.substring(6, 12);
    }

    // Random chuỗi số và chữ cái từ a-z và A-Z và 0-9 với độ dài là 6 kí tự
    public static String randomString() {
        String randomString = "";
        for (int i = 0; i < 6; i++) {
            int random = (int) (Math.random() * 62);
            if (random < 10) {
                randomString += random;
            } else if (random < 36) {
                randomString += (char) (random + 55);
            } else {
                randomString += (char) (random + 61);
            }
        }
        return randomString;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("+----------+--------------------+----------+");
        System.out.println("| NGAN HANG SO | FX19911@v1.0.0            |");
        System.out.println("+----------+--------------------+----------+");
        System.out.println("| 1. Nhap CCCD                             |");
        System.out.println("| 0. Thoat                                 |");
        System.out.println("+----------+--------------------+----------+");

        // Nhâp lựa chọn của người dùng từ bàn phím
        int choice;
        do {
            System.out.print("Chuc nang: ");
            System.out.print("\033[32m");
            choice = sc.nextInt();
            System.out.print("\033[0m");
            // Nếu người dùng chọn 1
            if (choice == 1) {
                // Random số ngẫu nhiên từ 100-199
                String random = randomString();
                System.out.println("Nhap ma xac thuc: " + random);
                String code;
                do {
                    // Nhập mã xác thực từ bàn phím
                    System.out.print("\033[32m");
                    code = sc.next();
                    System.out.print("\033[0m");
                    // Nếu mã xác thực không đúng thì yêu cầu nhập lại
                    if (code.equals(random)) {
                        String cccd;
                        System.out.print("Vui long nhap so CCCD: ");
                        System.out.print("\033[32m");
                        cccd = sc.next();
                        System.out.print("\033[0m");
                        do {
                            // Kiểm tra chuỗi cccd có hợp lệ không
                            if (checkCCCD(cccd)) {

                                int choice2;
                                do {
                                    System.out.println("\t| 1. Kiem tra noi sinh");
                                    System.out.println("\t| 2. Kiem tra tuoi, gioi tinh");
                                    System.out.println("\t| 3. Kiem tra so ngau nhien");
                                    System.out.println("\t| 0. Thoat");
                                    System.out.print("Chuc nang: ");
                                    System.out.print("\033[32m");
                                    choice2 = sc.nextInt();
                                    System.out.print("\033[0m");
                                    switch (choice2) {
                                        case 1:
                                            System.out.println("Noi sinh: " + getProvince(cccd));
                                            break;
                                        case 2:
                                            System.out.println("Gioi tinh: " + getSex(cccd) + " | " + getYear(cccd));
                                            break;
                                        case 3:
                                            System.out.println("So ngau nhien: " + getNumber(cccd));
                                            break;
                                        case 0:
                                            System.out.println("Cam on da su dung dich vu!");
                                            System.out.println("+----------+--------------------+----------+");
                                            System.exit(0);
                                            break;
                                        default:
                                            System.out.println("Lua chon khong hop le! Moi nhap lai!");
                                            break;
                                    }
                                } while (choice2 != 0);

                            } else {
                                System.out.println("So CCCD khong hop le.");
                                System.out.println("Vui long nhap lai hoac 'No' de thoat: ");
                                System.out.print("\033[32m");
                                cccd = sc.next();
                                System.out.print("\033[0m");
                                if (cccd.equalsIgnoreCase("No")) {
                                    System.out.println("Cam on da su dung dich vu!");
                                    System.out.println("+----------+--------------------+----------+");
                                    System.exit(0);
                                }
                            }
                        } while (checkCCCD(cccd));

                    } else {
                        System.out.println("Ma xac thuc khong dung. Vui long thu lai.");
                    }
                } while (code != random);
            } else if (choice != 0) {
                System.out.println("Lua chon khong hop le! Moi nhap lai!");
            } else {
                System.out.println("Cam on da su dung dich vu!");
                System.out.println("+----------+--------------------+----------+");
                System.exit(0);
            }
        } while (choice != 1 && choice != 0);

    }
}
