package com.kodeinc.producer.demo;

import java.util.function.Consumer;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@RestController
@EnableBinding(ProducerChannels.class)
public class ProducerApplication {

    private final MessageChannel Consumer;

    public ProducerApplication(ProducerChannels channels) {
        this.Consumer = channels.consumer(); 
    }
 
    @PostMapping("greet/{name}")
    public String  publish(@PathVariable String name) {
        String greeting = "Hello " + name;
        Message<String> msg = MessageBuilder.withPayload(greeting).build();

    
        this.Consumer.send(msg);
        
        return "Hello Rogers";
    }

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

}

interface ProducerChannels {

    @Output("mixer") 
    MessageChannel consumer();
}
