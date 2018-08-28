package nl.playground.demo.elasticsearch.client.storage;

import nl.playground.demo.elasticsearch.client.rest.model.BankAccount;

import java.util.List;

public interface StorageService {

    List<BankAccount> getAllBankAccounts();

    BankAccount getBankAccount(String id);

    BankAccount save(BankAccount bankAccount);

    void createIndex(String name);
}
