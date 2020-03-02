package ch.cinus.kata.sixkyu.diseasespread;

import java.util.Arrays;

public class Epidem {
  public static int epidemic(int tm, int n, int s0, int i0, double beta, double gamma) {
    /* Implementation of the basic SIR - model (Also know as the  Kermack-McKendrick Model see http://mathworld.wolfram.com/Kermack-McKendrickModel.html)
    ODE have ben transformed to finite differences, see: https://en.wikipedia.org/wiki/Finite_difference_method#Example:_ordinary_differential_equation

    s0 -> Starting population
    i0 -> initial infected
    tm -> time period (days)
    n -> steps for the finite difference conversion
    beta -> infection rate
    gamma -> recovery rate
     */

    double dt = (double) tm / n;

    double[] S = new double[n + 1];
    double[] I = new double[n + 1];
    double[] R = new double[n + 1];

    S[0] = s0;
    I[0] = i0;
    R[0] = 0.0d;

    for (int k = 0; k < n; k++) {
      double sk = S[k];
      double ik = I[k];
      double rk = R[k];
      S[k + 1] = sk - dt * beta * sk * ik;
      I[k + 1] = ik + dt * (beta * sk * ik - gamma * ik);
      R[k + 1] = rk + dt * ik * gamma;
    }

    return Arrays.stream(I).boxed().max(Double::compareTo).map(Double::intValue).orElse(-1);
  }
}
