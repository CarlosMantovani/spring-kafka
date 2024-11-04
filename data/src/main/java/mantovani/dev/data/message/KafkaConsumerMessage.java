package mantovani.dev.data.message;

import lombok.RequiredArgsConstructor;
import mantovani.dev.data.Service.PostAnalyticsService;
import mantovani.dev.data.dto.CarPostDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class KafkaConsumerMessage {

    private final Logger LOG = LoggerFactory.getLogger(KafkaConsumerMessage.class);

    private final PostAnalyticsService postAnalyticsService;

    @KafkaListener(topics = "car-post-topic", groupId = "analytics-post-group")
    public void listening(@Payload CarPostDTO carPost){
        System.out.println("Received Car Post information:" + carPost);
        LOG.info("ANALYTICS DATA - Received Car Post information: {}");
        postAnalyticsService.saveDataAnalytics(carPost);
    }
}
