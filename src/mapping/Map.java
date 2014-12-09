package mapping;
import java.util.ArrayList;

//Ziad Nörpel:
//Map class which inherits from ArrayList
//this class is basically a two dimensional ArrayList, a ArrayList containing
//other ArrayLists
//it should store all the elements which build alltogether the map which the robot
//is exploring
public class Map extends ArrayList<ArrayList<MapObject>>{
	
	private ArrayList<ArrayList<MapObject>> arrayList = new ArrayList<ArrayList<MapObject>>();
	
	//this function initializes empty inner Arrays(rows) of the map
	//depending on the given numberOfRows value
	public void initMap(int numberOfRows){
		for(int i=0;i<numberOfRows;i++){
			this.add(new ArrayList<MapObject>());
		}
		
	};

	//this function builds a map from a one dimensional ArrayList and the given mapWidth
	//the map width regulates how many elements can be placed in one row
	public void buildMapFromOneDimensionalArrayList(ArrayList<MapObject>mapObjects, int mapWidth){

		int row=0;
		int cumulatedRows=mapWidth;
		this.add(new ArrayList<MapObject>());
		for(int i=0; i<mapObjects.size();i++){
			
		    if(i<cumulatedRows){
		    	
		    	this.get(row).add(mapObjects.get(i));
		    	
		    }else{
		    	row+=1;
		    	this.add(new ArrayList<MapObject>());
		    	this.get(row).add(mapObjects.get(i));
		    	cumulatedRows+=mapWidth;
		    }
		}
	}
	//this function builds a map from a one dimensional Array and the given mapWidth
	//the map width regulates how many elements can be placed in one row
	public void buildMapFromOneDimensionalArray(MapObject[]mapObjects, int mapWidth){

		int row=0;
		int cumulatedRows=mapWidth;
		this.add(new ArrayList<MapObject>());
		for(int i=0; i<mapObjects.length;i++){
			
		    if(i<cumulatedRows){
		    	
		    	this.get(row).add(mapObjects[i]);
		    	
		    }else{
		    	row+=1;
		    	this.add(new ArrayList<MapObject>());
		    	this.get(row).add(mapObjects[i]);
		    	cumulatedRows+=mapWidth;
		    }
		}
	}
	
	


}
