package jp.ddd.server.web.data.json;

import jp.ddd.server.web.data.Error;
import jp.ddd.server.web.data.Json;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by noguchi_kohei 
 */
@AllArgsConstructor
@Data
public class ErrorJson implements Error, Json {
    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

}
