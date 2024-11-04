package mantovani.dev.car.message;

import mantovani.dev.car.dto.CarPostDTO;
import mantovani.dev.car.service.CarPostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerMessage {

    private final Logger LOG = LoggerFactory.getLogger(KafkaConsumerMessage.class);

    @Autowired
    private CarPostService carPostService;

    @KafkaListener(topics = "car-post-topic", groupId = "store-posts-group")
    public void listening(CarPostDTO carPost) {
        LOG.info("CAR STORE - Received Car Post information: {}", carPost);

        try {
            carPostService.newPostDetails(carPost);
        } catch (Exception e) {
            LOG.error("Error processing Car Post: {}", e.getMessage(), e);
        }
    }
}
