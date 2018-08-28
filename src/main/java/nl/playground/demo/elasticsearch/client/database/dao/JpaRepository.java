package nl.playground.demo.elasticsearch.client.database.dao;

import nl.playground.demo.elasticsearch.client.database.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface JpaRepository extends CrudRepository<BankAccountEntity, Long> {

    @Query("select t from BankAccountEntity t where t.state = ?1 and t.balance >= ?2")
    Iterable<BankAccountEntity> findByStateAndBalance(String state, Double balance);
}
