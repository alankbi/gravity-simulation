public class Transform {
    public double x;
    public double y;
    public double xSpeed;
    public double ySpeed;

    public Transform(double x, double y, double xSpeed, double ySpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed / Simulation.DISTANCE_SCALE;
        this.ySpeed = ySpeed / Simulation.DISTANCE_SCALE;
    }
}
