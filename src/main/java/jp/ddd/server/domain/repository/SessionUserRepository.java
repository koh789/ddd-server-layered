package jp.ddd.server.domain.repository;

import jp.ddd.server.domain.model.user.SessionUser;

import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
public interface SessionUserRepository {

    SessionUser save(SessionUser sessionUser);

    Optional<SessionUser> getOpt(String sid);

    void del(String sid);
}
