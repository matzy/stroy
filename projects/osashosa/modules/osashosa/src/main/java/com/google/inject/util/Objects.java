package com.google.inject.util;

/**
 * Object utilities.
 *
 * @author crazybob@google.com (Bob Lee)
 */
public class Objects {

  /**
   * Detects null values.
   *
   * @param t value
   * @param message to display in the event of a null
   * @return t
   */
  public static <T> T nonNull(T t, String message) {
    if (t == null) {
      throw new NullPointerException(message);
    }
    return t;
  }
}