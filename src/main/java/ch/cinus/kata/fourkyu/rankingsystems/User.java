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
    if (exerciseRank < -8 || exerciseRank > 8 || exerciseRank == 0)
      throw new IllegalArgumentException();
    logger.info(
        String.format("User[%d, %d]  exerciseRank = %d", this.rank, this.progress, exerciseRank));
    if (rank == 8) {
      return;
    }
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
      if (!increaseRank(newProgress / 100)) this.progress = newProgress % 100;
    }
  }

  private boolean increaseRank(int numberOfRanks) {
    boolean isMaxxed = false;
    int delta = numberOfRanks;
    if (rank < 0 && rank + numberOfRanks >= 0) delta++;
    this.rank += delta;
    this.rank = Math.min(this.rank, 8);
    if (this.rank == 8) {
      progress = 0;
      isMaxxed = true;
    }
    return isMaxxed;
  }

  private int computeGain(int exerciseRank) {
    int delta = -8;
    if ((this.rank > 0 && exerciseRank > 0) || (this.rank < 0 && exerciseRank < 0)) {
      delta = exerciseRank - rank;
    } else if (this.rank > 0 && exerciseRank < 0) {
      delta = exerciseRank - rank + 1;
    } else {
      delta = exerciseRank - rank - 1;
    }
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
