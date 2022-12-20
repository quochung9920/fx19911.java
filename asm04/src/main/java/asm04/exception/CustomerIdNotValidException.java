package asm04.exception;

/** Lớp này dùng để định nghĩa ngoại lệ khi mã khách hàng không hợp lệ */
public class CustomerIdNotValidException extends Exception {
    public CustomerIdNotValidException(String message) {
        super(message);
    }
}   
