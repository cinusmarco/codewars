package ch.cinus.kata.six_kyu.bouncingball;

public class BouncingBall {

  public static int bouncingBall(double h, double bounce, double window) {
    // check for conditions
    if (h <= 0 || window >= h || bounce < 0 || bounce >= 1) {
      return -1;
    }
    return doBouncingBall(h, bounce, window);
  }

  private static int doBouncingBall(double h, double bounce, double window) {
    if (h <= window) {
      return 0;
    }
    int bounces = 1; // the ball is going down
    double newH = h * bounce; //new height of the ball

    if (newH > window) bounces++; //ball is going higher than the window
    return bounces + doBouncingBall(newH, bounce, window);
  }
}
