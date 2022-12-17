package asm04.service;

import asm04.model.Transaction.TransactionType;

public interface ReportService {
    void log(double amount);
    void log(double amount, TransactionType type, String receiveAccount);
}
