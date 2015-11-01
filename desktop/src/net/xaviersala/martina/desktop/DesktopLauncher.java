package net.xaviersala.martina.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.xaviersala.martina.MartinaApp;

public class DesktopLauncher {
  public static void main (String[] arg) {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.title = "Martina";
    config.width = 800;
    config.height = 480;
    new LwjglApplication(new MartinaApp(), config);
  }
}
