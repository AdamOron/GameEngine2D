package engine.core;

import java.util.ArrayList;
import engine.core.entities.Entity;
import engine.core.physics.Collider;
import engine.core.physics.PhysicsScript;
import engine.core.scripts.Behavior;
import engine.graphics.renderer.*;

public abstract class Engine
{
	private Loader loader;
	private MasterRenderer renderer;
	
	private ArrayList<Entity> entites;
	private ArrayList<Behavior> scripts;
	private ArrayList<PhysicsScript> physicsScripts;
	
	public void run()
	{
		initDisplay();
		initEngineVariables();
		
		prepare();
		
		onCreates();
		
		while(!DisplayManager.isCloseRequested())
		{
			applyPhysics(); // Physics update comes before others
			
			onUpdates();
			
			renderEntites();
			
			DisplayManager.update();
		}
		
		onDestroys();
		
		closeEngine();
	}

	protected void appendEntity(Entity entity)
	{
		entites.add(entity);
	}
	
	protected void appendScript(Behavior script)
	{
		scripts.add(script);
	}
	
	protected void usePhysics(Entity entity, Collider collider)
	{
		physicsScripts.add(new PhysicsScript(entity, collider));
	}
	
	protected abstract void prepare();
	
	private void applyPhysics()
	{
		for(PhysicsScript script : physicsScripts)
		{
			script.onUpdate();
		}
	}
	
	private void onCreates()
	{
		for(Behavior script : scripts)
		{
			script.onCreate();
		}
	}
	
	private void onUpdates()
	{
		for(Behavior script : scripts)
		{
			script.onUpdate();
		}
	}
	
	private void onDestroys()
	{
		for(Behavior script : scripts)
		{
			script.onDestory();
		}
	}
	
	private void renderEntites()
	{
		for(Entity entity : entites)
		{
			renderer.processEntity(entity);
		}
		
		renderer.render();
	}
	
	private void closeEngine()
	{
		loader.clear();
		DisplayManager.close();
	}
	
	private void initEngineVariables()
	{
		loader = Loader.getInstance();
		renderer = new MasterRenderer();
		
		entites = new ArrayList<>();
		scripts = new ArrayList<>();
		physicsScripts = new ArrayList<>();
	}
	
	protected void initDisplay()
	{
		try
		{
			DisplayManager.setSettings(1280, 720, 120, "GameEngine2D");
			DisplayManager.create();
		}
		catch (RendererException e)
		{
			e.printStackTrace();
		}
	}
}
