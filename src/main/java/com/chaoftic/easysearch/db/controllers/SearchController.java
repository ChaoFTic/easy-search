package com.chaoftic.easysearch.db.controllers;

import com.chaoftic.easysearch.db.models.BaseEntity;
import com.chaoftic.easysearch.db.services.IndexService;
import com.chaoftic.easysearch.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("search")
public class SearchController {
    private final IndexService indexService;

    @Autowired
    public SearchController(IndexService indexService) {
        this.indexService = indexService;
    }

    @PostMapping
    public Result get(@RequestParam(defaultValue = "1") Integer page,
                      @RequestParam(defaultValue = "10") Integer pageSize,
                      @RequestParam(defaultValue = "") String query) {
        List<BaseEntity> result = indexService.vectorSearch(query, page * pageSize)
                .subList((page - 1) * pageSize, page * pageSize);
        return Result.success(result);
    }

}
