package org.ztshy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.ztshy.entity.ChatRecord;
import org.ztshy.mapper.ChatRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/model")
public class ChatController {

    @Autowired
    private ChatRecordMapper chatRecordMapper;
    private static final String OLLAMA_URL = "http://localhost:11434/api/generate";

    @PostMapping("/generate")
    public ResponseEntity<?> generate(@RequestBody Map<String, String> request) {
        String prompt = request.get("prompt");

        // 构造Ollama请求参数
        Map<String, Object> body = new HashMap<>();
        body.put("model", "deepseek-r1:1.5b");
        body.put("prompt", prompt);
        body.put("stream", false); // 非流式响应

        // 发送请求到Ollama
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(
                    OLLAMA_URL,
                    body,
                    Map.class
            );
            String answer = (String) response.getBody().get("response");
            // 创建ChatRecord对象
            ChatRecord chatRecord = new ChatRecord();
            chatRecord.setQuestion(prompt);
            chatRecord.setAnswer(answer);
            chatRecord.setCreateTime(new Date());
            chatRecord.setHistoryId(1);
            // 生成回答后保存记录
            chatRecordMapper.saveQuery(chatRecord);
            return ResponseEntity.ok(Collections.singletonMap("data", answer));
        } catch (Exception e) {
            e.printStackTrace(); // 打印详细的错误堆栈信息
            return ResponseEntity.status(500).body("模型调用失败: " + e.getMessage());
        }
    }
}