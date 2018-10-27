package com.chaoftic.easysearch.db.controllers;

import com.chaoftic.easysearch.db.models.Blogger;
import com.chaoftic.easysearch.db.services.BloggerService;
import com.chaoftic.easysearch.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api(tags = "Blogger")
@RestController
@RequestMapping("blogger")
public class BloggerController {
    private final BloggerService bloggerService;


    @Autowired
    public BloggerController(BloggerService bloggerService) {
        this.bloggerService = bloggerService;
    }


    @GetMapping
    @ApiOperation("获取Blogger")
    public Result list(@RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = "15") Integer size) {

        return Result.success(bloggerService.findAll(PageRequest.of(page, size)));
    }

    @PutMapping("{id:\\d+}")
    public Result update(@PathVariable Integer id, @RequestParam Blogger newBlogger) {
        Optional<Blogger> blogger = bloggerService.findById(id);
        if (blogger.isPresent()) {
            newBlogger.setId(id);
            bloggerService.save(newBlogger);
            return Result.success("updated");
        } else {
            throw new ServiceException("不存在id为" + id + "的Blogger");
        }
    }

    @PostMapping
    public Result insert(@RequestParam Blogger blogger) {
        Blogger result = bloggerService.save(blogger);
        return Result.success(result, "inserted");
    }

    @DeleteMapping("{id:\\d+}")
    public Result deleteById(@PathVariable Integer id) {
        Optional<Blogger> blogger = bloggerService.findById(id);
        if (blogger.isPresent()) {
            bloggerService.delete(blogger.get());
            return Result.success("Blogger" + id + "deleted");
        } else {
            throw new ServiceException("不存在id为" + id + "的Blogger");
        }
    }
}
