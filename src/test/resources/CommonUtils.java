import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.StringTokenizer;


public class CommonUtils {
	

	//search the file in the classpath
	public static File search(final String fileName) {
			        final String classpathStr = System.getProperty("java.class.path");
			        final String separator = System.getProperty("path.separator");
			        final StringTokenizer strTokenizer = new StringTokenizer(classpathStr,
			                separator);
			        while (strTokenizer.hasMoreTokens()) {
			            final String pathElement = strTokenizer.nextToken();
			            final File directory = new File(pathElement);
			            final File absoluteDirectory = directory
			                    .getAbsoluteFile();
			            if (absoluteDirectory.isFile()) {
			                final File target = new File(
			                        absoluteDirectory.getParent(), fileName);
			                if (target.exists()) {
			                    return target;
			                }
			            } else {
			                final File target = new File(directory, fileName);
			                if (target.exists()) {
			                    return target;
			                }
			            }
			        }
			        return null;
			    }
		
	
}
