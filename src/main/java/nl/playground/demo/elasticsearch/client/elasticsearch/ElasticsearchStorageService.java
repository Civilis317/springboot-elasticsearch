package nl.playground.demo.elasticsearch.client.elasticsearch;

import nl.playground.demo.elasticsearch.client.rest.model.BankAccount;
import nl.playground.demo.elasticsearch.client.rest.model.BaseTrademark;
import nl.playground.demo.elasticsearch.client.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ElasticsearchStorageService implements StorageService {
    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchStorageService.class);

    private final ElasticsearchManager manager;

    private final ElasticsearchTemplate esTemplate;

    public ElasticsearchStorageService(ElasticsearchManager manager, ElasticsearchTemplate esTemplate) {
        this.manager = manager;
        this.esTemplate = esTemplate;
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

    @Override
    public void createIndex(String name) {
        esTemplate.createIndex(name);
        logger.debug("Elasticsearch index created: {}", name);
    }

    public void deleteIndex(String name) {
        esTemplate.deleteIndex(name);
        logger.debug("Elasticsearch index deleted: {}", name);
    }

    public Iterable<BankAccount> saveAll(Stream<BankAccount> bankAccountStream) {
        return manager.saveAll(bankAccountStream);
    }

    public Iterable<BankAccount> getAllMales() {
        return manager.findByGender("M");
    }

    public Iterable<BankAccount> getAllFemales() {
        return manager.findByGender("F");
    }

    public Iterable<BankAccount> getRichYoungFemales() {
        return manager.getRichYoungFemales();
    }

    public Iterable<BankAccount> findByAddress(String address) {
        return manager.addressLikeQuery(address);
    }

    public Iterable<BaseTrademark> saveTrademarks(List<BaseTrademark> trademarkList) {
        return manager.saveTrademarkStream(trademarkList.stream());
    }
}
