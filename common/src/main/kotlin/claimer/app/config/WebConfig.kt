package claimer.app.config

import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import java.util.concurrent.TimeUnit
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.Connection
import reactor.netty.http.client.HttpClient
import reactor.netty.tcp.TcpClient


@Configuration
class WebConfig {

    @Bean
    fun webClientWithTimeout(): WebClient {
        val tcpClient = TcpClient
            .create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT.toInt())
            .doOnConnected { connection: Connection ->
                connection.addHandlerLast(ReadTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS))
                connection.addHandlerLast(WriteTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS))
            }

        return WebClient.builder()
            .clientConnector(ReactorClientHttpConnector(HttpClient.from(tcpClient)))
            .build()
    }

    companion object{
        private const val TIMEOUT = 2000L
    }
}
