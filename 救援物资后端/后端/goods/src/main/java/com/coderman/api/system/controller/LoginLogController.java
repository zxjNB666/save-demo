package com.coderman.api.system.controller;

import com.coderman.api.common.annotation.ControllerEndpoint;
import com.coderman.api.common.bean.ResponseBean;
import com.coderman.api.system.service.LoginLogService;
import com.coderman.api.system.vo.LoginLogVO;
import com.coderman.api.system.vo.PageVO;
import com.coderman.api.system.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登入日志

 **/
@Api(tags = "登入日志接口")
@RestController
@RequestMapping("/loginLog")
public class LoginLogController {


    @Autowired
    private LoginLogService loginLogService;

    /**
     * 日志列表
     *
     * @return
     */
    @ApiOperation(value = "日志列表", notes = "登入日志列表，模糊查询")
    @GetMapping("/findLoginLogList")
    public ResponseBean findLoginLogList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                         @RequestParam(value = "pageSize") Integer pageSize,
                                         LoginLogVO loginLogVO) {
        PageVO<LoginLogVO> loginLogList = loginLogService.findLoginLogList(pageNum, pageSize, loginLogVO);
        return ResponseBean.success(loginLogList);
    }

    /**
     * 删除日志
     *
     * @param id
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "删除登入日志失败", operation = "删除登入日志")
    @ApiOperation(value = "删除日志")
    @RequiresPermissions({"loginLog:delete"})
    @DeleteMapping("/delete/{id}")
    public ResponseBean delete(@PathVariable Long id) {
        loginLogService.delete(id);
        return ResponseBean.success();
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "批量删除登入日志失败", operation = "批量删除登入日志")
    @ApiOperation(value = "批量删除")
    @RequiresPermissions({"loginLog:batchDelete"})
    @DeleteMapping("/batchDelete/{ids}")
    public ResponseBean batchDelete(@PathVariable String ids) {
        String[] idList = ids.split(",");
        List<Long> list = new ArrayList<>();
        if (idList.length > 0) {
            for (String s : idList) {
                list.add(Long.parseLong(s));
            }
        }
        loginLogService.batchDelete(list);
        return ResponseBean.success();
    }

    /**
     * 登入报表
     * @return
     */
    @PostMapping("/loginReport")
    @ApiOperation(value = "登入报表",notes = "用户登入报表")
    public ResponseBean loginReport(@RequestBody UserVO userVO){
        List<Map<String,Object>> mapList= loginLogService.loginReport(userVO);
        Map<String,Object> map=new HashMap<>();
        userVO.setUsername(null);
        List<Map<String,Object>> meList= loginLogService.loginReport(userVO);
        map.put("me",mapList);
        map.put("all",meList);
        return ResponseBean.success(map);
    }


}
