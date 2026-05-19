package validator.person;

import dto.PersonRequest;

import java.util.HashMap;
import java.util.Map;

public class PersonValidator {
   public Map<String, String> validate(PersonRequest dto) {
       Map<String, String> errors = new HashMap<>();

       if (dto.name == null) {
           errors.put("name", "Name is required.");
       }

       if (dto.name != null && dto.name.isEmpty()) {
          errors.put("name", "Name is required.");
       }

       return errors;
   }
}
