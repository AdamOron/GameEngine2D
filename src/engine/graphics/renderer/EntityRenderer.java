package engine.graphics.renderer;

import java.util.ArrayList;
import java.util.HashMap;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import engine.graphics.models.RawModel;
import engine.graphics.models.TexturedModel;
import engine.graphics.shaders.StaticShader;
import engine.core.entities.Entity;
import engine.core.math.Mathf;

public class EntityRenderer
{
	private StaticShader shader;
	
	public EntityRenderer(StaticShader shader)
	{
		this.shader = shader;
	}
	
	public void render(HashMap<TexturedModel, ArrayList<Entity>> entities)
	{
		for(TexturedModel model : entities.keySet())
		{
			prepareTexturedModel(model);
			
			ArrayList<Entity> batch = entities.get(model);
			
			for(Entity entity : batch)
			{
				prepareInstance(entity);
				
				GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
			}
			
			unbindTexturedModel();
		}
	}
	
	private void prepareInstance(Entity entity)
	{
		Matrix4f transformationMatrix = Mathf.createTransformationMatrix(entity.getTransform());
		shader.loadTransformationMatrix(transformationMatrix);
	}
	
	private void prepareTexturedModel(TexturedModel texturedModel)
	{
		RawModel rawModel = texturedModel.getRawModel();
		
		GL30.glBindVertexArray(rawModel.getVaoId());
		
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getTexture().getId());
	}
	
	private void unbindTexturedModel()
	{
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		
		GL30.glBindVertexArray(0);
	}
}
