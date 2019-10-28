package prv.saevel.drools.playground.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import prv.saevel.drools.playground.persistence.model.TransactionModel;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<TransactionModel, Long> {
    @Query(value = "SELECT * FROM transactions AS t WHERE t.target_account_id = :accountId", nativeQuery = true)
    List<TransactionModel> findIncomingTransactionsForAccount(@Param("accountId") long accountId);

    @Query(value = "SELECT * FROM transactions AS t WHERE t.source_account_id = :accountId", nativeQuery = true)
    List<TransactionModel> findOutgoingTransactionsForAccount(@Param("accountId") long accountId);
}
