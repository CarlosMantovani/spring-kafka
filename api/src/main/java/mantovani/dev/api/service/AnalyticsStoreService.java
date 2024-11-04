package mantovani.dev.api.service;

import lombok.RequiredArgsConstructor;
import mantovani.dev.api.client.CarPostStoreClient;
import mantovani.dev.api.dto.AnalyticsBrandDTO;
import mantovani.dev.api.dto.AnalyticsModelDTO;
import mantovani.dev.api.dto.AnalyticsPriceDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AnalyticsStoreService {

    private final CarPostStoreClient carPostStoreClient;

    public List<AnalyticsBrandDTO> getBrandAnalytics(){
        return carPostStoreClient.getBrand();
    }
    public List<AnalyticsModelDTO>getModelAnalytics(){
        return carPostStoreClient.getModel();
    }

    public List<AnalyticsPriceDTO>getPriceAnalytics(){
        return carPostStoreClient.getPrice();
    }
}
