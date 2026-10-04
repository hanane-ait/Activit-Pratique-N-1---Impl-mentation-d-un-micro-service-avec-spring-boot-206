package org.sid.bank_account_service;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.bank_account_service.entities.BankAccount;

import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor @Data @Builder
public class Customer {
    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "customer")
    private List<BankAccount> bankAccounts;
}
