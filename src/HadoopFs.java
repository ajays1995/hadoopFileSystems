import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;


public class HadoopFs 
{

	/**
	 * @param args
	 * @throws URISyntaxException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws URISyntaxException, IOException 
	{
		URI uri = new URI(args[0]);
		Configuration cf = new Configuration();
		System.out.println(" Scheme  "+uri.getScheme());
		System.out.println("Authority  "+uri.getAuthority());
		FileSystem fs = FileSystem.get(uri, cf);
		System.out.println("Configuration Properties  "+cf.getClass("fs." + uri.getScheme() + ".impl", null));
		System.out.println("file system name  "+fs.getClass());
		InputStream is = null;
		
		try{
		is =fs.open(new Path(uri));
		IOUtils.copyBytes(is, System.out, 1024, false);
		}
		finally
		{
			IOUtils.closeStream(is);
		}
		
		

	}

}
