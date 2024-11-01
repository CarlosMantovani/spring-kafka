package mantovani.dev.car.dto.mapper;


import mantovani.dev.car.dto.OwnerPostDTO;
import mantovani.dev.car.entity.OwnerPostEntity;
import org.modelmapper.ModelMapper;

public class OwnerMapper {
    public OwnerPostEntity toOwnerPostDto(OwnerPostDTO ownerPostDTO){
        return new ModelMapper().map(ownerPostDTO, OwnerPostEntity.class);
    }
}
