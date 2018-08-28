package nl.playground.demo.elasticsearch.client.database;

import nl.playground.demo.elasticsearch.client.database.conversion.BankAccountConverter;
import nl.playground.demo.elasticsearch.client.database.dao.JpaManager;
import nl.playground.demo.elasticsearch.client.database.entity.BankAccountEntity;
import nl.playground.demo.elasticsearch.client.rest.model.BankAccount;
import nl.playground.demo.elasticsearch.client.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DatabaseStorageService implements StorageService {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseStorageService.class);

    private final JpaManager manager;

    public DatabaseStorageService(JpaManager manager) {
        this.manager = manager;
    }

    public Stream<BankAccount> streamSelected(String state, Double balance) {
        return manager.findByStateAndBalance(state, balance).map(BankAccountConverter::convertEntityToBankAccount);
    }

    public Stream<BankAccount> streamAllBankAccounts() {
        return manager.findAll().map(BankAccountConverter::convertEntityToBankAccount);
    }

    @Override
    public List<BankAccount> getAllBankAccounts() {
        return streamAllBankAccounts().collect(Collectors.toList());

// This will work without error, but will lose elements...
//        manager.findAll().forEach(e -> {
//            bankAccountList.add(BankAccountConverter.convertEntityToBankAccount(e));
//        });

    }

    @Override
    public BankAccount getBankAccount(String id) {
        return null;
    }

    @Override
    public BankAccount save(BankAccount bankAccount) {
        BankAccountEntity entity = BankAccountConverter.convertBankAccountToEntity(bankAccount);
        return BankAccountConverter.convertEntityToBankAccount(manager.save(entity));
    }

    @Override
    public void createIndex(String name) {

    }
}
