package com.xuke.macrosite.route.service;

import com.xuke.macrosite.common.api.route.LoginVO;
import com.xuke.macrosite.common.api.route.OffLineVO;

/**
 * Created by xuke on 2020/10/18
 */
public interface AccountService {

    void login(LoginVO loginVO);

    Boolean offLine(OffLineVO offLineVO);
}
