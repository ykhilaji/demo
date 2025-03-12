package com.shipping.demo.realTime.task.actor;

import java.time.Instant;

import org.apache.pekko.actor.typed.Behavior;
import org.apache.pekko.actor.typed.javadsl.ActorContext;
import org.apache.pekko.actor.typed.javadsl.Behaviors;
import com.shipping.demo.realTime.core.behaviors.SingletonBehavior;
import com.shipping.demo.realTime.task.converter.JacksonConfig;
import com.shipping.demo.realTime.task.dto.MessageData;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.pekko.actor.typed.ActorRef;


public class MessageProcessor implements SingletonBehavior<MessageProcessor.Command> {

    @Autowired
    private JacksonConfig jacksonConfig;

    public void setJacksonConfig(JacksonConfig jacksonConfig) {
        this.jacksonConfig = jacksonConfig;
    }

    @Override
    public String beanName() {
        return "messageProcessor";
    }

	@Override
    public Behavior<Command> create() {
        return Behaviors.setup(context -> {
            return Behaviors.receive(Command.class)
                    .onMessage(ProcessMessage.class, message -> handleProcessMessage(message, context))
                    .onMessage(ProcessJsonMessage.class, message -> handleJsonMessage(message, context))
                    .build();
        });
    }

    private Behavior<Command> handleProcessMessage(ProcessMessage message, ActorContext<Command> context) {
		System.out.println("Processing message");
		return Behaviors.same();
	}
    
    private Behavior<Command> handleJsonMessage(ProcessJsonMessage message, ActorContext<Command> context) {
        try {
            // Parse the incoming JSON to MessageData
            MessageData input = jacksonConfig.objectMapper().readValue(message.json, MessageData.class);
            
            // Create a new MessageData with current timestamp
            MessageData output = new MessageData(
                input.content(),
                Instant.now()
            );
            
            // Convert the output to JSON and send it back
            String responseJson = jacksonConfig.objectMapper().writeValueAsString(output);
            message.replyTo.tell(new JsonResponse(responseJson));
            
            return Behaviors.same();
        } catch (JsonProcessingException e) {
            System.err.println("Error processing JSON: " + e.getMessage());
            message.replyTo.tell(new JsonResponse("Error: " + e.getMessage()));
            return Behaviors.same();
        }
    }
    
    public sealed interface Command permits ProcessMessage, ProcessJsonMessage, JsonResponse {}

    public record ProcessMessage() implements Command {}
    
    public record ProcessJsonMessage(String json, ActorRef<JsonResponse> replyTo) implements Command {}
    
    public record JsonResponse(String json) implements Command {}
}
