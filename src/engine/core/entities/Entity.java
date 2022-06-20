package engine.core.entities;

import engine.core.math.Transform;
import engine.graphics.models.TexturedModel;

public class Entity extends GameObject
{
	private TexturedModel model;
	private Transform transform;
	
	public Entity(TexturedModel model, Transform transform)
	{
		this.model = model;
		this.transform = transform;
	}

	public TexturedModel getModel()
	{
		return model;
	}

	public void setModel(TexturedModel model)
	{
		this.model = model;
	}

	public Transform getTransform()
	{
		return transform;
	}

	public void setTransform(Transform transform)
	{
		this.transform = transform;
	}
}
