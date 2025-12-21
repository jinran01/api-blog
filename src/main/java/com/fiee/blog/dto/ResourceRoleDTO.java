package com.fiee.blog.dto;

import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * @Author: Fiee
 * @ClassName: ResourceRoleDTO
 * @Date: 2025/12/21
 * @Version: v1.0.0
 **/
@Data
@Getter
public class ResourceRoleDTO {
    /**
     * 资源id
     */
    private Integer id;

    /**
     * 路径
     */
    private String url;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 是否匿名访问 1是 0否
     */
    private Integer isAnonymous;

    /**
     * 角色名
     */
    private List<String> roleList;
}
