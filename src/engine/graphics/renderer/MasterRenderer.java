package engine.graphics.renderer;

import engine.core.entities.Entity;
import engine.graphics.models.TexturedModel;
import engine.graphics.shaders.StaticShader;
import org.lwjgl.opengl.GL11;
import java.util.ArrayList;
import java.util.HashMap;

public class MasterRenderer
{
	private StaticShader shader;
	private EntityRenderer entityRenderer;
	
	private HashMap<TexturedModel, ArrayList<Entity>> entities;
	
	public MasterRenderer()
	{
		shader = new StaticShader();
		entityRenderer = new EntityRenderer(shader);
		
		entities = new HashMap<>();
	}
	
	public void render()
	{
		prepare();
		
		shader.start();
		entityRenderer.render(entities);
	}
	
	public void processEntity(Entity entity)
	{
		TexturedModel model = entity.getModel();
		
		ArrayList<Entity> batch = entities.get(model);
		
		if(batch == null) 
		{
			ArrayList<Entity> newBatch = new ArrayList<>();
			
			newBatch.add(entity);
			
			entities.put(model, newBatch);
		}
		else
		{
			batch.add(entity);
		}
	}
	
	public void clear()
	{
		shader.clear();
	}
	
	private void prepare()
	{
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0.2f, 0.2f, 0.2f, 1f);
	}
}
