package asm03.Utils;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

public class Utils {

    public static String getDivider() {
        return "+----------+--------------------+----------+";
    }

    public static String getDateTime() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        // Thời gian hiện tại theo định dạng: 2020-12-31 23:59:59
        return date.toString() + " " + time.toString().substring(0, 8);
    }

    // Định dạng số tiền theo định dạng tiền tệ 1,000,000d
    public static String formatBalance(double balance) {
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);
        return en.format(balance) + "d";
    }

    // Định dạng số tiền theo định dạng tiền tệ 1,000,000.00d
    public static String formatAmount(double amount) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        String str = currencyFormat.format(amount);
        return str.substring(1);
    }

}
