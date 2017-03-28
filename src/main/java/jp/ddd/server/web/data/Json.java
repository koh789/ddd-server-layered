package jp.ddd.server.web.data;

import java.io.Serializable;

/**
 * APIのレスポンスを表現するマーカーインターフェースです。
 * Json変換のためlombokの@valueは利用できません。
 * 当オブジェクトはシャローコピー{@link Object#clone}の
 * 使用を推奨していません。
 * 個別にファクトリメソッドを作成してください。
 * @author noguchi_kohei
 *
 */
public interface Json extends Serializable {
}
