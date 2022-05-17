package com.home.skydance.dao;

import com.home.skydance.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    /**
     * 計算所有用戶數量
     * @return
     */
    long countUsers();

    /**
     * 查找用戶
     * @param id
     * @return
     */
    User selectByPrimaryKey(Integer id);

    /**
     * 根据时间分页查询
     * @param time
     * @return
     */
    List<User> selectByDateTime(Date time, Integer start, Integer limit);

    /**
     * 计算需要预处理的数量
     * @param time
     * @return
     */
    int countPreRecordsByDate(Date time);

    /**
     * 批量插入数据
     * @param users
     */
    void insertForeach(List<User> users);

    /**
     * 批量更新数据
     * @param users
     */
    void updateForeach(List<User> users);
}
