package mapping;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class MappingEV3 {

	MappingEV3Input test = new MappingEV3Input();
	private final static int SIZEX = 10;
	private final static int SIZEY = 10;
	private static boolean hi = true;

	static MapTestingClass a = new MapTestingClass();

	public static void main(String[] args) {
		MappingEV3Input.init();

		initDisplay();
		initGL();
		gameLoop();
		cleanUp();
	}

	private static void gameLoop() {
		while (!Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT);
			glLoadIdentity();
			// glColor3f(0.80f, 0.20f, 0.2f);

			// drawRect(400, 400, 224, 32, 22);
			// drawRect(0, 0, SIZEX, SIZEY, true);
			// drawRect(20, 40, SIZEX, SIZEY, true);

			// System.out.println(a.map.toString());
			// a.map.toString();
			// if (MapObject == WALL){
			//
			// }
			for (int y = 0; y < a.map.size(); y++) {
				for (int x = 0; x < a.map.get(y).size(); x++) {
					// System.out.println(a.map.get(0).size());
					// System.out.println(a.map.get(i).get(j));

					if (a.map.get(y).get(x) == MapObject.WALL) {
						glColor3f(0.80f, 0.20f, 0.2f);
						drawRect(y * 15, x * 15, SIZEX, SIZEY, hi);
					} else if (a.map.get(y).get(x) == MapObject.OBSTACLE) {
						glColor3f(0.80f, 0.20f, 0.98f);
						drawRect(y * 15, x * 15, SIZEX, SIZEY, hi);
					}
				}
			}
			// int i = 0;
			// int j = 0;
			//
			// int k = 40;
			// int l = 20;
			// for (ArrayList<MapObject> line : a.map) {
			// System.out.println("Wert: " + line);
			// for (MapObject o : line) {
			// if (o == MapObject.WALL) {
			// drawRect(i, j, SIZEX, SIZEY, hi);
			// i += 20;
			// j += 20;
			// } else if (o == MapObject.OBSTACLE) {
			// drawRect(k, l, SIZEX, SIZEY, hi);
			// k += 20;
			// l += 20;
			// }
			// }
			// }
			// drawRect(MappingEV3Input.a[0][0], MappingEV3Input.a[0][1], SIZEX,
			// SIZEY, hi);

			// for (int i = 0; i < 4; i++) {
			// for (int j = 0; j < 4; j++) {
			// drawRect(a.map[i][], a.map[][j], SIZEX, SIZEY, hi);
			// }
			//
			// }
			// if (hi == false)
			// hi = true;
			// else
			// hi = false;
			Display.update();
		}
	}

	private static void drawRect(float x, float y, float width, float height,
			boolean banana) {
		drawRect(x, y, width, height, 0, banana);
	}

	private static void drawRect(float x, float y, float width, float height,
			float rot, boolean banana) {
		if (banana) {
			glPushMatrix();
			{
				glTranslatef(x, y, 0);
				glRotatef(rot, 0, 0, 1);

				glBegin(GL_QUADS);
				{
					glVertex2f(0, 0);
					glVertex2f(0, height);
					glVertex2f(width, height);
					glVertex2f(width, 0);
				}
				glEnd();
			}
			glPopMatrix();
		}
	}

	private static void initGL() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
		glMatrixMode(GL_MODELVIEW);

		glClearColor(0, 0, 0, 1);

		glDisable(GL_DEPTH_TEST);
	}

	private static void cleanUp() {
		Display.destroy();
	}

	private static void initDisplay() {

		try {
			Display.setDisplayMode(new DisplayMode(800, 800));
			Display.create();
		} catch (LWJGLException ex) {
			Logger.getLogger(MappingEV3.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}
}