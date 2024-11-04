package mantovani.dev.api.service;

import mantovani.dev.api.client.CarPostStoreClient;
import mantovani.dev.api.dto.OwnerPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerPostService  {

    @Autowired
    private CarPostStoreClient carPostStoreClient;

    public void createOwnerCar(OwnerPostDTO ownerPostDTO) {
            carPostStoreClient.ownerPostsClient(ownerPostDTO);
    }
}
