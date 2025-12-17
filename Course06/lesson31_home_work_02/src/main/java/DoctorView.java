public class DoctorView {
    private final TemplateService templateService;
    private final SanitizerService sanitizer;

    public DoctorView(TemplateService templateService, SanitizerService sanitizer) {
        this.templateService = templateService;
        this.sanitizer = sanitizer;
    }

}
