package com.coderman.api.biz.controller;

import com.coderman.api.biz.service.SupplierService;
import com.coderman.api.biz.vo.SupplierVO;
import com.coderman.api.common.annotation.ControllerEndpoint;
import com.coderman.api.common.bean.ResponseBean;
import com.coderman.api.system.vo.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "物资来源接口")
@RestController
@RequestMapping("/supplier")
public class SupplierController {


    @Autowired
    private SupplierService supplierService;

    /**
     * 来源列表
     *
     * @return
     */
    @ApiOperation(value = "来源列表", notes = "来源列表,根据来源名模糊查询")
    @GetMapping("/findSupplierList")
    public ResponseBean findSupplierList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                         @RequestParam(value = "pageSize") Integer pageSize,
                                         SupplierVO supplierVO) {
        PageVO<SupplierVO> supplierVOPageVO = supplierService.findSupplierList(pageNum, pageSize, supplierVO);
        return ResponseBean.success(supplierVOPageVO);
    }


    /**
     * 添加来源
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "物资来源添加失败", operation = "物资来源添加")
    @RequiresPermissions({"supplier:add"})
    @ApiOperation(value = "添加来源")
    @PostMapping("/add")
    public ResponseBean add(@RequestBody @Validated SupplierVO supplierVO) {
        supplierService.add(supplierVO);
        return ResponseBean.success("添加来源成功");
    }

    /**
     * 编辑来源
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "编辑来源", notes = "编辑来源信息")
    @RequiresPermissions({"supplier:edit"})
    @GetMapping("/edit/{id}")
    public ResponseBean edit(@PathVariable Long id) {
        SupplierVO supplierVO = supplierService.edit(id);
        return ResponseBean.success(supplierVO);
    }

    /**
     * 更新来源
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "物资来源更新失败", operation = "物资来源更新")
    @ApiOperation(value = "更新来源", notes = "更新来源信息")
    @RequiresPermissions({"supplier:update"})
    @PutMapping("/update/{id}")
    public ResponseBean update(@PathVariable Long id, @RequestBody @Validated SupplierVO supplierVO) {
        supplierService.update(id, supplierVO);
        return ResponseBean.success("更新来源成功");
    }

    /**
     * 删除来源
     *
     * @param id
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "物资来源删除失败", operation = "物资来源删除")
    @ApiOperation(value = "删除来源", notes = "删除来源信息")
    @RequiresPermissions({"supplier:delete"})
    @DeleteMapping("/delete/{id}")
    public ResponseBean delete(@PathVariable Long id) {
        supplierService.delete(id);
        return ResponseBean.success("删除来源成功");
    }

    /**
     * 所有来源
     *
     * @return
     */
    @ApiOperation(value = "所有来源", notes = "所有来源列表")
    @GetMapping("/findAll")
    public ResponseBean findAll() {
        List<SupplierVO> supplierVOS = supplierService.findAll();
        return ResponseBean.success(supplierVOS);
    }
}
