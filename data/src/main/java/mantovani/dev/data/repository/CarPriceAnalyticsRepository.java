package mantovani.dev.data.repository;

import mantovani.dev.data.entity.CarModelAnalyticsEntity;
import mantovani.dev.data.entity.CarModelPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarPriceAnalyticsRepository extends JpaRepository<CarModelAnalyticsEntity, Long> {
    Optional<CarModelPriceEntity> findByModel(String model);
}
