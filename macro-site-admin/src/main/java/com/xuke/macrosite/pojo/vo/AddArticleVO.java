package com.xuke.macrosite.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * Created by xuke on 2020/9/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("要添加的文章")
public class AddArticleVO implements Serializable {
    private static final long serialVersionUID = 8033532973263291567L;

    @Null
    @ApiModelProperty
    private int id;

    @NotNull
    @ApiModelProperty(value = "添加文章的用户id", example = "1", required = true)
    private int uid;

    @NotNull
    @ApiModelProperty(example = "Mybatis从入门到精通", required = true)
    private String title;

    @NotNull
    @ApiModelProperty(example = "这是一篇Mybatis从入门到精通的教程", required = true)
    private String description;

    @NotNull
    @ApiModelProperty(value = "文章的分类", example = "1", required = true)
    private int cid;

    @ApiModelProperty(example = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1600249269378&di=77182b0c7a1a83e94ee7741fffbe3b03&imgtype=0&src=http%3A%2F%2Fimg2.imgtn.bdimg.com%2Fit%2Fu%3D2196685962%2C796489853%26fm%3D214%26gp%3D0.jpg", required = false)
    private String picture;

    @NotNull
    @ApiModelProperty(example = "Mybatis从入门到精通的教程，这是一篇Mybatis从入门到精通的教程这是一篇Mybatis从入门到精通的教程", required = true)
    private String content;
}
