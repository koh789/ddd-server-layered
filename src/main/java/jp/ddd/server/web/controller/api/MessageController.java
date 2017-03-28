package jp.ddd.server.web.controller.api;

import jp.ddd.server.application.MessageService;
import jp.ddd.server.domain.model.user.SessionUser;
import jp.ddd.server.domain.repository.SessionUserRepository;
import jp.ddd.server.other.data.common.Page;
import jp.ddd.server.other.data.message.RegisterMessageParam;
import jp.ddd.server.other.exception.AccessPermissonException;
import jp.ddd.server.other.exception.AuthException;
import jp.ddd.server.other.utils.Cookies;
import jp.ddd.server.other.utils.Msg;
import jp.ddd.server.web.controller.base.BaseApi;
import jp.ddd.server.web.data.form.message.MessageForm;
import jp.ddd.server.web.data.json.ResultJson;
import jp.ddd.server.web.data.json.message.MessagesJson;
import jp.ddd.server.web.data.json.message.SavedMessageJson;
import lombok.val;
import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.management.ImmutableDescriptor;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by noguchi_kohei 
 */
@RestController
@RequestMapping(value = "/api/v1/rooms/{roomId}/messages", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController extends BaseApi {
    @Autowired
    private MessageService messageService;
    @Autowired
    private SessionUserRepository sessionUserRepository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResultJson<SavedMessageJson> register(HttpServletRequest req, @PathVariable("roomId") Integer roomId,
      @RequestBody @Validated MessageForm form) {
        val user = SessionUser.getOpt(sessionUserRepository, Cookies.getKey(req))
          .orElseThrow(() -> new AuthException());
        if (!user.getUserId().equals(form.getUserId())) {
            throw new AccessPermissonException(Msg.FORBIDDEN_MSG_POST, true);
        }
        val message = messageService.register(RegisterMessageParam.create(roomId, form));
        return ResultJson.create(SavedMessageJson.create(message));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResultJson<ImmutableList<MessagesJson>> get(HttpServletRequest req, @PathVariable("roomId") Integer roomId,
      @RequestParam(value = "p", defaultValue = "1") int pageNum) {
        val user = SessionUser.getOpt(sessionUserRepository, Cookies.getKey(req))
          .orElseThrow(() -> new AuthException());

        val dtos = messageService.find(roomId, user.getUserId(), Page.create(pageNum, 50));

        return ResultJson.create(dtos.collect(dto -> MessagesJson.create(dto)));
    }
}
