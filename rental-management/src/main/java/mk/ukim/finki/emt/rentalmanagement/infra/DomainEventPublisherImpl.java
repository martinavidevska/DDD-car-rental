package mk.ukim.finki.emt.rentalmanagement.infra;


import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;
import mk.ukim.finki.emt.sharedkernel.infra.DomainEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;



@Service
@AllArgsConstructor
public class DomainEventPublisherImpl implements DomainEventPublisher {

    private final KafkaTemplate<String, String>  kafkaTemplate;
    @Override
    public void publish(DomainEvent event) {
       this.kafkaTemplate.send(event.topic(),event.toJson());
    }
}
