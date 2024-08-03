package party.zonarius.hermes.sender;

import discord4j.core.object.entity.channel.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public class HermesDiscordSender implements HermesSender {
    private final MessageChannel channel;

    public HermesDiscordSender(MessageChannel channel) {
        this.channel = channel;
    }

    @Override
    public void sendMessage(String message) {
        channel.createMessage(message).block();
    }
}
