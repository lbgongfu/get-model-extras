package cn.lbgongfu.gdx.graphics.g3d;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.Array;

public class ModelExtras {
	public final short[] version = new short[2];
	public final Array<Camera> cameras = new Array<Camera>();
	
	public void addCameras(Array<Camera> cameras)
	{
		this.cameras.addAll(cameras);
	}
}
