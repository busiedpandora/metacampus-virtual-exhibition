using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Event : MonoBehaviour
{
    [SerializeField] private string _name;
    [SerializeField] private string _dateTime;

    public string Name { get => _name; set => _name = value; }
    public string DateTime { get => _dateTime; set => _dateTime = value; }
}
