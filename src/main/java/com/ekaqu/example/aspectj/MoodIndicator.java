package com.ekaqu.example.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;

/**
 * Date: 2/25/12
 * Time: 7:52 PM
 */
@Aspect
public class MoodIndicator {
  // this interface can be outside of the aspect
  public interface Moody {
    public static enum Mood {HAPPY, SAD};
    Mood getMood();
  };

  // this implementation can be outside of the aspect
  public static class MoodyImpl implements Moody {
    private Mood mood = Mood.HAPPY;

    public Mood getMood() {
      return mood;
    }
  }

  // the field type must be the introduced interface. It can't be a class.
  @DeclareParents(value="com.ekaqu.example.aspectj.MockCache",defaultImpl=MoodyImpl.class)
  private Moody implementedInterface;

  @Before("execution(* com.ekaqu.example.aspectj.MockCache.*(..)) && this(m)")
  public void feelingMoody(Moody m) {
    System.out.println("I'm feeling " + m.getMood());
  }
}
