package mantovani.dev.car.service;

import lombok.RequiredArgsConstructor;
import mantovani.dev.car.dto.OwnerPostDTO;
import mantovani.dev.car.dto.mapper.OwnerMapper;
import mantovani.dev.car.entity.OwnerPostEntity;
import mantovani.dev.car.repository.OwnerPostRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerPostService {

    private final OwnerPostRepository ownerPostRepository;
    private final OwnerMapper ownerMapper;
    public void createOwnerPost(OwnerPostDTO ownerPostDTO){
        ownerPostRepository.save(ownerMapper.toOwnerPostDto(ownerPostDTO));
    }

}
