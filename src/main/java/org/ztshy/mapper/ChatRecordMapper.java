package org.ztshy.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.ztshy.entity.ChatRecord;
import org.apache.ibatis.annotations.Mapper;

// 标注这是一个 MyBatis 的 Mapper 接口
@Mapper
public interface ChatRecordMapper {
    /**
     * 保存对话记录到数据库
     * @param chatRecord 要保存的对话记录对象
     * @return 插入成功的记录数，通常为 1
     */

    @Insert("INSERT INTO chat_record (question, answer, createTime, historyId) VALUES (#{question}, #{answer}, #{createTime}, #{historyId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int saveQuery(ChatRecord chatRecord);
}