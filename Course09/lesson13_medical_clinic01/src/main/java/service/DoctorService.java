package service;

import model.DoctorModel;
import repository.DoctorRepository;

import java.util.List;

public class DoctorService {
    private DoctorRepository repository;

    public DoctorService() {
        this.repository = new DoctorRepository();
    }

    public List<DoctorModel> getAllDoctors() {
        return repository.getAllDoctors();
    }
}
