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
     * form is key - value that controller received from POST request.
     */
    public DoctorModel create(Map<String, String> form) {
        DoctorModel doctor = new DoctorModel(
                UUID.randomUUID(),
                form.get("name"),
                form.get("surname"),
                form.get("spe"),
                this.uploadFileService.getURL(form.get("file"))
        );
        this.repository.add(doctor);
        // photo we don't need to copy, it's uploaded by controller before this method.


        return doctor;
    }

    public DoctorModel getByUUID(UUID id) {
        return this.repository.getByUUID(id);
    }

    /**
     * here you delete photo and update model in repository.
     */
    public void update(DoctorModel doctorModel, Map<String, String> form) {
        // new file exists from controller like create method will get it.
        // here we need to delete old one before we update doctorModel object

        // if in form it submitted new file
        // we need to delete old one and update doctorModel with new URL.
        if (form.get("file") != null) {
            String photoURL = doctorModel.getPhoto();
            this.uploadFileService.deleteFile(photoURL);
            doctorModel.setPhoto(this.uploadFileService.getURL(form.get("file")));
        }

        doctorModel.setFirstName(form.get("name"));
        doctorModel.setLastName(form.get("surname"));
        doctorModel.setProfession(form.get("spe"));

        // save to file not only to RAM.
        this.repository.update(doctorModel);
    }

    /**
     * here you delete photo and delete model in repository.
     */
    public void delete(DoctorModel doctorModel) {
        // delete photo if exists because
        // if i delete doctor first I will not have any link for his photo.

        // after I deleted photo I can delete doctor from repository.

        this.uploadFileService.deleteFile(doctorModel.getPhoto());
        this.repository.deleteByUUID(doctorModel.getId());
    }


    /**
     * just for testing purposes, to clear the repository before each test.
     * in reality we never use it only for practice for testing.
     */
    public void clear() {
        this.repository.clear();
    }
    public DoctorRepository getRepository() {
        return repository;
    }

    public UploadFileService getUploadFileService() {
        return uploadFileService;
    }
}
