package mantovani.dev.car.repository;

import mantovani.dev.car.entity.CarPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerPostRepository extends JpaRepository<CarPostEntity, Long> {
}
