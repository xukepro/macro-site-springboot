package com.xuke.macrosite.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by xuke on 2020/9/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class UpdateArticleParams implements Serializable {
    private static final long serialVersionUID = -2384310901940180325L;

    @NotNull
    @ApiModelProperty(name = "需要修改的文章id", example = "1")
    private Integer id;

    @ApiModelProperty(example = "Mybatis从入门到精通", required = true)
    private String title;

    @ApiModelProperty(example = "这是一篇Mybatis从入门到精通的教程", required = true)
    private String description;

    @ApiModelProperty(value = "文章的分类", example = "1", required = true)
    private Integer cid;

    @ApiModelProperty(example = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1600249269378&di=77182b0c7a1a83e94ee7741fffbe3b03&imgtype=0&src=http%3A%2F%2Fimg2.imgtn.bdimg.com%2Fit%2Fu%3D2196685962%2C796489853%26fm%3D214%26gp%3D0.jpg", required = false)
    private String picture;

    @ApiModelProperty(example = "Mybatis从入门到精通的教程，这是一篇Mybatis从入门到精通的教程这是一篇Mybatis从入门到精通的教程", required = true)
    private String content;

    private List<String> tags;
}
