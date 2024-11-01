package mantovani.dev.car.controller;

import lombok.RequiredArgsConstructor;
import mantovani.dev.car.dto.CarPostDTO;
import mantovani.dev.car.service.CarPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class CarPostController {

    private final CarPostService carPostService;

    @GetMapping("/cars")
    public ResponseEntity<List<CarPostDTO>> getCarSales(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(carPostService.getCarSales());
    }

    @PutMapping("/car/{id}")
    public ResponseEntity changeCarSale(@RequestBody CarPostDTO carPostDTO, @PathVariable("id") String id){
        carPostService.changeCarSales(carPostDTO, Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity removeCarSale(@PathVariable("id") String id){
        carPostService.removeCarSale(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
