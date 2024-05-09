package com.example.mapper;

import com.example.entity.Score;
import org.apache.ibatis.annotations.Insert;

public interface ScoreMapper {

    @Insert("insert into students_score (course_id,students_id,score,comment,feedback) " +
            "values (#{course_id},#{students_id},#{score},#{comment},#{feedback})")
    void insert(Score score);
}
