package org.sid.bank_account_service.web;

import org.sid.bank_account_service.DTO.BankAccountRequestDTO;
import org.sid.bank_account_service.DTO.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.sid.bank_account_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphQlController {
    @Autowired
     private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountService accountService;
    @QueryMapping
    public List<BankAccount> accountsList(){
        return bankAccountRepository.findAll();
    }

    @QueryMapping
    public BankAccount bankAccountById (@Argument  String id){
        return bankAccountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(String.format("Account %s not found",id)));
    }
    @MutationMapping
    public BankAccountResponseDTO addAccount (@Argument BankAccountRequestDTO bankAccount){
        return accountService.addAccount(bankAccount);
    }

    @MutationMapping
    public BankAccountResponseDTO updateAccount (@Argument String id , @Argument BankAccountRequestDTO bankAccount){
        return accountService.updateAccount(id, bankAccount);
    }

    @MutationMapping
    public Boolean deleteAccount (@Argument String id ){
        bankAccountRepository.deleteById(id);
        return true;
    }

}

//record BankAccountDTO(Double Balance, String type,String currency )
