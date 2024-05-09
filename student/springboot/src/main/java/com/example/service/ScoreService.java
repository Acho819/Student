package com.example.service;

import com.example.entity.Score;
import com.example.mapper.ScoreMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ScoreService {

    @Resource
    ScoreMapper scoreMapper;

    public void add(Score score){
        scoreMapper.insert(score);
    }
}
