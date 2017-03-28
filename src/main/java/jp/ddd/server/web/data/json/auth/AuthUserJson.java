package jp.ddd.server.web.data.json.auth;

import jp.ddd.server.domain.model.user.SessionUser;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by noguchi_kohei 
 */
@Builder
@Data
public class AuthUserJson implements Serializable {
    private static final long serialVersionUID = 910139523300534581L;

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

    public static AuthUserJson create(SessionUser user) {

        return AuthUserJson.builder() //
          .sessionId(user.getSessionId()).ipAddress(user.getIpAddress()).userId(user.getUserId())
          .loginId(user.getLoginId()).name(user.getName()).email(user.getEmail()).tel(user.getTel()).build();
    }
}
