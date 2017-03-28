package jp.ddd.server.domain.repository;

import jp.ddd.server.domain.model.message.Message;
import jp.ddd.server.infrastructure.MessageRepositoryCtm;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by noguchi_kohei 
 */
public interface MessageRepository extends JpaRepository<Message, Long>, MessageRepositoryCtm {
}
