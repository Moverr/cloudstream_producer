package com.kodeinc.producer.demo;

import java.util.function.Consumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(ProducerChannels.class)
@SpringBootApplication
public class DemoApplication {
    
    
    private final MessageChannel Consumer;

    public DemoApplication(ProducerChannels channels) {
        this.Consumer = channels.consumer();
    }
    
    
    @PostMapping("greet/{name}");
    public void publish(@PathVariable String name){
        String greeting =  "Hello "+name;
    }

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

interface ProducerChannels{
    
    @Output
    MessageChannel consumer();
}
