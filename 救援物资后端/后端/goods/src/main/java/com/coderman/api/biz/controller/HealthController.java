package com.coderman.api.biz.controller;

import com.coderman.api.common.pojo.biz.Health;
import com.coderman.api.biz.service.HealthService;
import com.coderman.api.biz.vo.HealthVO;
import com.coderman.api.common.annotation.ControllerEndpoint;
import com.coderman.api.common.bean.ActiveUser;
import com.coderman.api.common.bean.ResponseBean;
import com.coderman.api.system.vo.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Api(tags = "健康上报接口")
@RestController
@RequestMapping("/health")
public class HealthController {

    @Autowired
    private HealthService healthService;

    /**
     * 健康上报
     * @param healthVO
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "健康上报失败", operation = "健康上报")
    @ApiOperation(value = "健康上报",notes = "用户健康上报")
    @RequiresPermissions({"health:report"})
    @PostMapping("/report")
    public ResponseBean report(@Validated @RequestBody HealthVO healthVO){
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        healthVO.setUserId(activeUser.getUser().getId());
        healthService.report(healthVO);
        return ResponseBean.success();
    }

    /**
     * 签到记录
     * @return
     */
    @ApiOperation(value = "健康记录",notes = "用户健康上报历史记录")
    @GetMapping("/history")
    public ResponseBean history(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize") Integer pageSize){
        ActiveUser activeUser= (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        Long id = activeUser.getUser().getId();
        PageVO<Health> Health=healthService.history(id,pageNum,pageSize);
        return ResponseBean.success(Health);
    }

    /**
     * 今日是否已报备
     * @return
     */
    @ApiOperation(value = "是否报备",notes = "今日是否已报备")
    @GetMapping("/isReport")
    public ResponseBean isReport(){
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        Health report = healthService.isReport(activeUser.getUser().getId());
        return ResponseBean.success(report);
    }

}
