package jp.ddd.server.web.data.json.user;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by noguchi_kohei 
 */
@Builder
@Data
public class SavedUserJson implements Serializable {
    private static final long serialVersionUID = 910139523300534581L;

    private Integer userId;

    public static SavedUserJson create(Integer userId) {
        return SavedUserJson.builder().userId(userId).build();
    }
}
