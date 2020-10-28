package com.xuke.macrosite.common.api.chat;

import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by xuke on 2020/10/18
 */
public interface ServerApi {

    Object chat(@RequestBody ChatMsgVO chatMsgVO);

}
