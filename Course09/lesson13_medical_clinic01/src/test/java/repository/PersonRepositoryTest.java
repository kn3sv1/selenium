package repository;

import model.Person;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PersonRepositoryTest {
//    @Test
//    public void testSingleton() {
//        PersonRepository instance1 = PersonRepository.getInstance();
//        PersonRepository instance2 = PersonRepository.getInstance();
//
//        assertSame(instance1, instance2, "Both instances should be the same (singleton)");
//    }
    @Test
    void testAddPeople() {
        PersonRepository repository = new PersonRepository(PersonRepository.DATABASE_TEST);
        repository.clear();

        assertEquals(0, repository.getPeople().size());

        UUID id1 = UUID.randomUUID();
        Person person1 = new Person(
                id1,
                "John Doe",
                "123 Main St",
                5551234
        );

        repository.add(person1);
        Person retrievedPerson1 = repository.getById(id1);
        // the order is important, we need to check if the retrieved person is
        // not null before checking its properties because if it's null,
        // we will get NullPointerException when trying to access its properties,
        // and we want to have a clear assertion error message that the person
        // is not found in the repository.
        assertNotNull(retrievedPerson1);

        // we check if getId returns who we asked and not somebody else.
        // If ID doesn't exist or the method doesn't work correctly, we don't continue to check other properties.
        assertEquals(id1, retrievedPerson1.getId());
        assertEquals("John Doe", retrievedPerson1.getName());
        assertEquals("123 Main St", retrievedPerson1.getAddress());
        assertEquals(5551234, retrievedPerson1.getPhoneNumber());
    }

    @Test
    void testUpdatePeople() {
        PersonRepository repository = new PersonRepository(PersonRepository.DATABASE_TEST);
        repository.clear();

        UUID id1 = UUID.randomUUID();
        Person person1 = new Person(
                id1,
                "John Doe",
                "123 Main St",
                5551234
        );

        repository.add(person1);

        person1.setName("Michael Jordan");
        person1.setAddress("456 Elm St");
        person1.setPhoneNumber(5555678);
        repository.update(person1);
        Person updatedPerson1 = repository.getById(id1);
        assertNotNull(updatedPerson1);
        assertEquals("Michael Jordan", updatedPerson1.getName());
        assertEquals("456 Elm St", updatedPerson1.getAddress());
        assertEquals(5555678, updatedPerson1.getPhoneNumber());
    }

    @Test
    void testDeletePeople() {
        PersonRepository repository = new PersonRepository(PersonRepository.DATABASE_TEST);
        repository.clear();

        UUID id1 = UUID.randomUUID();
        Person person1 = new Person(
                id1,
                "John Doe",
                "123 Main St",
                5551234
        );

        repository.add(person1);
        assertNotNull(repository.getById(id1));

        repository.deleteById(id1);
        assertNull(repository.getById(id1));
    }
}
