package party.zonarius.hermes;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.channel.MessageChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Closeable;

@Configuration
public class HermesConfiguration {

    @Value("${hermes.discord.token}")
    private String discordToken;

    @Value("${hermes.discord.channel.name}")
    private String channelName;

    @Bean
    public DiscordClient discordClient() {
        return DiscordClient.create(discordToken);
    }

    @Bean
    public GatewayDiscordClient gatewayDiscordClient() {
        return discordClient().login().block();
    }

    @Bean
    public Closeable gatewayDiscordCloser(GatewayDiscordClient client) {
        return () -> client.logout().block();
    }

    @Bean
    public MessageChannel discordChannel(GatewayDiscordClient client) {
        return client.getGuilds()
            .flatMap(Guild::getChannels)
            .filter(channel -> channel.getName().equals(channelName))
            .next()
            .cast(MessageChannel.class)
            .block();
    }
}
