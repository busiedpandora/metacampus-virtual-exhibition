package metacampus2.service;

import metacampus2.model.Lecture;
import metacampus2.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LectureService implements ILectureService {

    @Autowired
    private LectureRepository lectureRepository;

    @Override
    public void addNewLecture(Lecture lecture) {
        lectureRepository.save(lecture);
    }

    @Override
    public Lecture getLectureFromMetaverse(String lectureName, LocalDateTime lectureDateTime, String metaverseName) {
        return lectureRepository.findByNameAndDateTimeAndMetaverseName(lectureName, lectureDateTime, metaverseName);
    }

    @Override
    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }



    /*
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

     */
}


