package mantovani.dev.data.controller.mapper;

import mantovani.dev.data.dto.AnalyticsBrandDTO;
import mantovani.dev.data.dto.AnalyticsModelDTO;
import mantovani.dev.data.dto.AnalyticsPriceDTO;
import mantovani.dev.data.entity.BrandAnalyticsEntity;
import mantovani.dev.data.entity.CarModelAnalyticsEntity;
import mantovani.dev.data.entity.CarModelPriceEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AnalyticsMapper {

    public AnalyticsBrandDTO toAnalyticsBrandDTO(BrandAnalyticsEntity brandAnalyticsEntity){
        return new ModelMapper().map(brandAnalyticsEntity, AnalyticsBrandDTO.class);
    }

    public AnalyticsModelDTO toAnalyticsModelDTO(CarModelAnalyticsEntity carModelAnalyticsEntity){
        return new ModelMapper().map(carModelAnalyticsEntity, AnalyticsModelDTO.class);
    }
    public AnalyticsPriceDTO toAnalyticsPriceDTO(CarModelPriceEntity carModelPriceEntity){
        return new ModelMapper().map(carModelPriceEntity, AnalyticsPriceDTO.class);
    }



}
