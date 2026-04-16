package service;

import model.DoctorModel;
import repository.DoctorRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DoctorService {
    private DoctorRepository repository;
    private UploadFileService uploadFileService;

    public DoctorService(DoctorRepository repository, UploadFileService uploadFileService) {
        this.repository = repository;
        this.uploadFileService = uploadFileService;
    }

    public List<DoctorModel> getAllDoctors() {
        return repository.getDoctors();
    }

    /**
     * just create model and add it to repository, you can ignore photo for now, we will add it later.
     */
    public DoctorModel create(Map<String, String> form) {

        return  null;
    }

    /**
     * here you delete photo and update model in repository.
     */
    public void update(DoctorModel doctorModel, Map<String, String> form) {

    }

    /**
     * here you delete photo and delete model in repository.
     */
    public void delete(DoctorModel doctorModel) {

    }

    /**
     * just for testing purposes, to clear the repository before each test.
     * in reality we never use it only for practice for testing.
     */
    public void clear() {
        this.repository.clear();
    }
}
