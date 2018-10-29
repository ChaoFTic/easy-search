package com.chaoftic.easysearch.db.controllers;

import com.chaoftic.easysearch.db.models.BaseEntity;
import com.chaoftic.easysearch.db.services.IndexService;
import com.chaoftic.easysearch.db.services.PostService;
import com.chaoftic.easysearch.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("index")
public class IndexController {
    private final IndexService indexService;
    private final PostService postService;

    @Autowired
    public IndexController(IndexService indexService, PostService postService) {
        this.indexService = indexService;
        this.postService = postService;
    }

    @GetMapping
    public Result get(@RequestParam(defaultValue = "post") String category,
                      @RequestParam(defaultValue = "1") Integer page,
                      @RequestParam(defaultValue = "25") Integer pageSize) {
        List<BaseEntity> baseEntities = new LinkedList<>(postService.findAll());
        List<Map<String, Object>> result = indexService.getInvertedIndex(baseEntities).getIndex();
        return Result.success(result);
    }

}
