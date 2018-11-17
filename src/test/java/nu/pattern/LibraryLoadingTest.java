package nu.pattern;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

/**
 * @see <a href="https://github.com/PatternConsulting/opencv/issues/7">Issue 7</a>
 */
@RunWith(JUnit4.class)
public class LibraryLoadingTest {
  private final static Logger logger = Logger.getLogger(LibraryLoadingTest.class.getName());

  public static class Client {
    static {
      OpenCV.loadLocally();
    }

    /**
     * Run interesting tests on instantiation to make reflection chores simpler.
     */
    public Client() {
      final Mat m = new Mat(5, 10, CvType.CV_8UC1, new Scalar(0));
      logger.log(Level.FINEST, "Initial matrix: {0}.", m.dump());

      final Mat mr1 = m.row(1);
      mr1.setTo(new Scalar(1));

      final Mat mc5 = m.col(5);
      mc5.setTo(new Scalar(5));

      logger.log(Level.FINEST, "Final matrix: {0}.", m.dump());
    }
  }

  /**
   * {@link OpenCV#loadLocally()} is safe to call repeatedly within a single {@link ClassLoader} context.
   */
  @Test
  public void spuriousLoads() {
    OpenCV.loadLocally();
    OpenCV.loadLocally();
  }
}
