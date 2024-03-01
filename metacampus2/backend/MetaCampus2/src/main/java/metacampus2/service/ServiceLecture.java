package metacampus2.service;

import metacampus2.model.Lecture;
import metacampus2.model.Lecturer;
import metacampus2.model.Metaverse;
import metacampus2.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceLecture implements IServiceLecture {

    @Autowired
    private LectureRepository lectureRepository;

    @Override
    public Lecture addLectureByOthers(Lecture lecture, Lecturer lecturer, Metaverse metaverse) {

        lecturer.getLectureClassroom().add(lecture);
        metaverse.getLectures().add(lecture);

        lecture.setLecturer(lecturer);
        lecture.setMetaverseLecture(metaverse);

        return lectureRepository.save(lecture);
    }

    @Override
    public Optional<Lecture> findLecture(String id) {
        return lectureRepository.findById(id);
    }

    @Override
    public Optional<Lecture> updateLecture(Lecture lecture, String id) {

        Optional<Lecture> checkLecture = lectureRepository.findById(id);

        if(checkLecture.isPresent()){
            if(lecture.getLocalDateTime() != null){
                checkLecture.get().setLocalDateTime(lecture.getLocalDateTime());
            }

            lectureRepository.save(checkLecture.get());

            return checkLecture;
        }

        return Optional.empty();
    }

    @Override
    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }
}
