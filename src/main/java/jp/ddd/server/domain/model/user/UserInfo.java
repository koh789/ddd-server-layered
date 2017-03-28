package jp.ddd.server.domain.model.user;

import jp.ddd.server.domain.model.base.ValueObject;
import lombok.*;

import javax.persistence.Embeddable;

/**
 * Created by noguchi_kohei 
 */
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class UserInfo implements ValueObject<UserInfo> {
    private static final long serialVersionUID = 1L;

    private String name;

    private String email;

    private String tel;

}
