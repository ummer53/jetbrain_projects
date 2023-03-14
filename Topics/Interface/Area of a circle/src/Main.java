class Circle implements Measurable {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double area() {
        return Math.PI * radius * radius;
    }
}

// do not change the interface
interface Measurable {
    double area();
}