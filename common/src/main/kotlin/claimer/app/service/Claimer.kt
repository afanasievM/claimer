package claimer.app.service

import reactor.core.publisher.Mono

interface Claimer {
    fun claim():Mono<Unit>
}
