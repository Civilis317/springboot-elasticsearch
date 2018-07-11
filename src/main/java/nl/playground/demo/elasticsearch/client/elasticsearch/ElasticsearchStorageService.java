package nl.playground.demo.elasticsearch.client.elasticsearch;

import nl.playground.demo.elasticsearch.client.rest.model.BankAccount;
import nl.playground.demo.elasticsearch.client.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElasticsearchStorageService implements StorageService {
    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchStorageService.class);

    private final BankManager manager;

    public ElasticsearchStorageService(BankManager manager) {
        this.manager = manager;
    }

    @Override
    public List<BankAccount> getAllBankAccounts() {
        return manager.getAllBankAccounts().collect(Collectors.toList());
    }

    @Override
    public BankAccount getBankAccount(String id) {
        return manager.getBankAccount(id);
    }

    @Override
    public BankAccount save(BankAccount bankAccount) {
        return manager.save(bankAccount);
    }
}
