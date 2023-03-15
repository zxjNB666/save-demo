package com.coderman.api.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
public class DepartmentVO {

    private Long id;

    @NotBlank(message = "院系名称不能为空")
    private String name;

    @NotBlank(message = "办公电话不能为空")
    private String phone;

    @NotBlank(message = "办公地址不能为空")
    private String address;


    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date modifiedTime;


    /** 部门内人数**/
    private int total;

}
