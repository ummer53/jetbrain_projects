class Shape {
    public double area() {
        return 0;
    }
}

class Triangle extends Shape {
    double height;
    double base;

    // override the method here
    public double area(){
        return 1.0/2*height*base;
    }
}

class Circle extends Shape {
    double radius;

    // override the method here
    public double area(){
        return Math.PI*radius*radius;
    }

}

class Square extends Shape {
    double side;

    // override the method here
    public double area(){
        return side*side;
    }
}

class Rectangle extends Shape {
    double width;
    double height;

    // override the method here
    public double area(){
        return width*height;
    }
}