package mantovani.dev.api.service;

import lombok.RequiredArgsConstructor;
import mantovani.dev.api.client.CarPostStoreClient;
import mantovani.dev.api.dto.CarPostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarPostStoreServiceImpl implements CarPostStoreService{

    private CarPostStoreClient carPostStoreClient;
    @Override
    public List<CarPostDTO> getCarForSales() {
        return carPostStoreClient.carForSaleClient();
    }

    @Override
    public void changeCarForSale(CarPostDTO carPost, String id) {
        carPostStoreClient.changeCarForSaleClient(carPost, id);
    }

    @Override
    public void removeCarForSale(String id) {
        carPostStoreClient.deleteCarForSaleClient(id);
    }
}
