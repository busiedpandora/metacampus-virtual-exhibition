using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UIElements;

public class CoordinatesManager : MonoBehaviour
{
    [SerializeField] private GameObject coordinateObject;
    [SerializeField] private GameObject edgeObject;

    private const string coordinatesPath = "/Campus/Coordinates";
    private const string edgesPath = "/Campus/Edges";

    private const int minXCoordinate = -30;
    private const int maxXCoordinate = 30;

    private const int minYCoordinate = 0;
    private const int maxYCoordinate = 5;

    private const int minZCoordinate = -30;
    private const int maxZCoordinate = 30;

    private readonly Vector3 edge1Position = new Vector3(30, 0, 20);
    private readonly Vector3 edge2Position = new Vector3(30, 0, -20);
    private readonly Vector3 edge3Position = new Vector3(-30, 0, 20);

    //1° index: x position, 2° index: y position, 3° index: z position
    private GameObject[][][] coordinates;

    private GameObject resourcesInitializer;



    // Start is called before the first frame update
    void Start()
    {
        InitCoordinates();
        InitEdges();

        resourcesInitializer = GameObject.Find("ResourcesInitializer");
        ResourcesManager resourcesManager = resourcesInitializer.GetComponent<ResourcesManager>();
        resourcesManager.StartInitResources();
    }

    // Update is called once per frame
    void Update()
    {
        
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

        GameObject edge1Instance = Instantiate(edgeObject, edge1Position, Quaternion.identity);
        edge1Instance.transform.parent = parentObject.transform;

        GameObject edge2Instance = Instantiate(edgeObject, edge2Position, Quaternion.identity);
        edge2Instance.transform.parent = parentObject.transform;

        GameObject edge3Instance = Instantiate(edgeObject, edge3Position, Quaternion.identity);
        edge3Instance.transform.parent = parentObject.transform;
    }
}
