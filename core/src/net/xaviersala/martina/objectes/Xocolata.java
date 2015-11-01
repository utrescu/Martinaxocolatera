package net.xaviersala.martina.objectes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Xocolata {
  private static final int VELOCITAT = 200;
  public Texture imatge;
  public Rectangle posicio;

  public Xocolata(Texture image, float x, float y) {
    imatge = image;
    posicio = new Rectangle(x, y, imatge.getWidth(), imatge.getHeight());
  }

  public Texture getImatge() {
    return imatge;
  }

  public float getX() {
    return posicio.getX();
  }

  public float getY() {
    return posicio.getY();
  }

  public void cau(float temps) {

    posicio.setY(posicio.getY() - VELOCITAT * temps);

  }

  public Rectangle getPosicio() {
    return posicio;
  }
}
