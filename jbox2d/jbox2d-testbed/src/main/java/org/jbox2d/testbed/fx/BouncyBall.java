package org.jbox2d.testbed.fx;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BouncyBall {

	// javafx ui for ball
	public Node node;

	// x & y position of the ball
	private float posX;
	private float posY;

	// ball radius (px)
	private int radius;

	private Color color;

	public BouncyBall(float posX, float posY, int radius, Color color) {
		this.posX = posX;
		this.posY = posY;
		this.radius = radius;
		this.color = color;
		node = create();
	}

	// create ball
	private Node create() {
		// creating a ball i.e. a circle on the javafx scene
		Circle ball = new Circle();
		ball.setRadius(radius);
		ball.setFill(color);

		ball.setLayoutX(Utility.toPixelPosX(posX));
		ball.setLayoutY(Utility.toPixelPosY(posY));

		// create body definition for the ball
		BodyDef bd = new BodyDef();
		bd.type = BodyType.DYNAMIC;
		bd.position.set(posX, posY);

		// create shape
		CircleShape cs = new CircleShape();
		cs.m_radius = radius * 0.1f;

		// create fixture definition
		FixtureDef fd = new FixtureDef();
		fd.shape = cs;
		fd.density = 0.6f;
		fd.friction = 0.3f;
		fd.restitution = 0.8f;

		Body body = Utility.world.createBody(bd);
		body.createFixture(fd);
		ball.setUserData(body);

		return ball;
	}
}
