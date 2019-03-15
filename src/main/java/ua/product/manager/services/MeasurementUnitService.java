package ua.product.manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.product.manager.entities.MeasurementUnit;
import ua.product.manager.repo.MeasurementUnitRepo;

@Service
public class MeasurementUnitService {

    private MeasurementUnitRepo measurementUnitRepo;

    @Autowired
    public MeasurementUnitService(MeasurementUnitRepo measurementUnitRepo) {
        this.measurementUnitRepo = measurementUnitRepo;
    }

    public void saveMeasurementUnit(MeasurementUnit unit) {
        if (!measurementUnitRepo.existsByFullNameOrShortName(unit.getFullName(), unit.getShortName())) {
            measurementUnitRepo.save(unit);
        }
    }

    public Iterable<MeasurementUnit> getAllMeasurementUnits() {
        return measurementUnitRepo.findAll();
    }

    public boolean isFullNameExist(String fullName) {
        return measurementUnitRepo.existsByFullName(fullName);
    }

    public boolean isShortNameExist(String shortName) {
        return measurementUnitRepo.existsByShortName(shortName);
    }
}
