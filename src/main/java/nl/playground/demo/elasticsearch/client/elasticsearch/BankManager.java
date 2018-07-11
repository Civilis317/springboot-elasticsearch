package nl.playground.demo.elasticsearch.client.elasticsearch;

import nl.playground.demo.elasticsearch.client.rest.model.BankAccount;
import nl.playground.demo.elasticsearch.client.storage.DocumentNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class BankManager {
    private final BankRepository repository;

    public BankManager(BankRepository repository) {
        this.repository = repository;
    }

    public Stream<BankAccount> getAllBankAccounts() {
        return StreamSupport.stream(repository.findAll().spliterator(), true);
    }

    public BankAccount getBankAccount(String id) {
        return repository.findById(id).orElseThrow(() -> new DocumentNotFoundException("Record not found for id: " + id));
    }

    public BankAccount save(BankAccount bankAccount) {
        return repository.save(bankAccount);
    }
}
