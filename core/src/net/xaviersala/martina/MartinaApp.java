package net.xaviersala.martina;

import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import net.xaviersala.martina.objectes.Martina;
import net.xaviersala.martina.objectes.Xocolata;
import net.xaviersala.martina.utils.ImageManager;

public class MartinaApp extends ApplicationAdapter {

  private static final int ALTPANTALLA = 480;
  private static final int AMPLEPANTALLA = 800;
  private static final String[] CARAMELS = { "xocolata", "caramel1", "caramel2", "caramel3" };

  SpriteBatch batch;
  private OrthographicCamera camera;

  Rectangle pantalla;
  ImageManager imatges;
  Array<Xocolata> xocolates;
  Random aleatori;

  Martina martina;
  private long lastDropTime;

  @Override
  public void create () {
    batch = new SpriteBatch();
    camera = new OrthographicCamera();
    pantalla = new Rectangle(0, 0, AMPLEPANTALLA, ALTPANTALLA);
    camera.setToOrtho(false, pantalla.getWidth(), pantalla.getHeight());


    // Gestor per reutilitzar les imatges
    imatges = new ImageManager();

    for(String nom: CARAMELS) {
      imatges.afegirImatge(nom, new Texture(nom + ".png"));
    }

    martina = new Martina(new Texture("martina.png"), 0, 0);
    martina.setSound("nyam.wav");
    xocolates = new Array<Xocolata>();

    crearXocolata();

  }

  /**
   * Crear una xocolata en una posició determinada.
   */
  private void crearXocolata() {
    xocolates.add(new Xocolata(imatges.obtenirImatge(CARAMELS[MathUtils.random(CARAMELS.length-1)]),
        MathUtils.random(0, pantalla.getWidth()), pantalla.getHeight()-10));
    lastDropTime = TimeUtils.nanoTime();

  }

  @Override
  public void render () {
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.begin();
    batch.draw(martina.getImatge(), martina.getX(), martina.getY());
    for(Xocolata xocolata: xocolates) {
      batch.draw(xocolata.getImatge(), xocolata.getX(), xocolata.getY());
    }
    batch.end();

    Iterator<Xocolata> iter = xocolates.iterator();
    while(iter.hasNext()) {
       Xocolata xocolata = iter.next();
       xocolata.cau(Gdx.graphics.getDeltaTime());
       if (!pantalla.overlaps(xocolata.getPosicio())) {
         // Fer fressa?
         iter.remove();
       } else if (martina.xoca(xocolata.getPosicio())) {
         // Fer fressa?
         iter.remove();
       }
       if(TimeUtils.nanoTime() - lastDropTime > 1000000000) crearXocolata();

    }



    if(Gdx.input.isTouched()) {
      Vector3 touchPos = new Vector3();
      touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
      camera.unproject(touchPos);
      martina.posicionaA(touchPos.x);
   }
   if(Gdx.input.isKeyPressed(Keys.LEFT)) martina.mou(-1 * Gdx.graphics.getDeltaTime());
   if(Gdx.input.isKeyPressed(Keys.RIGHT))martina.mou(Gdx.graphics.getDeltaTime());
  }

  public void dispose() {
    imatges.destrueix();
    martina.destrueix();
    batch.dispose();
  }
}
