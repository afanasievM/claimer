//package claimer.app.repository
//
//import claimer.app.entity.Berachain
//import java.util.UUID
//import org.springframework.data.repository.reactive.ReactiveCrudRepository
//import reactor.core.publisher.Flux
//import reactor.core.publisher.Mono
//
//interface PenumbraRepository : ReactiveCrudRepository<Berachain, UUID> {
//    override fun findById(id: UUID): Mono<Berachain>
//    override fun findAll(): Flux<Berachain>
//
//    fun save(berachain: Berachain): Mono<Berachain>
//}
