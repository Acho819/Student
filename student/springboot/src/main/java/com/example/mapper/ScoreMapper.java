package com.example.mapper;

import com.example.entity.Score;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ScoreMapper {

    @Delete("delete from students_score where id = #{id}")
     void deleteById(Integer id);

    @Insert("insert into students_score (course_id,students_id,score,comment,feedback) " +
            "values (#{course_id},#{students_id},#{score},#{comment},#{feedback})")
    void insert(Score score);

    @Select("select * from students_score where students_id = #{students_id} and course_id = #{course_id}")
    Score selectByCondition(Score score);

    List<Score> selectAll(Score score);

    @Update("update students_score set score = #{score},comment = #{comment},feedback = #{feedback} where id = #{id}")
    void update(Score score);
}
