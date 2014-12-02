package mapping;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

public class MappingEV3 {
	public static void main(String[] args) {

		initDisplay();
		initGL();
		gameLoop();
		cleanUp();
	}

	private static void gameLoop() {
		// Game Loop
		while (!Display.isCloseRequested()) {
			// first we want to clear everything
			glClear(GL_COLOR_BUFFER_BIT);

			// we need that so the method glTranslatef() is not moving the
			// object around
			glLoadIdentity();

			// color for square r g b = 3f
			glColor3f(0.25f, 0.75f, 0.5f);

			// drawing a rect (x, y, width, height)
			// x, y = position width, height = object-size
			drawRect(56, 56, 32, 256);
			drawRect(56, 56, 448, 32);
			drawRect(0, 0, 48, 48);
			// another rect drawed with rotation
			// drawRect(400, 400, 224, 32, 45);

			// shows the drawing
			Display.update();
		}
	}

	// start-posiion x and y, width and height
	private static void drawRect(float x, float y, float width, float height) {

		// every rect gets his own unique matrix (push = pushing on stack)
		glPushMatrix();
		{
			// (matrix) takes it and shifts it over x, y, z (z = 3D) pixels
			glTranslatef(x, y, 0);

			// what we want to draw (squares)
			glBegin(GL_QUADS);
			{
				// 2 pieces of information and float = 2f
				// moving around by x and y (width and height)
				glVertex2f(0, 0);
				// next point
				glVertex2f(0, height);
				// next point
				glVertex2f(width, height);
				// last point
				glVertex2f(width, 0);
			}
			glEnd();
		}
		glPopMatrix();
	}

	/**
	 * with this method you can draw a rect with rotation but the drawing starts
	 * not from the corner but the centre also it rotates at the centre
	 * 
	 * But I don't think we need that for our project ;) banana
	 * 
	 * If you still want to rotate (but not at the centre) add the
	 * glRotate()-method above like in the method below. You would just need
	 * that.
	 */
	// // start-posiion x and y, width and height, last one is rotation
	// private static void drawRect(float x, float y, float width, float height,
	// float rot) {
	//
	// // every rect gets his own unique matrix (push = pushing on stack)
	// glPushMatrix();
	// {
	// // so it rotates at the centre
	// width /= 2;
	// height /= 2;
	// // (matrix) takes it and shifts it over x, y, z (z = 3D) pixels
	// glTranslatef(x, y, 0);
	//
	// // rotate angle for a 2D object
	// // rotating on the z-axis
	// glRotatef(rot, 0, 0, 1);
	//
	// // what we want to draw (squares)
	// glBegin(GL_QUADS);
	// {
	// // 2 pieces of information and float = 2f
	// // moving around by x and y (width and height)
	// glVertex2f(-width, -height);
	// // next point
	// glVertex2f(-width, height);
	// // next point
	// glVertex2f(width, height);
	// // last point
	// glVertex2f(width, -height);
	// }
	// glEnd();
	// }
	// glPopMatrix();
	// }

	private static void initGL() {
		// call projection-matrix
		glMatrixMode(GL_PROJECTION);
		// clear matrix
		glLoadIdentity();
		// coordinate-system to draw it
		// startpoint, .. , height, .. , -1 = behind camera, last default
		glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
		// changing back to modelview-matrix
		glMatrixMode(GL_MODELVIEW);
		// what color to clear: red, green, blue, transparent
		glClearColor(0, 0, 0, 1);
		// we want 2D and not 3D
		glDisable(GL_DEPTH_TEST);
	}

	private static void cleanUp() {
		// does all the openGL cleanup we need
		Display.destroy();
	}

	private static void initDisplay() {
		// Create display
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
		} catch (LWJGLException ex) {
			Logger.getLogger(MappingEV3.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}
}
