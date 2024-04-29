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
            TextAsset textAsset = Resources.Load<TextAsset>("host_address");

            if (textAsset != null)
            {
                string[] lines = textAsset.text.Split("\n");

                foreach (string line in lines)
                {
                    if (line.StartsWith("host-address="))
                    {
                        //read host address file text file
                        hostAddress = line.Substring("host-address=".Length).Trim();
                        //Debug.Log($"found host address in resource file: set host address: {hostAddress}");
                        //DebugLog.instance.Log("found host address in resource file: ", $"set host address: {hostAddress}");
                        return;
                    }
                }
            }

            //set a default host address if the resource file cannot be found
            hostAddress = "localhost";
            Debug.Log($"Cannot load host address file from resources: using host address: {hostAddress}");
            DebugLog.instance.Log("Cannot load host address file from resources: ", $"using host address: {hostAddress}");
        }
        catch (Exception ex)
        {
            //set a default host address if the resource file cannot be found
            hostAddress = "localhost";
            Debug.Log($"Cannot load host address file from resources: using host address: {hostAddress}");
            DebugLog.instance.Log("Cannot load host address file from resources: ", $"using host address: {hostAddress}");
        }
    }
}
