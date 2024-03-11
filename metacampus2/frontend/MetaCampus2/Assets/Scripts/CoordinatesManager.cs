using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CoordinatesManager : MonoBehaviour
{
    [SerializeField] private GameObject floor0CoordinateObject;
    [SerializeField] private GameObject floor1CoordinateObject;
    [SerializeField] private GameObject floor2CoordinateObject;
    [SerializeField] private GameObject floor3CoordinateObject;
    [SerializeField] private GameObject floor4CoordinateObject;
    [SerializeField] private GameObject floor5CoordinateObject;

    private const float floor0YCoordinate = 2.5f;
    private const float floor1YCoordinate = 6.5f;
    private const float floor2YCoordinate = 9.5f;
    private const float floor3YCoordinate = 12.5f;
    private const float floor4YCoordinate = 15.5f;
    private const float floor5YCoordinate = 18.5f;

    private const int minFloorNumber = 0;
    private const int maxFloorNumber = 5;

    private const int minXCoordinate = -10;
    private const int maxXCoordinate = 10;

    private const int minZCoordinate = 5;
    private const int maxZCoordinate = 10;

    //1° index: floor number, 2° index: x position, 3° index: z position
    private GameObject[][][] coordinates;

    private GameObject resourcesInitializer;



    // Start is called before the first frame update
    void Start()
    {
        InitCoordinates();

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
        int floorCount = maxFloorNumber - minFloorNumber + 1;
        int xSize = maxXCoordinate - minXCoordinate + 1;
        int zSize = maxZCoordinate - minZCoordinate + 1;

        coordinates = new GameObject[floorCount][][];

        for(int floorIndex = 0;  floorIndex < floorCount; floorIndex++)
        {
            coordinates[floorIndex] = new GameObject[xSize][];

            float floorYCoordinate = GetFloorYCoordinate(floorIndex);

            GameObject floorObject = GameObject.Find($"/Campus/{minFloorNumber + floorIndex}");

            for (int x = 0; x < xSize; x++)
            {
                coordinates[floorIndex][x] = new GameObject[zSize];

                for(int z = 0;  z < zSize; z++)
                {
                    Vector3 position = new Vector3(minXCoordinate + x, floorYCoordinate, minZCoordinate + z);
                   
                    GameObject coordinate = Instantiate(GetFloorObject(floorIndex), position, Quaternion.identity);

                    //Set floorObject as parent
                    coordinate.transform.parent = floorObject.transform;

                    coordinates[floorIndex][x][z] = coordinate;
                }
            }
        }
    }

    private float GetFloorYCoordinate(int floorIndex)
    {
        switch (floorIndex)
        {
            case 0:
                return floor0YCoordinate;
            case 1:
                return floor1YCoordinate;
            case 2:
                return floor2YCoordinate;
            case 3:
                return floor3YCoordinate;
            case 4:
                return floor4YCoordinate;
            case 5:
                return floor5YCoordinate;
            default: 
                return 0f;
        }
    }

    private GameObject GetFloorObject(int floorIndex)
    {
        switch (floorIndex)
        {
            case 0:
                return floor0CoordinateObject;
            case 1:
                return floor1CoordinateObject;
            case 2:
                return floor2CoordinateObject;
            case 3:
                return floor3CoordinateObject;
            case 4:
                return floor4CoordinateObject;
            case 5:
                return floor5CoordinateObject;    
            default:
                return floor0CoordinateObject;
        }
    }

    public GameObject GetGameObject(int floorNumber, int xPosition, int zPosition)
    {
        int floorIndex = floorNumber - minFloorNumber;
        int xIndex = xPosition - minXCoordinate;
        int zIndex = zPosition - minZCoordinate;

        if (floorIndex >= 0 && floorIndex < coordinates.Length && 
            xIndex >= 0 && xIndex < coordinates[0].Length &&
            zIndex >= 0 && zIndex < coordinates[0][0].Length)
        {
            return coordinates[floorIndex][xIndex][zIndex];
        }
        else
        {
            Debug.Log("getGameObject: Out of bounds");
            return null;
        }
    }
}
