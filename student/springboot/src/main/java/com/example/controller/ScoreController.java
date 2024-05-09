package com.example.controller;

import com.example.common.Result;
import com.example.entity.Score;
import com.example.service.ScoreService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/Score")
public class ScoreController {
    @Resource
    ScoreService scoreService;

    @PostMapping("/add")
    public Result add(@RequestBody Score score){
        scoreService.add(score);
        return Result.success();
    }
}
