package jp.ddd.server.domain.repository;

import jp.ddd.server.domain.model.message.MessageRead;
import jp.ddd.server.infrastructure.MessageReadRepositoryCtm;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by noguchi_kohei 
 */
public interface MessageReadRepository extends JpaRepository<MessageRead, Long>, MessageReadRepositoryCtm {
}
