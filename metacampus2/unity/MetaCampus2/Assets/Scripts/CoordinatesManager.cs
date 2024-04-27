using Newtonsoft.Json;
using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UIElements;

public class CoordinatesManager : MonoBehaviour
{
    [SerializeField] private GameObject coordinateObject;
    [SerializeField] private GameObject edgeObject;

    private HTTPRequest httpRequest;

    private const string coordinatesPath = "/Metaverse/Coordinates";
    private const string edgesPath = "/Metaverse/Edges";

    private int minXCoordinate = 0;
    private int maxXCoordinate = 0;

    private int minYCoordinate = 0;
    private int maxYCoordinate = 0;

    private int minZCoordinate = 0;
    private int maxZCoordinate = 0;

    private Vector3 edge1Position;
    private Vector3 edge2Position;
    private Vector3 edge3Position;

    //1° index: x position, 2° index: y position, 3° index: z position
    private GameObject[][][] coordinates;

    private GameObject resourcesInitializer;


    private void Awake()
    {
        httpRequest = GetComponent<HTTPRequest>();
    }

    // Start is called before the first frame update
    void Start()
    {
        Debug.Log($"Loaded scene for {MetaverseSelectionManager.metaverseNameSelected} with URL {MetaverseSelectionManager.metaverseUrlNameSelected}");

        try
        {
            StartCoroutine(SetUpMetaverse());
        }
        catch (Exception e)
        {
            DebugLog.instance.Log("Exception Occurred", e.Message);
        }
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private IEnumerator SetUpMetaverse()
    {
        string url = $"http://{HTTPInfo.hostAddress}:{HTTPInfo.port}/{HTTPInfo.metaversesPath}/{MetaverseSelectionManager.metaverseUrlNameSelected}";

        string responseData = null;
        yield return StartCoroutine(httpRequest.GetDataFromServer(url, ""));
        responseData = httpRequest.ResponseData;

        if(responseData == null || responseData.Length == 0)
        {
            throw new Exception($"Metaverse with urlName {MetaverseSelectionManager.metaverseUrlNameSelected} not found");
        }

        Debug.Log($"Metaverse: {responseData}");

        var metaverse = JsonConvert.DeserializeObject<MetaverseSerializable>(responseData);

        minXCoordinate = metaverse.minXDimension;
        maxXCoordinate = metaverse.maxXDimension;
        minYCoordinate = metaverse.minYDimension;
        maxYCoordinate = metaverse.maxYDimension;
        minZCoordinate = metaverse.minZDimension;
        maxZCoordinate = metaverse.maxZDimension;

        InitCoordinates();
        InitEdges();

        resourcesInitializer = GameObject.Find("ResourcesInitializer");
        ResourcesManager resourcesManager = resourcesInitializer.GetComponent<ResourcesManager>();
        resourcesManager.StartInitResources();
    }

    private void InitCoordinates()
    {
        //array dimensions
        int xSize = maxXCoordinate - minXCoordinate + 1;
        int ySize = maxYCoordinate - minYCoordinate + 1;
        int zSize = maxZCoordinate - minZCoordinate + 1;

        coordinates = new GameObject[xSize][][];

        GameObject parentObject = GameObject.Find(coordinatesPath);

        for(int x = 0;  x < xSize; x++)
        {
            coordinates[x] = new GameObject[ySize][];

            for (int y = 0; y < ySize; y++)
            {
                coordinates[x][y] = new GameObject[zSize];

                for(int z = 0;  z < zSize; z++)
                {
                    Vector3 position = new Vector3(minXCoordinate + x, minYCoordinate + y, minZCoordinate + z);
                   
                    GameObject coordinate = Instantiate(coordinateObject, position, Quaternion.identity);

                    //Set floorObject as parent
                    coordinate.transform.parent = parentObject.transform;

                    coordinate.GetComponent<MeshRenderer>().enabled = false;

                    coordinates[x][y][z] = coordinate;
                }
            }
        }
    }


    public GameObject GetGameObject(int xPosition, int yPosition, int zPosition)
    {
        int xIndex = xPosition - minXCoordinate;
        int yIndex = yPosition - minYCoordinate;
        int zIndex = zPosition - minZCoordinate;

        if (xIndex >= 0 && xIndex < coordinates.Length && 
            yIndex >= 0 && yIndex < coordinates[0].Length &&
            zIndex >= 0 && zIndex < coordinates[0][0].Length)
        {
            return coordinates[xIndex][yIndex][zIndex];
        }
        else
        {
            Debug.Log("getGameObject: Out of bounds");
            return null;
        }
    }

    private void InitEdges()
    {
        GameObject parentObject = GameObject.Find(edgesPath);

        edge1Position = new Vector3(minXCoordinate, minYCoordinate, minZCoordinate);
        GameObject edge1Instance = Instantiate(edgeObject, edge1Position, Quaternion.identity);
        edge1Instance.transform.parent = parentObject.transform;

        edge2Position = new Vector3(maxXCoordinate, minYCoordinate, maxZCoordinate);
        GameObject edge2Instance = Instantiate(edgeObject, edge2Position, Quaternion.identity);
        edge2Instance.transform.parent = parentObject.transform;

        edge3Position = new Vector3(minXCoordinate, minYCoordinate, maxZCoordinate);
        GameObject edge3Instance = Instantiate(edgeObject, edge3Position, Quaternion.identity);
        edge3Instance.transform.parent = parentObject.transform;
    }
}
