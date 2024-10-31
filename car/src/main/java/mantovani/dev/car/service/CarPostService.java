package mantovani.dev.car.service;

import lombok.RequiredArgsConstructor;
import mantovani.dev.car.dto.CarPostDTO;
import mantovani.dev.car.dto.mapper.CarMapper;
import mantovani.dev.car.repository.CarPostRepository;
import mantovani.dev.car.repository.OwnerPostRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CarPostService {

    private final CarPostRepository carPostRepository;
    private final OwnerPostRepository ownerPostRepository;
    private final CarMapper carMapper;

    public void newPostDetails(CarPostDTO carPostDTO) {

    }

    public List<CarPostDTO> getCarSales() {
        List<CarPostDTO> listCarSales = new ArrayList<>();
        return carPostRepository.findAll().stream().map(carMapper::toCarPostDto)
                .collect(Collectors.toList());
    }

    public void changeCarSales(CarPostDTO carPostDTO, Long postId) {
            carPostRepository.findById(postId).ifPresentOrElse(item -> {
                item.setDescription(carPostDTO.getDescription());
                item.setContact(carPostDTO.getContact());
                item.setPrice(carPostDTO.getPrice());
                item.setBrand(carPostDTO.getBrand());
                item.setEngineVersion(carPostDTO.getEngineVersion());
                item.setModel(carPostDTO.getModel());

                carPostRepository.save(item);
            }, ()-> {
                throw new NoSuchElementException();
            });

    }

    public void removeCarSale(Long postId) {

        carPostRepository.deleteById(postId);

    }

}
