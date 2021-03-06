package nl.playground.demo.elasticsearch.client.elasticsearch;

import nl.playground.demo.elasticsearch.client.rest.model.BankAccount;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticsearchBankRepository extends ElasticsearchRepository<BankAccount, String> {
}
