package com.example.service;

import com.example.entity.Score;
import com.example.exception.CustomException;
import com.example.mapper.ScoreMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScoreService {

    @Resource
    ScoreMapper scoreMapper;

    public void add(Score score){
        Score dbScore = scoreMapper.selectByCondition(score);
        if (dbScore != null){
            throw new CustomException("您已经打过分了");
        }
        scoreMapper.insert(score);
    }

    public PageInfo<Score> selectPage(Integer pageNum, Integer pageSize, Score score) {
        PageHelper.startPage(pageNum,pageSize);
        List<Score> list = scoreMapper.selectAll(score);
        return PageInfo.of(list);
    }

    public void update(Score score) {
        scoreMapper.update(score);
    }

    public void deleteById(Integer id) {
        scoreMapper.deleteById(id);
    }
}
