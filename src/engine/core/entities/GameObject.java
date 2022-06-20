package engine.core.entities;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class GameObject
{
	private static HashMap<Integer, ArrayList<GameObject>> layers = new HashMap<>(); 
	
	public static final int UNDEFINED = -1;
	
	public int tag;
	private int layer;
	
	public GameObject(int tag, int layer)
	{
		this.tag = tag;
		this.layer = layer;
	}
	
	public GameObject()
	{
		this.tag = UNDEFINED;
		this.layer = UNDEFINED;
	}

	public int getTag()
	{
		return tag;
	}
	
	public void setTag(int tag)
	{
		this.tag = tag;
	}
	
	public int getLayer()
	{
		return layer;
	}
	
	public void setLayer(int layer)
	{
		ArrayList<GameObject> layerObjects = layers.get(layer);
		
		if(layerObjects == null)
		{
			ArrayList<GameObject> newLayer = new ArrayList<>();
			
			newLayer.add(this);
			
			layers.put(layer, newLayer);
		}
		else
		{
			layerObjects.add(this);
		}
	}
	
	public static ArrayList<GameObject> getLayerObjects(int layer)
	{
		return layers.get(layer);
	}
}
