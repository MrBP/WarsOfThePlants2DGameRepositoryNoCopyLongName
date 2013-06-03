package net.mrblockplacer.WarsOfThePlants.conf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class MainConf {
	String APPDATA = System.getenv("APPDATA");
	private File confFile;

	public MainConf(String s) {
		try {
			confFile = new File(APPDATA + "\\WarsOfThePlants\\" + s);
			if (!confFile.exists()) {
				confFile.getParentFile().mkdirs();
				confFile.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readFromKey(String key) {
		// ArrayList<String> list1 = new ArrayList<String>();
		// BufferedReader br = null;
		String fin = "";
		Properties prop = new Properties();

		try {
			// load a properties file
			prop.load(new FileInputStream(confFile));

			// get the property value and print it out
			// System.out.println(prop.getProperty(key));
			fin = prop.getProperty(key);

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		// try {
		// String sCurrentLine;
		// br = new BufferedReader(new FileReader(confFile));
		// while ((sCurrentLine = br.readLine()) != null) {
		// list1.add(sCurrentLine);
		// System.out.println("Reading property: " + sCurrentLine);
		// }
		// } catch (IOException e) {
		// e.printStackTrace();
		// } finally {
		// try {
		// if (br != null)
		// br.close();
		// } catch (IOException ex) {
		// ex.printStackTrace();
		// }
		// }
		// for (String s : list1) {
		// if (s != "") {
		// String[] s2 = s.split(":::");
		// if (key.equals(s2[0])) {
		// fin = s2[1];
		// }
		// }
		// }
		return fin;
	}

	public boolean writeToKey(String key, String object) {
		// try {
		// PrintWriter out = new PrintWriter(new BufferedWriter(
		// new FileWriter(confFile, true)));
		// out.println(key + ":::" + object);
		// out.close();
		// return true;
		// } catch (IOException e) {
		// e.printStackTrace();
		// return false;
		// }
		Properties prop = new Properties();

		try {
			prop.load(new FileInputStream(confFile));
			// set the properties value
			prop.setProperty(key, object);

			// save properties to project root folder
			prop.store(new FileOutputStream(confFile), null);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return false;

	}
}
