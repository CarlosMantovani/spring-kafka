package mantovani.dev.api.message;

import lombok.RequiredArgsConstructor;
import mantovani.dev.api.dto.CarPostDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducerMessage {

    private final KafkaTemplate<String, CarPostDTO> kafkaTemplate;

    private final String KAFKA_TOPIC = "car-post-topic";

    public void sendMessage(CarPostDTO carPostDTO){
        kafkaTemplate.send(KAFKA_TOPIC, carPostDTO);
    }
}
