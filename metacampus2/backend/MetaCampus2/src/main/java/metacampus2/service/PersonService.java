package metacampus2.service;

import metacampus2.model.Person;
import metacampus2.model.Role;
import metacampus2.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements IPersonService {
    @Autowired
    private PersonRepository personRepository;


    @Override
    public void addNewPerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public Person getPersonFromMetaverse(String personFirstName, String personLastName, String personCellphone, String metaverseName) {
        return personRepository.findByFirstNameAndLastNameAndCellphoneAndMetaverseName(personFirstName,
                personLastName, personCellphone, metaverseName);
    }

    @Override
    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    @Override
    public List<Person> getAllLecturers() {
        return personRepository.findAll()
                .stream()
                .filter(p -> p.getRole() == Role.LECTURER)
                .toList();
    }

    @Override
    public List<Person> getAllLecturesFromMetaverse(String metaverseName) {
        return personRepository.findAllByMetaverseName(metaverseName);
    }
}
