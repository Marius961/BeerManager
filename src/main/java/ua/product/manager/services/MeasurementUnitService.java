package ua.product.manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.product.manager.entities.MeasurementUnit;
import ua.product.manager.exceptions.NotFoundException;
import ua.product.manager.repo.MeasurementUnitRepo;
import ua.product.manager.repo.ProductRepo;

import java.util.Optional;

@Service
public class MeasurementUnitService {

    private MeasurementUnitRepo measurementUnitRepo;
    private ProductRepo productRepo;

    @Autowired
    public MeasurementUnitService(MeasurementUnitRepo measurementUnitRepo, ProductRepo productRepo) {
        this.measurementUnitRepo = measurementUnitRepo;
        this.productRepo = productRepo;
    }

    public void saveMeasurementUnit(MeasurementUnit unit) throws NotFoundException {
        if (!measurementUnitRepo.existsByFullNameOrShortName(unit.getFullName(), unit.getShortName())) {
            measurementUnitRepo.save(unit);
        } else throw new NotFoundException("Measurement unit with this full name or short name already exist");
    }

    public void updateMeasurementUnit(MeasurementUnit unit) throws NotFoundException {
        if (measurementUnitRepo.existsById(unit.getId())) {
            if (measurementUnitRepo.countByShortNameAndFullNameAndNotId(unit.getShortName(), unit.getFullName(), unit.getId()) == 0) {
                measurementUnitRepo.save(unit);
            } else throw new IllegalArgumentException("Measurement unit with this full name or short name already exists");
        } else throw new NotFoundException("Cannot find measurement with id " + unit.getId());
    }

    public void deleteMeasurementUnit(Long id) {
        if (!productRepo.existsByMeasurementUnitId(id)) {
            measurementUnitRepo.deleteById(id);
        } else throw new IllegalArgumentException("Cannot delete measurement unit because it is already used");
    }

    public Iterable<MeasurementUnit> getAllMeasurementUnits() {
        return measurementUnitRepo.findAll();
    }

    public MeasurementUnit getMeasurementUnit(Long id) throws NotFoundException {
        Optional<MeasurementUnit> opMeasurementUnit = measurementUnitRepo.findById(id);
        if (opMeasurementUnit.isPresent()) {
            return opMeasurementUnit.get();
        } else throw new NotFoundException("Cannot find measurement unit with id " + id);
    }

    public boolean isFullNameExist(String fullName) {
        return measurementUnitRepo.existsByFullName(fullName);
    }

    public boolean isShortNameExist(String shortName) {
        return measurementUnitRepo.existsByShortName(shortName);
    }
}
