using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ResourcesInfo
{
    //Classroom
    public string ClassroomNumber { get; set; }

    //Office
    public string OfficeNumber { get; set; }

    //Event
    public string EventName { get; set; }
    public DateTime EventDateTime { get; set; }

    //Lecture
    public string LectureName { get; set; }
    public string LectureDateTime { get; set; }

    //Person
    public string PersonFirstName { get; set; }
    public string PersonLastName { get; set; }
    public string PersonRole { get; set; }
    public string PersonCellphone { get; set; }
    public string PersonOfficePhone { get; set; }

    //All
    public Location Location { get; set; }
}
