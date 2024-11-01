package mantovani.dev.car.controller;

import lombok.RequiredArgsConstructor;
import mantovani.dev.car.dto.OwnerPostDTO;
import mantovani.dev.car.service.OwnerPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class OwnerPostController {

    private final OwnerPostService ownerPostService;

    public ResponseEntity createOwner(@RequestBody OwnerPostDTO ownerPostDTO){
        ownerPostService.createOwnerPost(ownerPostDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
