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

    public void saveMeasurementUnit(MeasurementUnit measurementUnit) {
        if (!measurementUnitRepo.existsByShortName(measurementUnit.getShortName())) {
            measurementUnitRepo.save(measurementUnit);
        }
    }

    public Iterable<MeasurementUnit> getAllMeasurementUnits() {
        return measurementUnitRepo.findAll();
    }
}
