using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Lecture : MonoBehaviour
{
    [SerializeField] private string _name;
    [SerializeField] private string _dateTime;
    [SerializeField] private string _classroomNumber;
    [SerializeField] private string _lecturerFirstName;
    [SerializeField] private string _lecturerLastName;

    public string Name { get => _name; set => _name = value; }
    public string DateTime { get => _dateTime; set => _dateTime = value; }
    public string ClassroomNumber { get => _classroomNumber; set => _classroomNumber = value; }
    public string LecturerFirstName { get => _lecturerFirstName; set => _lecturerFirstName = value; }
    public string LecturerLastName { get => _lecturerLastName; set => _lecturerLastName = value; }
}
