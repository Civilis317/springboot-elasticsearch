package nl.playground.demo.elasticsearch.client.scanner;

import nl.playground.demo.elasticsearch.client.database.DatabaseStorageService;
import nl.playground.demo.elasticsearch.client.elasticsearch.ElasticsearchStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DatabaseMutationScanner {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseMutationScanner.class);

    private static final String STATE_OK = "OK";
    private static final Double BALANCE_10K = Double.valueOf("10000");

    private  final DatabaseStorageService databaseStorageService;
    private final ElasticsearchStorageService elasticsearchStorageService;

    public DatabaseMutationScanner(DatabaseStorageService databaseStorageService, ElasticsearchStorageService elasticsearchStorageService) {
        this.databaseStorageService = databaseStorageService;
        this.elasticsearchStorageService = elasticsearchStorageService;
    }

    //@Scheduled(fixedRateString = "10000", initialDelayString = "1000")
    public void scan() {
        elasticsearchStorageService.deleteIndex("bank");
        elasticsearchStorageService.createIndex("bank");
        elasticsearchStorageService.saveAll(databaseStorageService.streamSelected(STATE_OK, BALANCE_10K) );
    }

}
