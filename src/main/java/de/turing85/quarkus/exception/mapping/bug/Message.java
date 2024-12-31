package de.turing85.quarkus.exception.mapping.bug;

record Message(String message) {
  public static Message of(String message) {
    return new Message(message);
  }
}
