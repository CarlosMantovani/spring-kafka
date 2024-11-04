package mantovani.dev.data.Service;

import lombok.RequiredArgsConstructor;
import mantovani.dev.data.dto.CarPostDTO;
import mantovani.dev.data.entity.BrandAnalyticsEntity;
import mantovani.dev.data.entity.CarModelAnalyticsEntity;
import mantovani.dev.data.entity.CarModelPriceEntity;
import mantovani.dev.data.repository.BrandAnalyticsRepository;
import mantovani.dev.data.repository.CarModelAnalyticsRepository;
import mantovani.dev.data.repository.CarPriceAnalyticsRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostAnalyticsService {

    private final BrandAnalyticsRepository brandAnalyticsRepository;
    private final CarModelAnalyticsRepository carModelAnalyticsRepository;
    private final CarPriceAnalyticsRepository carPriceAnalyticsRepository;

    public void saveDataAnalytics(CarPostDTO carPostDTO){
        saveBrandAnalytics(carPostDTO.getBrand());
        saveCarModelAnalytics(carPostDTO.getModel());
        saveCarModelPriceAnalytics(carPostDTO.getModel(), carPostDTO.getPrice());
    }
    private void saveBrandAnalytics(String brand){

        BrandAnalyticsEntity brandAnalyticsEntity = new BrandAnalyticsEntity();

        brandAnalyticsRepository.findByBrand(brand).ifPresentOrElse(item->{
            item.setPosts(item.getPosts()+1);
            brandAnalyticsRepository.save(item);
        }, ()-> {
            brandAnalyticsEntity.setBrand(brand);
            brandAnalyticsEntity.setPosts(1L);
            brandAnalyticsRepository.save(brandAnalyticsEntity);
        });

    }

    private void saveCarModelAnalytics(String carModel){

        CarModelAnalyticsEntity carModelAnalyticsEntity = new CarModelAnalyticsEntity();

        carModelAnalyticsRepository.findByModel(carModel).ifPresentOrElse(item->{
            item.setPosts(item.getPosts()+1);
            carModelAnalyticsRepository.save(item);
        }, ()-> {
            carModelAnalyticsEntity.setModel(carModel);
            carModelAnalyticsEntity.setPosts(1L);
            carModelAnalyticsRepository.save(carModelAnalyticsEntity);
        });

    }

    private void saveCarModelPriceAnalytics(String carModel, Double price){

        CarModelPriceEntity carModelPriceEntity = new CarModelPriceEntity();

        carModelPriceEntity.setModel(carModel);
        carModelPriceEntity.setPrice(price);
        carPriceAnalyticsRepository.save(carModelPriceEntity);

    }



}
