package com.xuke.macrosite.common.api.route;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by xuke on 2020/10/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteInfo {

    private String ip;

    private Integer wsPort;

    private Integer httpPort;

}