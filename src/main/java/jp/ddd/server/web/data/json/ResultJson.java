package jp.ddd.server.web.data.json;

import jp.ddd.server.web.data.Json;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class ResultJson<T> implements Json {
    private T result;

    public static <T> ResultJson create(T result) {
        return ResultJson.builder().result(result).build();
    }

}