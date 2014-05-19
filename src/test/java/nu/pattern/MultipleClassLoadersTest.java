package nu.pattern;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.net.URLClassLoader;

@RunWith(JUnit4.class)
public class MultipleClassLoadersTest {
  public static class Client {
    public Client() {
      OpenCV.loadLibrary();
    }
  }


  @Test
  @SuppressWarnings("unchecked")
  public void test() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    final ClassLoader loader0 = new TestClassLoader();
    final ClassLoader loader1 = new TestClassLoader();

    final Class<?> c0 = Class.forName(Client.class.getName(), true, loader0);
    final Class<?> c1 = Class.forName(Client.class.getName(), true, loader1);

    Assert.assertNotEquals("A class from two different loaders should not be equal.", c0, c1);
    Assert.assertNotSame("A class from two different loaders should not be same.", c0, c1);

// c0.newInstance();
// c1.newInstance();
  }


  public static class TestClassLoader extends URLClassLoader {
    public TestClassLoader() {
      super(((URLClassLoader) getSystemClassLoader()).getURLs());
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
      if (name.startsWith("nu.pattern")) {
        return super.findClass(name);
      }

      return super.loadClass(name);
    }
  }
}
