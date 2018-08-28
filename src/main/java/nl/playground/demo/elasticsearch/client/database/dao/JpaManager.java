package nl.playground.demo.elasticsearch.client.database.dao;

import nl.playground.demo.elasticsearch.client.database.entity.BankAccountEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class JpaManager {

    private final JpaRepository repository;

    public JpaManager(JpaRepository repository) {
        this.repository = repository;
    }

    public Stream<BankAccountEntity> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), true);
    }

    public Stream<BankAccountEntity> findByStateAndBalance(String state, Double balance) {
        return StreamSupport.stream(repository.findByStateAndBalance(state, balance).spliterator(), true);
    }

    public BankAccountEntity save(BankAccountEntity entity) {
        return repository.save(entity);
    }



}
