package engine.graphics.renderer;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.opengl.ContextAttribs;

public class DisplayManager
{
	private static final ContextAttribs contextAttribs = new ContextAttribs(3, 2).withForwardCompatible(true).withProfileCore(true);
	
	private static DisplayAttribs displayAttribs;
	
	public static void setSettings(int width, int height, int fpsCap, String title) throws RendererException
	{
		if(displayAttribs != null)
		{
			throw new RendererException("Display settings are already defined.");
		}
		
		displayAttribs = new DisplayAttribs(width, height, fpsCap, title);
	}
	
	public static void create() throws RendererException
	{
		if(displayAttribs == null)
		{
			throw new RendererException("Display settings are undefined.");
		}
		
		try
		{
			Display.setDisplayMode(new DisplayMode(displayAttribs.width, displayAttribs.height));
			Display.create(new PixelFormat(), contextAttribs);
			Display.setTitle(getTitle());
		}
		catch(LWJGLException e)
		{
			e.printStackTrace();
		}
		
		GL11.glViewport(0, 0, getWidth(), getHeight());
	}
	
	public static void update()
	{
		Display.sync(getFpsCap());
		Display.update();
	}
	
	public static void close()
	{
		Display.destroy();
	}
	
	public static boolean isCloseRequested()
	{
		return Display.isCloseRequested();
	}
	
	public static int getWidth()
	{
		return displayAttribs.width;
	}
	
	public static int getHeight()
	{
		return displayAttribs.height;
	}
	
	public static float getAspectRatio()
	{
		return (float)getWidth() / (float)getHeight();
	}
	
	public static int getFpsCap()
	{
		return displayAttribs.fpsCap;
	}
	
	public static String getTitle()
	{
		return displayAttribs.title;
	}
	
	private static class DisplayAttribs
	{
		public int width, height, fpsCap;
		public String title;
		
		public DisplayAttribs(int width, int height, int fpsCap, String title)
		{
			this.width = width;
			this.height = height;
			this.fpsCap = fpsCap;
			this.title = title;
		}
	}
}
