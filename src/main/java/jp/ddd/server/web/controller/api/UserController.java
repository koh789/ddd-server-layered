package jp.ddd.server.web.controller.api;

import jp.ddd.server.domain.model.user.User;
import jp.ddd.server.domain.repository.UserRepository;
import jp.ddd.server.other.data.user.UserParam;
import jp.ddd.server.other.exception.IllegalDataException;
import jp.ddd.server.other.utils.Msg;
import jp.ddd.server.web.controller.base.BaseApi;
import jp.ddd.server.web.data.form.user.UserForm;
import jp.ddd.server.web.data.json.ResultJson;
import jp.ddd.server.web.data.json.user.SavedUserJson;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by noguchi_kohei 
 */
@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends BaseApi {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResultJson<SavedUserJson> register(@RequestBody @Validated UserForm userForm, HttpServletRequest req) {
        if (!userForm.getPassword().equals(userForm.getConfirmedPassword())) {
            new IllegalDataException(Msg.MISMATCH_PASS, true);
        }
        if (User.isExist(userRepository, userForm.getLoginId())) {
            new IllegalDataException(Msg.EXISTED_LOGIN_ID, true);
        }
        val result = SavedUserJson.create(User.register(userRepository, UserParam.create(userForm)).getId());
        return ResultJson.create(result);
    }
}
