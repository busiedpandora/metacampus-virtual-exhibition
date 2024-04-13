/*
package metacampus2.service;

import metacampus2.model.Person;
import metacampus2.model.Role;
import metacampus2.repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements IPersonService {
    private IPersonRepository IPersonRepository;


    @Autowired
    public PersonService(IPersonRepository IPersonRepository) {
        this.IPersonRepository = IPersonRepository;
    }

    @Override
    public void addNewPerson(Person person) {
        IPersonRepository.save(person);
    }

    @Override
    public Person getPersonFromMetaverse(String personFirstName, String personLastName, String personCellphone, String metaverseName) {
        return IPersonRepository.findByFirstNameAndLastNameAndCellphoneAndMetaverseName(personFirstName,
                personLastName, personCellphone, metaverseName);
    }

    @Override
    public List<Person> getAllPeople() {
        return IPersonRepository.findAll();
    }

    @Override
    public List<Person> getAllLecturers() {
        return IPersonRepository.findAll()
                .stream()
                .filter(p -> p.getRole() == Role.LECTURER)
                .toList();
    }

    public List<Person> getAllPeopleFromMetaverse(String metaverseName) {
        return IPersonRepository.findAllByMetaverseName(metaverseName);

    }
}
*/
