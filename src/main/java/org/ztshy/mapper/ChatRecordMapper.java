package org.ztshy.mapper;

import org.ztshy.entity.ChatRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatRecordMapper {
    @Insert("INSERT INTO chat_record (question, answer, create_time) VALUES (#{question}, #{answer}, NOW())")
    void insertChatRecord(ChatRecord chatRecord);
}