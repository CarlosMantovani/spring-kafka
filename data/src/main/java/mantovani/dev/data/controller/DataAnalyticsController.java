package mantovani.dev.data.controller;

import lombok.RequiredArgsConstructor;
import mantovani.dev.data.Service.PostAnalyticsService;
import mantovani.dev.data.dto.AnalyticsBrandDTO;
import mantovani.dev.data.dto.AnalyticsModelDTO;
import mantovani.dev.data.dto.AnalyticsPriceDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/data")
@RequiredArgsConstructor
public class DataAnalyticsController {

    private final PostAnalyticsService postAnalyticsService;

    @GetMapping("/analyticsBrand")
    public ResponseEntity<List<AnalyticsBrandDTO>> getBrand() {
        return ResponseEntity.status(HttpStatus.OK).body(postAnalyticsService.getAnalyticsBrand());
    }

    @GetMapping("/analyticsModel")
    public ResponseEntity<List<AnalyticsModelDTO>> getModel() {
        return ResponseEntity.status(HttpStatus.OK).body(postAnalyticsService.getAnalyticsModel());
    }

    @GetMapping("/analyticsPrice")
    public ResponseEntity<List<AnalyticsPriceDTO>> getPrice() {
        return ResponseEntity.status(HttpStatus.OK).body(postAnalyticsService.getAnalyticsPrice());
    }


}
