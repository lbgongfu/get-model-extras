package cn.lbgongfu.gdx.graphics.g3d.loader;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

import cn.lbgongfu.gdx.graphics.g3d.ModelExtras;

public abstract class ModelExtrasLoader extends AsynchronousAssetLoader<ModelExtras, ModelExtrasLoader.ModelExtrasParameters> {

	static public class ModelExtrasParameters extends AssetLoaderParameters<ModelExtras> {
	}
	
	ModelExtras modelExtras;
	
	public ModelExtrasLoader(FileHandleResolver resolver) {
		super(resolver);
	}
	
	public abstract ModelExtras loadModelExtras(FileHandle file, ModelExtrasParameters parameters);
	
	@Override
	public void loadAsync(AssetManager manager, String fileName, FileHandle file, ModelExtrasParameters parameter) {
		modelExtras = null;
		modelExtras = loadModelExtras(file, parameter);
	}

	@Override
	public ModelExtras loadSync(AssetManager manager, String fileName, FileHandle file,
			ModelExtrasParameters parameter) {
		ModelExtras modelExtras = this.modelExtras;
		this.modelExtras = null;
		return modelExtras;
	}

	@Override
	public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, ModelExtrasParameters parameter) {
		return null;
	}
	
}
