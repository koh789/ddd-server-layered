package jp.ddd.server.domain.model.user;

import jp.ddd.server.domain.repository.SessionUserRepository;
import jp.ddd.server.domain.repository.UserRepository;
import jp.ddd.server.other.exception.AuthException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.val;

import java.io.Serializable;
import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
@Builder
@AllArgsConstructor
@Value
public class SessionUser implements Serializable {
    private static final long serialVersionUID = -324299695194671919L;

    /**
     * 利用者を特定するID
     */
    private final String sessionId;
    /**
     * 利用者のIPアドレス
     */
    private final String ipAddress;

    private final Integer userId;

    private final String loginId;

    private final String name;

    private final String email;

    private final String tel;

    public static SessionUser create(String sid, String ipAddress, User user) {
        val userInfo = user.getUserInfo();
        return SessionUser.builder().sessionId(sid).ipAddress(ipAddress).userId(user.getId()).loginId(user.getLoginId())
          .name(userInfo.getName()).email(userInfo.getEmail()).tel(userInfo.getTel()).build();
    }

    public static SessionUser login(SessionUserRepository sRep, UserRepository uRep, //
      String sid, String ipAddress, String loginId, String pass) {

        return User.getOpt(uRep, loginId, pass)//
          .map(u -> sRep.save(create(sid, ipAddress, u)))
          .orElseThrow(() -> new AuthException("invalid loginId and password!" + loginId));
    }

    public static Optional<SessionUser> getOpt(SessionUserRepository rep, String sid) {
        return rep.getOpt(sid);
    }

    public static void logout(SessionUserRepository rep, String sid) {
        rep.del(sid);
    }

    public static boolean isLogin(SessionUserRepository rep, String sid) {
        return rep.getOpt(sid).isPresent();
    }

    public static boolean isNotLogin(SessionUserRepository rep, String sid) {
        return !isLogin(rep, sid);
    }
}
