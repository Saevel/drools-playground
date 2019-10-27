package prv.saevel.drools.playground.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import prv.saevel.drools.playground.persistence.model.Transaction;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "SELECT * FROM transactions AS t WHERE t.target_account_id = :accountId", nativeQuery = true)
    List<Transaction> findIncomingTransactionsForAccount(@Param("accountId") long accountId);

    @Query(value = "SELECT * FROM transactions AS t WHERE t.source_account_id = :accountId", nativeQuery = true)
    List<Transaction> findOutgoingTransactionsForAccount(@Param("accountId") long accountId);
}
