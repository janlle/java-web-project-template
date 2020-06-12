package com.domain.cms.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Set;

/**
 * @author leone
 **/
@RestController
@Api(tags = "模板")
@RequestMapping("api/template")
public class TemplateController {

    @PostMapping
    @ApiOperation("添加")
    public void save(@RequestBody @Valid Object object) {
    }

    @DeleteMapping
    @ApiOperation("删除")
    public void delete(@RequestParam @ApiParam("id列表") Set<Integer> ids) {
    }

    @PutMapping
    @ApiOperation("修改")
    public Object update(@RequestBody Object object) {
        return null;
    }


    @GetMapping("/page")
    @ApiOperation("分页")
    public Page<Object> page(@ModelAttribute Object dto, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return null;
    }

    @GetMapping("/export")
    @ApiOperation("导出excel")
    public void export(Pageable pageable, @RequestBody Object query, HttpServletResponse response) {

    }

}
