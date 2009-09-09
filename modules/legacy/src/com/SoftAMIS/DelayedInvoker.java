package com.SoftAMIS;

/**
 * source http://www.soft-amis.com/serendipity/index.php?/archives/12-Class-that-any-Swing-application-cant-live-without.html
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DelayedInvoker extends Timer {

  // data that could be passed to listener
  protected Object fParameter = null;
  // default delay
  public static final int DEF_DELAY = 200;

  //  Creates invoker that will fire event to provided
  //  listener after  specified delay
  //  @param aDelay - delay in milliseconds
  //  @param anActionListener listener that will be invoked
  public DelayedInvoker(int aDelay,
                        ActionListener anActionListener) {
    super(aDelay, anActionListener);
    setRepeats(false);
  }

  // Creates invoker that will fire event to provided
  // listener after specified delay. Default delay (200 ms)
  // is used for invocation.
  // @param anActionListener listener that will be invoked
  public DelayedInvoker(ActionListener anActionListener){
    this(DEF_DELAY, anActionListener);
  }

  //  Data associated with invoker
  public Object getParameter(){
    return fParameter;
  }

  // Setter for data accociated
  public void setParameter(Object aParameter){
    fParameter = aParameter;
  }

  // Schedules invocation of listener. Stores
  // parameter and does the same as takeUp().
  public void takeUp(Object aParameter) {
    setParameter(aParameter);
    takeUp();
  }

  // Scheduled invocation of listener - if timer
  // is currently running, it's restarts it (so
  // already scheduled invocation will not fired,
  // if timer is not started - it simply starts it.
  // Therefore, after calling this method the
  // provided listener will be invoked once after
  // specified delay if no subsequent invocations
  // of that method will be peformed.
  // Many subsequent calls to this method could be
  // performed, but listener will be called after
  // specified delay only after LAST invocation.
  public void takeUp() {
    if (isRunning()) {
      restart();
    }
    else {
      start();
    }
  }

  // Schedules invocation of listener with provided
  // data and delay. Many subsequent calls to this
  // method could be performed, but listener will
  // be called after specified delay only after
  // LAST invocation.
  public void takeUp(Object aParameter, int aDelay){
    setParameter(aParameter);
    takeUp(aDelay);
  }

  // Schedules invocation of specified listener after
  // specified delay.
  // If timer is started, performs restart of it,
  // otherwise starts it.
  // Many subsequent calls to this method could be
  // performed, but listener
  // will be called after specified delay only after
  // LAST invocation.
  public void takeUp(int aDelay) {
    setDelay(aDelay);
    setInitialDelay(aDelay);
    if (isRunning()) {
      restart();
    }
    else {
      start();
    }
  }

  // Utility method that allows to invoke listener
  // associated with invoker immidiately.
  public void force()
  {
    fireActionPerformed(new ActionEvent(this,
                  ActionEvent.ACTION_PERFORMED, ""));
    if (isRunning()) {
      stop();
    }
  }

  //Internal method that fires invocation of
  // accociated listener
  @Override
  protected void fireActionPerformed(ActionEvent e)  {
    final ActionEvent actionEvent = e;
    SwingUtilities.invokeLater(new Runnable() {
      ActionEvent fActionEvent = actionEvent;
      public void run() {
        DelayedInvoker.super.fireActionPerformed(fActionEvent);
      }
    });
  }
}
