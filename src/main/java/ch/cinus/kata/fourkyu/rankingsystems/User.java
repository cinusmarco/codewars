package ch.cinus.kata.fourkyu.rankingsystems;

import java.util.logging.Logger;

public class User {
  private static Logger logger = Logger.getLogger(User.class.getSimpleName());
  int rank;
  int progress;

  public User() {
    this(-8, 0);
  }

  public User(int rank, int progress) {
    this.rank = rank;
    this.progress = progress;
  }

  void incProgress(int exerciseRank) {
    int increase = computeGain(exerciseRank);
    updateProgress(increase);
  }

  private void updateProgress(int increase) {
    int newProgress = this.progress + increase;
    logger.info(
        String.format(
            "this.progress = %d, increase = %d, newProgress = %d",
            this.progress, increase, newProgress));
    if (newProgress < 100) {
      this.progress = newProgress;
    } else {
      increaseRank(newProgress / 100);
      this.progress = newProgress % 100;
    }
  }

  private void increaseRank(int numberOfRanks) {
    int delta = numberOfRanks;
    if (rank < 0 && rank + numberOfRanks >= 0) delta++;
    this.rank += delta;
  }

  private int computeGain(int exerciseRank) {
    int delta = (exerciseRank - this.rank) - (exerciseRank * this.rank >= 0 ? 0 : 1);
    logger.info(String.format("delta = %d", delta));
    int gain = 0;
    if (delta <= -2) {
      return 0;
    } else if (delta == -1) {
      gain = 1;
    } else if (delta == 0) {
      gain = 3;
    } else {
      gain = 10 * delta * delta;
    }

    return gain;
  }
}
