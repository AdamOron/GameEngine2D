package engine.core.physics;

import engine.core.entities.GameObject;
import engine.core.entities.Rect;

public abstract class Collider extends GameObject
{
	public abstract boolean accept(Collider collider);
	
	public abstract boolean collides(Collider.RectCollider collider);
	
	public static class RectCollider extends Collider
	{
		private Rect rect;
		
		public RectCollider(Rect rect)
		{
			this.rect = rect;
		}

		@Override
		public boolean collides(RectCollider collider)
		{
			float aRightEdge = rect.transform.pos.x + rect.width;
			float aLeftEdge = rect.transform.pos.x - rect.width;
			float aTopEdge = rect.transform.pos.y + rect.height;
			float aBottomEdge = rect.transform.pos.y - rect.height;
			
			Rect rect0 = collider.rect;
			
			float bRightEdge = rect0.transform.pos.x + rect0.width;
			float bLeftEdge = rect0.transform.pos.x - rect0.width;
			float bTopEdge = rect0.transform.pos.y + rect0.height;
			float bBottomEdge = rect0.transform.pos.y - rect0.height;
			
			return	aRightEdge >= bLeftEdge &&
					aLeftEdge <= bRightEdge &&
					aTopEdge >= bBottomEdge &&
					aBottomEdge <= bTopEdge;
		}

		@Override
		public boolean accept(Collider collider)
		{
			return collider.collides(this);
		}
	}
}
