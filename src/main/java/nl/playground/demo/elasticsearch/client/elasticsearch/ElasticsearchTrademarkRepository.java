package nl.playground.demo.elasticsearch.client.elasticsearch;

import nl.playground.demo.elasticsearch.client.rest.model.BaseTrademark;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticsearchTrademarkRepository extends ElasticsearchRepository<BaseTrademark, String> {
}
