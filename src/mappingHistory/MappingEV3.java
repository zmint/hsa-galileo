package mappingHistory;

import java.util.logging.Level;

import java.util.logging.Logger;

import mapping.MapObject;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class MappingEV3 {

	private final static int SIZE = 20;
	private static boolean drawingIt = true;

	static mapping.MapTestingClass a = new mapping.MapTestingClass();

	public static void main(String[] args) {

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
			// drawRect(20, 40, SIZEX, SIZEY, 23, true);

			int var1 = 0;
			int var2 = -1;

			 System.out.println(a.map3.toString());
			for (int y = a.map3.size() - 1; y >= 0; --y) {
				var2++;
				for (int x = 0; x < a.map3.get(y).size(); x++) {
					System.out.println(a.map3.toString());

					if (a.map3.get(y).get(x) == MapObject.WALL) {
						glColor3f(0.80f, 0.20f, 0.2f);
						drawRect(var1 * (SIZE + 5), var2 * (SIZE + 5), SIZE,
								SIZE, drawingIt);
					} else if (a.map3.get(y).get(x) == MapObject.OBSTACLE) {
						glColor3f(0.80f, 0.20f, 0.98f);
						drawRect(var1 * (SIZE + 5), var2 * (SIZE + 5), SIZE,
								SIZE, drawingIt);
					}
					var1++;
				}
				var1 = 0;
			}

			// if (drawingIt == false)
			// drawingIt = true;
			// else
			// drawingIt = false;
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