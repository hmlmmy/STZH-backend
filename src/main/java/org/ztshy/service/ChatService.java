package org.ztshy.service;

import org.ztshy.client.DeepSeekR1Client;
import org.ztshy.entity.ChatRecord;
import org.ztshy.mapper.ChatRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ChatService {

    private final ChatRecordMapper chatRecordMapper;

    @Autowired
    public ChatService(ChatRecordMapper chatRecordMapper) {
        this.chatRecordMapper = chatRecordMapper;
    }

    public String getAnswer(String question) {
        try {
            String answer = DeepSeekR1Client.getAnswer(question);
            // 保存对话记录到数据库
            ChatRecord chatRecord = new ChatRecord();
            chatRecord.setQuestion(question);
            chatRecord.setAnswer(answer);
            chatRecord.setCreateTime(new Date());
            chatRecordMapper.insertChatRecord(chatRecord);
            return answer;
        } catch (Exception e) {
            e.printStackTrace();
            return "调用模型出错";
        }
    }
}
