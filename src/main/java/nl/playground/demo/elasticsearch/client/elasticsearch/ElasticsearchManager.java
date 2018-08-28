package nl.playground.demo.elasticsearch.client.elasticsearch;

import nl.playground.demo.elasticsearch.client.rest.model.BankAccount;
import nl.playground.demo.elasticsearch.client.storage.DocumentNotFoundException;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class ElasticsearchManager {

    private final ElasticsearchBankRepository repository;

    public ElasticsearchManager(ElasticsearchBankRepository repository) {
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

    public Iterable<BankAccount> saveAll(Stream<BankAccount> bankAccountStream) {
        return repository.saveAll(bankAccountStream.collect(Collectors.toList()));
    }

    public Iterable<BankAccount> findByGender(String gender) {
        return repository.search(QueryBuilders.matchQuery("gender", gender));
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
        return repository.search(query);
    }

    public Iterable<BankAccount> addressLikeQuery(String addressPart) {
        QueryBuilder query = QueryBuilders.wildcardQuery("address", addressPart);
        return repository.search(query);
    }


}
