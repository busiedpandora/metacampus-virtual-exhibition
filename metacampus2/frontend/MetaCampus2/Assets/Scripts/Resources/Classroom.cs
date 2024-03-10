using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Classroom : Resource
{
    [SerializeField] private string _number;

    public string Number { get => _number; set => _number = value; }
}