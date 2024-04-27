using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System.IO;

public class HTTPInfo
{
    public static readonly string hostAddress = "localhost";
    public static readonly string port = "8080";
    public static readonly string metaversesPath = "metaverses";
    public static readonly string metaversesListPath = "metaversesList";
    public static readonly string spacesPath = "spaces";


    static HTTPInfo()
    {
        try
        {
            string filePath = "Assets/Resources/host_address.txt";

            if (File.Exists(filePath))
            {
                string[] lines = File.ReadAllLines(filePath);

                foreach (string line in lines)
                {
                    if (line.StartsWith("host-address="))
                    {
                        hostAddress = line.Substring("host-address=".Length).Trim();
                        Debug.Log($"Using host address: {hostAddress}");
                        return;
                    }
                }
            }

            hostAddress = "localhost";
            Debug.Log("Using localhost as host address");
        }
        catch (Exception ex)
        {
            hostAddress = "localhost";
            Debug.LogError($"Error reading hostname.txt: {ex.Message}");
        }
    }
}
