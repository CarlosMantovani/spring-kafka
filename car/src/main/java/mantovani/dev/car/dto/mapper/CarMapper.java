package mantovani.dev.car.dto.mapper;

import mantovani.dev.car.dto.CarPostDTO;
import mantovani.dev.car.entity.CarPostEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {
        public CarPostDTO toCarPostDto(CarPostEntity carPostEntity){
            return new ModelMapper().map(carPostEntity, CarPostDTO.class);
        }
}
