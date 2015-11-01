package net.xaviersala.martina.objectes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Martina {

  public static final int VELOCITAT = 200;

  Texture imatge;
  Rectangle posicio;
  Sound so;



  public Martina(Texture image, float x, float y) {
    imatge = image;
    posicio = new Rectangle(x, y, image.getWidth(), imatge.getHeight());
  }

  public float getX() {
    return posicio.getX();
  }

  public float getY() {
    return posicio.getY();
  }

  public Texture getImatge() {
    return imatge;
  }

  public void mou(int x, int y) {
    posicio.setX(posicio.getX() + x);
    posicio.setX(posicio.getY() + y);
  }

  public void mou(float f) {
    posicio.setX(VELOCITAT * f +  posicio.getX());
  }

  public void posicionaA(float x) {
    posicio.setX(x - imatge.getWidth()/2);

  }

  public void destrueix() {
    imatge.dispose();
    if (so!=null) {
      so.dispose();
    }
  }

  public boolean xoca(Rectangle posicio2) {
    if (posicio.overlaps(posicio2)) {
      so.play();
      return true;
    }
    return false;
  }

  public void setSound(String fitxer) {
    so = Gdx.audio.newSound(Gdx.files.internal(fitxer));
  }


}
