package ch.cinus.kata.sixkyu.crackthepin;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.google.common.hash.Hashing;

public class CodeWars {

  public String crack(String hash) {

    for (int i = 0; i < 99999; i++) {
      final var candidatePin = String.format("%05d", i);
      String candidateHash = Hashing.md5().hashString(candidatePin, UTF_8).toString();
      if (hash.equals(candidateHash)) {
        return candidatePin;
      }
    }
    return "Not Cracked";
  }
}
