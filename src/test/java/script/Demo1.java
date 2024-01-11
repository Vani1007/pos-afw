package script;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Demo1 {

	public static void main(String[] args) throws Exception {
		Properties p=new Properties();
		p.load(new FileInputStream("./config.properties"));
		String v = p.getProperty("URl");
		System.out.println(v);
	}

}
