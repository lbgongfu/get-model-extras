package cn.lbgongfu.gdx.graphics.g3d.model;

import com.badlogic.gdx.math.Vector3;

public class CameraData {
	public final Vector3 position = new Vector3();
	public final Vector3 lookAt = new Vector3(0, 0, -1);
	public final Vector3 direction = new Vector3(0, 0, -1);
	public final Vector3 up = new Vector3(0, 1, 0);
	public float near = 1;
	public float far = 300;
	public float fieldOfView = 67;
	public float aspectRatio = 1.33333f;
	
	public CameraData(){}

	@Override
	public String toString() {
		return String.format("%s: pos%s, lookAt%s, dir%s, up%s, near: %f, far: %f, fieldOfView: %f, aspectRatio: %f", 
				super.toString(), position, lookAt, direction, up, near, far, fieldOfView, aspectRatio);
	}
	
}
