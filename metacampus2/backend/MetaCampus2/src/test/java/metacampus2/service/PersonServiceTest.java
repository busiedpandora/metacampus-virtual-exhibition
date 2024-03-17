/*
package metacampus2.service;

import metacampus2.AbstractTest;
import metacampus2.model.Event;
import metacampus2.model.Person;
import metacampus2.model.Role;
import metacampus2.repository.IEventRepository;
import metacampus2.repository.IPersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PersonServiceTest extends AbstractTest {

    @Mock
    private IPersonRepository personRepository;

    private PersonService personService;

    @BeforeEach
    public void setUp() {
        personService = new PersonService(personRepository);
    }

    @Test
    void getPersonFromMetaverse() {

        Person person = new Person();
        person.setLastName("Rinaldi");

        when(personRepository.findByFirstNameAndLastNameAndCellphoneAndMetaverseName(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString())).thenReturn(person);

        assertEquals(person,personService.getPersonFromMetaverse("","Rinaldi", "","Campus Est SUPSI"));

    }

    @Test
    void getAllPeople() {

        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setLastName("Rinaldi");

        personList.add(person);
        personList.add(new Person());

        when(personRepository.findAll()).thenReturn(personList);

        assertEquals(personList.size(), personService.getAllPeople().size());

    }

    @Test
    void getAllLecturers() {

        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setLastName("Rinaldi");
        person.setRole(Role.LECTURER);

        personList.add(person);
        personList.add(new Person());

        when(personRepository.findAll()
                .stream()
                .filter(p -> p.getRole() == Role.LECTURER)
                .toList()).thenReturn(personList);

        assertEquals(1, personService.getAllLecturers().size());

    }

    @Test
    void getAllPeopleFromMetaverse() {

        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setLastName("Rinaldi");
        person.setRole(Role.LECTURER);

        personList.add(person);
        personList.add(new Person());

        when(personRepository.findAllByMetaverseName(Mockito.anyString())).thenReturn(personList);


        assertEquals(person.getLastName(), personService.getAllPeopleFromMetaverse("Campus Est SUPSI").get(0).getLastName());

    }
}*/
