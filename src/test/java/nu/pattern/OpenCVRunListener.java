package nu.pattern;

import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;

public class OpenCVRunListener extends RunListener {
  @Override
  public void testRunStarted(final Description description) throws Exception {
    super.testRunStarted(description);
    OpenCV.loadShared();
  }
}
