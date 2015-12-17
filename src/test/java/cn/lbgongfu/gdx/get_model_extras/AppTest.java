package cn.lbgongfu.gdx.get_model_extras;

import org.junit.Test;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.utils.JsonReader;

import cn.lbgongfu.gdx.graphics.g3d.ModelExtras;
import cn.lbgongfu.gdx.graphics.g3d.loader.G3dModelExtrasLoader;
import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	
	@Test
	public void testLoad()
	{
		AssetManager assets = new AssetManager();
		assets.setLoader(ModelExtras.class, new G3dModelExtrasLoader(new JsonReader(), new InternalFileHandleResolver()));
		assets.load("simple_scene_max_plane.extras.g3dj", ModelExtras.class);
		try
		{
			assets.finishLoading();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		assets.dispose();
	}
}
