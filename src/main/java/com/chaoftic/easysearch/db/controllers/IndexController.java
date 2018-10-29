package com.chaoftic.easysearch.db.controllers;

import com.chaoftic.easysearch.db.models.BaseEntity;
import com.chaoftic.easysearch.db.services.IndexService;
import com.chaoftic.easysearch.db.services.PostService;
import com.chaoftic.easysearch.index.inverted.InvertedIndex;
import com.chaoftic.easysearch.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
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
    public Result get(@RequestParam(defaultValue = "post") String category) {
        List<BaseEntity> baseEntities = new LinkedList<>(postService.findAll());
        InvertedIndex result = indexService.getInvertedIndex(baseEntities);
        return Result.success(result);
    }

}
