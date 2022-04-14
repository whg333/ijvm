package com.whg.ijvm.ch09.test.method;

public class Vector3D extends Vector2D {

    protected double z;

    public Vector3D() {
        this(1, 1, 1);
    }

    public Vector3D(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    @Override
    public void multiply(double s) {
        super.multiply(s);
        this.z *= s;
    }

}
