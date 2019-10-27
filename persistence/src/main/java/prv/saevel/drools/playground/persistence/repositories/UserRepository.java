package prv.saevel.drools.playground.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prv.saevel.drools.playground.persistence.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    ;
}
