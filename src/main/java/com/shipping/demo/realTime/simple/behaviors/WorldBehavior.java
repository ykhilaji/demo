package com.shipping.demo.realTime.simple.behaviors;

import org.springframework.stereotype.Component;

import com.shipping.demo.realTime.core.behaviors.SingletonBehavior;

import org.apache.pekko.actor.typed.Behavior;
import org.apache.pekko.actor.typed.javadsl.Behaviors;
import org.apache.pekko.actor.typed.ActorRef;

@Component
public class WorldBehavior implements SingletonBehavior<WorldBehavior.Command> {
	@Override
	public String beanName() {
		return "system-world-behavior";
	}

	@Override
	public Behavior<Command> create() {
		return Behaviors.setup(
				context ->
						Behaviors.receive(Command.class)
								.onMessage(HelloCommand.class, command -> handleHelloCommand())
								.onMessage(AskMessageCommand.class, this::handleAskMessageCommand)
								.onMessage(HiCommand.class, command -> handleHiCommand())
								.build());
	}

	private Behavior<Command> handleHelloCommand() {
		System.out.println("hello world");
		return Behaviors.same();
	}

	private Behavior<Command> handleHiCommand() {
		System.out.println("hi world");
		return Behaviors.same();
	}

	private Behavior<Command> handleAskMessageCommand(AskMessageCommand<String> command) {
        command.replyTo.tell("Received: " + command.message);
        return Behaviors.same();
    }

public sealed interface Command permits HelloCommand, HiCommand ,AskMessageCommand {}

public record HelloCommand() implements Command {}

public record HiCommand() implements Command {}

public record AskMessageCommand<T>(String message, ActorRef<T> replyTo) implements Command {
}
}