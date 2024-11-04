package mantovani.dev.data.Service;

import lombok.RequiredArgsConstructor;
import mantovani.dev.data.controller.mapper.AnalyticsMapper;
import mantovani.dev.data.dto.AnalyticsBrandDTO;
import mantovani.dev.data.dto.AnalyticsModelDTO;
import mantovani.dev.data.dto.AnalyticsPriceDTO;
import mantovani.dev.data.dto.CarPostDTO;
import mantovani.dev.data.entity.BrandAnalyticsEntity;
import mantovani.dev.data.entity.CarModelAnalyticsEntity;
import mantovani.dev.data.entity.CarModelPriceEntity;
import mantovani.dev.data.repository.BrandAnalyticsRepository;
import mantovani.dev.data.repository.CarModelAnalyticsRepository;
import mantovani.dev.data.repository.CarPriceAnalyticsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostAnalyticsService {

    private final BrandAnalyticsRepository brandAnalyticsRepository;
    private final CarModelAnalyticsRepository carModelAnalyticsRepository;
    private final CarPriceAnalyticsRepository carPriceAnalyticsRepository;

    private final AnalyticsMapper analyticsMapper;
    public void saveDataAnalytics(CarPostDTO carPostDTO){
        saveBrandAnalytics(carPostDTO.getBrand());
        saveCarModelAnalytics(carPostDTO.getModel());
        saveCarModelPriceAnalytics(carPostDTO.getModel(), carPostDTO.getPrice());
    }

    public List<AnalyticsBrandDTO> getAnalyticsBrand(){
        List<AnalyticsBrandDTO> analyticsBrandList = new ArrayList<>();
        return brandAnalyticsRepository.findAll().stream().map(analyticsMapper::toAnalyticsBrandDTO)
                .collect(Collectors.toList());
    }
    public List<AnalyticsModelDTO> getAnalyticsModel(){
        List<AnalyticsModelDTO> analyticsModelList = new ArrayList<>();
        return carModelAnalyticsRepository.findAll().stream().map(analyticsMapper::toAnalyticsModelDTO)
                .collect(Collectors.toList());
    }
    public List<AnalyticsPriceDTO> getAnalyticsPrice(){
        List<AnalyticsPriceDTO> analyticsPriceList = new ArrayList<>();
        return carPriceAnalyticsRepository.findAll().stream().map(analyticsMapper::toAnalyticsPriceDTO)
                .collect(Collectors.toList());
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
