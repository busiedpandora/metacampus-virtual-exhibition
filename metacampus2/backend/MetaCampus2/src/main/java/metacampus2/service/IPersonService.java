package metacampus2.service;

import metacampus2.model.Person;

import java.util.List;

public interface IPersonService {
    void addNewPerson(Person person);
    Person getPersonFromMetaverse(String personFirstName, String personLastName,
                                  String personCellphone, String metaverseName);
    List<Person> getAllPeople();
    List<Person> getAllLecturers();
}
