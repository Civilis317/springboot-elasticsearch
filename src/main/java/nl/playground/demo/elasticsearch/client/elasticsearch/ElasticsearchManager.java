package nl.playground.demo.elasticsearch.client.elasticsearch;

import nl.playground.demo.elasticsearch.client.rest.model.BankAccount;
import nl.playground.demo.elasticsearch.client.rest.model.BaseTrademark;
import nl.playground.demo.elasticsearch.client.storage.DocumentNotFoundException;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class ElasticsearchManager {

    private final ElasticsearchBankRepository bankRepository;
    private final ElasticsearchTrademarkRepository trademarkRepository;

    public ElasticsearchManager(ElasticsearchBankRepository bankRepository, ElasticsearchTrademarkRepository trademarkRepository) {
        this.bankRepository = bankRepository;
        this.trademarkRepository = trademarkRepository;
    }

    public Stream<BankAccount> getAllBankAccounts() {
        return StreamSupport.stream(bankRepository.findAll().spliterator(), true);
    }

    public BankAccount getBankAccount(String id) {
        return bankRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException("Record not found for id: " + id));
    }

    public BankAccount save(BankAccount bankAccount) {
        return bankRepository.save(bankAccount);
    }

    public Iterable<BankAccount> saveAll(Stream<BankAccount> bankAccountStream) {
        return bankRepository.saveAll(bankAccountStream.collect(Collectors.toList()));
    }

    public Iterable<BaseTrademark> saveTrademarkStream(Stream<BaseTrademark> trademarkStream) {
        return trademarkRepository.saveAll(trademarkStream.collect(Collectors.toList()));
    }

    public Iterable<BankAccount> findByGender(String gender) {
        return bankRepository.search(QueryBuilders.matchQuery("gender", gender));
    }

    public Iterable<BankAccount> getRichYoungFemales() {
        QueryBuilder query = QueryBuilders
                .boolQuery()
                .must(
                        QueryBuilders.rangeQuery("age").lte(35)
                )
                .must(
                        QueryBuilders.matchQuery("gender", "F")
                )
                .must(
                        QueryBuilders.rangeQuery("balance").gte(34000)
                );
        return bankRepository.search(query);
    }

    public Iterable<BankAccount> addressLikeQuery(String addressPart) {
        QueryBuilder query = QueryBuilders.wildcardQuery("address", addressPart);
        return bankRepository.search(query);
    }


}
