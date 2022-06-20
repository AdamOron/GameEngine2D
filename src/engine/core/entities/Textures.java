package engine.core.entities;

import engine.graphics.renderer.Loader;
import engine.graphics.textures.ModelTexture;

public class Textures
{
	public static final ModelTexture WHITE	= new ModelTexture(Loader.getInstance().loadTexture("white"));
	public static final ModelTexture BLACK	= new ModelTexture(Loader.getInstance().loadTexture("black"));
	public static final ModelTexture RED	= new ModelTexture(Loader.getInstance().loadTexture("red"));
	public static final ModelTexture GREEN	= new ModelTexture(Loader.getInstance().loadTexture("green"));
	public static final ModelTexture BLUE	= new ModelTexture(Loader.getInstance().loadTexture("blue"));
}
