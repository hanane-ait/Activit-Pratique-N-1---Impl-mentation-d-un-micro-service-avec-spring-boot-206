package org.sid.bank_account_service.web;

import org.sid.bank_account_service.DTO.BankAccountRequestDTO;
import org.sid.bank_account_service.DTO.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.mappers.AccountMapper;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.sid.bank_account_service.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private AccountService accountService;
    private AccountMapper accountMapper;
    private BankAccountRepository bankAccountRepository;
    public AccountRestController(BankAccountRepository bankAccountRepository){
        this.bankAccountRepository= bankAccountRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }
    //consulter liste de  comptes
   @GetMapping("/BankAccounts")
    public List<BankAccount> bankAccounts(){
        return bankAccountRepository.findAll();
    }
    //consulter compte
    @GetMapping("/BankAccounts/{id}")
    public BankAccount bankAccounts(@PathVariable String id){
        return bankAccountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(String.format ("Account is not found", id)));
    }
    @PostMapping("/BankAccounts")
    public BankAccountResponseDTO save(BankAccountRequestDTO requestDTO){
        return accountService.addAccount(requestDTO);
    }

    @PutMapping("/BankAccounts/{id}")
    public BankAccount update(@PathVariable String id, BankAccount bankAccount){
        BankAccount account = bankAccountRepository.findById(id).orElseThrow();
        if(bankAccount.getBalance()!=null) account.setBalance(bankAccount.getBalance());
        if(bankAccount.getCreatedAt()!=null) account.setCreatedAt(new Date());
        if(bankAccount.getType()!=null) account.setType(bankAccount.getType());
        if(bankAccount.getCurrency()!=null) account.setCurrency(bankAccount.getCurrency());
        return bankAccountRepository.save(account);
    }

    @DeleteMapping("/BankAccounts/{id}")
    public void deleteAccount(@PathVariable String id) {
        bankAccountRepository.deleteById(id);

    }
}
