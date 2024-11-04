package mantovani.dev.api.controller;

import lombok.RequiredArgsConstructor;
import mantovani.dev.api.dto.AnalyticsBrandDTO;
import mantovani.dev.api.dto.AnalyticsModelDTO;
import mantovani.dev.api.dto.AnalyticsPriceDTO;
import mantovani.dev.api.dto.CarPostDTO;
import mantovani.dev.api.message.KafkaProducerMessage;
import mantovani.dev.api.service.AnalyticsStoreService;
import mantovani.dev.api.service.CarPostStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor
public class CarPostController {
    private final Logger LOG = LoggerFactory.getLogger(CarPostController.class);
    private final CarPostStoreService carPostStoreService;
    private final AnalyticsStoreService analyticsStoreService;
    private final KafkaProducerMessage kafkaProducerMessage;

        @PostMapping("/post")
        public ResponseEntity postCarForSale(@RequestBody CarPostDTO carPostDTO){

        LOG.info("USANDO EVENTOS/MENSAGENS KAFKA - Producer Car Post information: {}", carPostDTO);

        kafkaProducerMessage.sendMessage(carPostDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<CarPostDTO>> getCarSales(){
        return ResponseEntity.status(HttpStatus.OK).body(carPostStoreService.getCarForSales());
    }

    @GetMapping("/brands")
    public ResponseEntity<List<AnalyticsBrandDTO>> getBrands(){
            return ResponseEntity.status(HttpStatus.OK).body(analyticsStoreService.getBrandAnalytics());
    }
    @GetMapping("/model")
    public ResponseEntity<List<AnalyticsModelDTO>> getModel(){
        return ResponseEntity.status(HttpStatus.OK).body(analyticsStoreService.getModelAnalytics());
    }
    @GetMapping("/price")
    public ResponseEntity<List<AnalyticsPriceDTO>> getPrice(){
        return ResponseEntity.status(HttpStatus.OK).body(analyticsStoreService.getPriceAnalytics());
    }
    @PutMapping("/{id}")
    public ResponseEntity changeCarSale(@RequestBody CarPostDTO carPostDTO, @PathVariable("id") String id){
        carPostStoreService.changeCarForSale(carPostDTO,id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCarForSale(@PathVariable("id") String id){
        carPostStoreService.removeCarForSale(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
