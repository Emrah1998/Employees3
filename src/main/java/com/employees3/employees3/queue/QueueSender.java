package com.employees3.employees3.queue;

import com.employees3.employees3.model.response.ChangeManagerStatusDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class QueueSender {
    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public void sendMessageToQ(ChangeManagerStatusDto request){
        amqpTemplate.convertAndSend("TEST_Q", objectMapper.writeValueAsString(request));
        log.info("Sending to queue end");
    }

    @PostConstruct
    public void test(){
        log.info("Sending to queue");
        sendMessageToQ(new ChangeManagerStatusDto(1L));
    }
}
