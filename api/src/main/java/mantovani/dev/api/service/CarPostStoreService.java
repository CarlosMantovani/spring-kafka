package mantovani.dev.api.service;

import lombok.RequiredArgsConstructor;
import mantovani.dev.api.client.CarPostStoreClient;
import mantovani.dev.api.dto.AnalyticsBrandDTO;
import mantovani.dev.api.dto.AnalyticsModelDTO;
import mantovani.dev.api.dto.AnalyticsPriceDTO;
import mantovani.dev.api.dto.CarPostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarPostStoreService {

    private final CarPostStoreClient carPostStoreClient;

    public List<CarPostDTO> getCarForSales() {
        return carPostStoreClient.carForSaleClient();
    }

    public void changeCarForSale(CarPostDTO carPost, String id) {
        carPostStoreClient.changeCarForSaleClient(carPost, id);
    }

    public void removeCarForSale(String id) {
        carPostStoreClient.deleteCarForSaleClient(id);
    }


}
