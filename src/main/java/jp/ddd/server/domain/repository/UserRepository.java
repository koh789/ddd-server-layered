package jp.ddd.server.domain.repository;

import jp.ddd.server.domain.model.user.User;
import jp.ddd.server.infrastructure.UserRepositoryCtm;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by noguchi_kohei 
 */
public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCtm {
}
