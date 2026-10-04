package org.sid.bank_account_service.service;

import org.sid.bank_account_service.DTO.BankAccountRequestDTO;
import org.sid.bank_account_service.DTO.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;

public interface AccountService {

    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);

    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO);
}
