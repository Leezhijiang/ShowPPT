package top.jjust.junit;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;

import top.jiang.utils.FileUtils;
import top.jjust.changer.DocxShower;

public class Test1 {
	@Test
	public void get() throws Exception {
		File file = new File("k://a.docx");
		InputStream is = new FileInputStream(file);
	
	}
}
