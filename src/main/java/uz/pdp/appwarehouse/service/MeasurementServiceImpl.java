package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.dtos.Result;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementServiceImpl implements MeasurementService {

    final MeasurementRepository measurementRepository;

    public MeasurementServiceImpl(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Override
    public Result save(Measurement measurement) {
        if (measurementRepository.existsByName(measurement.getName()))
            return new Result("Bunday o'lchov birligi mavjud!", false);

        measurementRepository.save(measurement);
        return new Result("O'lchov birligi bazaga saqlandi!", true);
    }

    @Override
    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    @Override
    public Measurement findById(Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        return optionalMeasurement.orElse(null);
    }

    @Override
    public Result edit(Measurement measurement, Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isPresent()) {
            optionalMeasurement.get().setName(measurement.getName());
            return new Result("Muvaffaqiyatli tahrirlandi!", true);
        }
        return new Result("Bunday o'lchov birligi topilmadi!", false);
    }

    @Override
    public Result delete(Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if(optionalMeasurement.isPresent()){
            measurementRepository.deleteById(id);
            return new Result("Muvaffaqiyatli o'chirildi!", true);
        }
        return new Result("Bunday o'lchov birligi topilmadi!", false);
    }
}
