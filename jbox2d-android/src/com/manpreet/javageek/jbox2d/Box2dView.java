package com.manpreet.javageek.jbox2d;

import java.util.Random;

import org.jbox2d.collision.AABB;
import org.jbox2d.collision.CircleDef;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.View;

public class Box2dView extends View {

	public World world;
	public Body[] bodies;
	public Paint paint;
	public int width, height;
	public AABB worldAABB;
	public float radius = 10;
	private int count = 0;

	public int targetFPS = 40;
	public float timeStep = 10.0f / targetFPS;
	public int iterations = 5;

	public Box2dView(Context context, int width, int height) {
		super(context);
		this.width = width;
		this.height = height;

		// create boundaries
		worldAABB = new AABB();

		Vec2 min = new Vec2(-50, -50);
		Vec2 max = new Vec2(width + 50, height + 50);

		worldAABB.lowerBound.set(min);
		worldAABB.upperBound.set(max);

		// create world
		Vec2 gravity = new Vec2(0.0f, -10.0f);
		boolean doSleep = true;
		world = new World(worldAABB, gravity, doSleep);

		// create boundaries

		// ground
		BodyDef bdg = new BodyDef();
		bdg.position.set(new Vec2(0.0f, -10.0f));
		PolygonDef psg = new PolygonDef();
		psg.setAsBox(width, 10.0f);
		world.createBody(bdg).createShape(psg);

		// top
		BodyDef bdt = new BodyDef();
		bdt.position.set(new Vec2(0.0f, height + 10.0f));
		PolygonDef pst = new PolygonDef();
		pst.setAsBox(width, 10.0f);
		world.createBody(bdt).createShape(pst);

		// left
		BodyDef bdl = new BodyDef();
		bdl.position.set(new Vec2(-10.0f, 0.0f));
		PolygonDef psl = new PolygonDef();
		psl.setAsBox(10.0f, height);
		world.createBody(bdl).createShape(psl);

		// right
		BodyDef bdr = new BodyDef();
		bdr.position.set(new Vec2(width + 10.0f, 0.0f));
		PolygonDef psr = new PolygonDef();
		psr.setAsBox(10.0f, height);
		world.createBody(bdr).createShape(psr);

		// bodies
		bodies = new Body[50];

		// paint object
		paint = new Paint();
		paint.setStyle(Style.FILL);
		paint.setColor(Color.RED);
	}

	public void addBall() {
		// create body
		BodyDef bd = new BodyDef();
		Random rdm = new Random();
		bd.position.set(new Vec2(radius * 2 + rdm.nextInt((int) (width - radius * 4)), radius * 2 + rdm.nextInt((int) (width - radius * 4))));

		// create shape
		CircleDef cd = new CircleDef();
		cd.radius = radius;
		cd.density = 1.0f;
		cd.restitution = 1f;

		bodies[count] = world.createBody(bd);
		bodies[count].createShape(cd);
		bodies[count].setMassFromShapes();

		count += 1;
	}

	public void update() {
		Log.d("box2d", "in view... starting update...");
		world.step(timeStep, 5);
		Log.d("box2d", "in view... step done...");
		postInvalidate();
		Log.d("box2d", "in view... postinvalidate done...");
	}

	protected void onDraw(Canvas canvas) {
		Log.d("box2d", "in view... starting to draw...");
		for (int i = 0; i < count; i++) {
			Log.d("box2d", "in view... starting to draw... count is ... " + i);
			canvas.drawCircle(bodies[i].getPosition().x, height - bodies[i].getPosition().y, radius, paint);
		}
		Log.d("box2d", "in view... done with draw...");
	}
}
