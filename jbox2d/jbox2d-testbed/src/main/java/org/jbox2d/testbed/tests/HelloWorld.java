package org.jbox2d.testbed.tests;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class HelloWorld {

	public static void main(String args[]) {
		// create world
		Vec2 gravity = new Vec2(0.0f, -10.0f);
		boolean doSleep = true;
		World world = new World(gravity);

		// body definition
		BodyDef bd = new BodyDef();
		bd.position.set(50, 50);
		bd.type = BodyType.DYNAMIC;

		// body shape
		CircleShape cs = new CircleShape();
		cs.setRadius(0.5f);

		// body fixture
		FixtureDef fd = new FixtureDef();
		fd.shape = cs;
		fd.density = 0.5f;
		fd.friction = 0.3f;
		fd.restitution = 0.5f;

		// create body
		Body body = world.createBody(bd);
		body.createFixture(fd);

		// simulate the world
		float timeStep = 1.0f / 60.0f;
		int velocityIterations = 6;
		int positionIterations = 2;

		for (int i = 0; i < 60; i++) {
			world.step(timeStep, velocityIterations, positionIterations);
			Vec2 position = body.getPosition();
			float angle = body.getAngle();
			System.out.println("Position & Angle ==> " + position + "||" + angle);
		}
	}

}
