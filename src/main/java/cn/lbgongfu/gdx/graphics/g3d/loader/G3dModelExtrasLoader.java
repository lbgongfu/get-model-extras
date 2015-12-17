package cn.lbgongfu.gdx.graphics.g3d.loader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BaseJsonReader;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.JsonValue;

import cn.lbgongfu.gdx.graphics.g3d.ModelExtras;
import cn.lbgongfu.gdx.graphics.g3d.model.CameraData;

public class G3dModelExtrasLoader extends ModelExtrasLoader {
	public static final short VERSION_HI = 0;
	public static final short VERSION_LO = 1;
	protected final BaseJsonReader reader;

	public G3dModelExtrasLoader (final BaseJsonReader reader) {
		this(reader, null);
	}

	public G3dModelExtrasLoader (BaseJsonReader reader, FileHandleResolver resolver) {
		super(resolver);
		this.reader = reader;
	}

	@Override
	public ModelExtras loadModelExtras(FileHandle file, ModelExtrasParameters parameters) {
		return parseModelExtras(file);
	}

	private ModelExtras parseModelExtras(FileHandle file) {
		JsonValue json = reader.parse(file);
		ModelExtras modelExtras = new ModelExtras();
		JsonValue version = json.require("version");
		modelExtras.version[0] = version.getShort(0);
		modelExtras.version[1] = version.getShort(1);
		if (modelExtras.version[0] != VERSION_HI || modelExtras.version[1] != VERSION_LO)
			throw new GdxRuntimeException("ModelExtras version not supported");

		parseCameras(modelExtras, json);
		return modelExtras;
	}

	private void parseCameras(ModelExtras modelExtras, JsonValue json) {
		Array<Camera> cameras = new Array<Camera>();
		JsonValue jsonValue = json.get("cameraDatas");
		if (jsonValue != null) {
			cameras.ensureCapacity(jsonValue.size);
			for (JsonValue camera = jsonValue.child; camera != null; camera = camera.next) {
				CameraData cameraData = new CameraData();
				JsonValue position = camera.require("position");
				cameraData.position.set(position.getFloat(0), position.getFloat(1), position.getFloat(2));
				JsonValue lookAt = camera.require("lookAt");
				cameraData.lookAt.set(lookAt.getFloat(0), lookAt.getFloat(1), lookAt.getFloat(2));
				JsonValue up = camera.require("up");
				cameraData.up.set(up.getFloat(0), up.getFloat(1), up.getFloat(2));
				cameraData.near = camera.require("near").asFloat();
				cameraData.far = camera.require("far").asFloat();
				cameraData.fieldOfView = camera.require("fieldOfView").asFloat();
				cameraData.aspectRatio = camera.require("aspectRatio").asFloat();
				cameras.add(createCamera(cameraData));
			}
		}
		modelExtras.addCameras(cameras);
	}

	private Camera createCamera(CameraData cameraData) {
		float screenAspectRatio = (float)Gdx.graphics.getWidth() / (float)Gdx.graphics.getHeight();
		float viewportWidth = 0;
		float viewportHeight = 0;
		if (screenAspectRatio > cameraData.aspectRatio)
		{
			viewportHeight = Gdx.graphics.getHeight();
			viewportWidth = viewportHeight * cameraData.aspectRatio;
		}
		else
		{
			viewportWidth = Gdx.graphics.getWidth();
			viewportHeight = viewportWidth / cameraData.aspectRatio;
		}
		PerspectiveCamera camera = new PerspectiveCamera(cameraData.fieldOfView, viewportWidth, viewportHeight);
		camera.position.set(cameraData.position);
		camera.up.set(cameraData.up);
		camera.near = cameraData.near;
		camera.far = cameraData.far;
		camera.lookAt(cameraData.lookAt);
		return camera;
	}

}
