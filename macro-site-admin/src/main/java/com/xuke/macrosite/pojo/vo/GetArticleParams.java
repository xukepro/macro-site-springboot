package com.xuke.macrosite.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Created by xuke on 2020/9/20
 */
@ApiModel
@Data
public class GetArticleParams implements Serializable {
    private static final long serialVersionUID = -6367250730497772344L;

    @ApiModelProperty(example = "1", required = false)
    private Integer uid;

    @ApiModelProperty(example = "m", required = false)
    private String key;

    @ApiModelProperty(example = "1", required = false)
    private Integer cid;

    @ApiModelProperty(example = "1", required = false)
    private List<String> tags;

    @ApiModelProperty(example = "1", required = false)
    private Integer pageSize;

    @ApiModelProperty(example = "1", required = false)
    private Integer pageNum;
}
