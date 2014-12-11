package mapping;

import java.util.ArrayList;

public class MapTestingClass {
	public static Map map = new Map();

	public MapTestingClass() {
		Map map2 = new Map();
		MapObject wall = MapObject.WALL;
		MapObject empty = MapObject.EMPTY;
		MapObject obstacle = MapObject.OBSTACLE;

		MapObject[] mapObjects = new MapObject[20];
		ArrayList<MapObject> mapObjectsList = new ArrayList<MapObject>();

		for (int i = 20; i > 0; i--) {
			mapObjectsList.add(wall);
		}
		for (int i = 0; i < mapObjects.length; i++) {
			mapObjects[i] = wall;
		}

		mapObjects[3] = empty;
		mapObjects[9] = obstacle;

		map.buildMapFromOneDimensionalArray(mapObjects, 4);
		map2.buildMapFromOneDimensionalArrayList(mapObjectsList, 4);

//		 System.out.println(map.toString());
		// System.out.println(map2.toString());

	}
}
