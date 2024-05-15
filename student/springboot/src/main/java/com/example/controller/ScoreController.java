package com.example.controller;

import com.example.common.Result;
import com.example.entity.Course;
import com.example.entity.Score;
import com.example.service.ScoreService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "5") Integer pageSize,
                             Score score){
        PageInfo<Score> pageInfo = scoreService.selectPage(pageNum, pageSize,score);
        return Result.success(pageInfo);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Score score){
        scoreService.update(score);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        try {
            scoreService.deleteById(id);
            return Result.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除失败:"+ e.getMessage());
        }
    }
}
