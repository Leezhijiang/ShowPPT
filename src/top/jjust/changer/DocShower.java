package top.jjust.changer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.w3c.dom.Document;

import top.jiang.utils.FileUtils;
import top.jiang.utils.HtmlUtils;
import top.jjust.common.StaticVariable;
/**
 * 不支持方框等，支持表格
 * @author Lee
 *
 */
public class DocShower {


/*	public static void run() {  
		try {  
			convert2Html("K://a.doc","K://a.html");  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  
	} */ 

	
	public static String convert2Html(InputStream is,final String roomID)  
	throws TransformerException, IOException,  
	ParserConfigurationException {  
		HWPFDocument wordDocument = new HWPFDocument(is);//WordToHtmlUtils.loadDoc(new FileInputStream(inputFile));  
		WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(  
				DocumentBuilderFactory.newInstance().newDocumentBuilder()  
				.newDocument());  
		wordToHtmlConverter.setPicturesManager( new PicturesManager()  
		{  
			public String savePicture( byte[] content,  
					PictureType pictureType, String suggestedName,  
					float widthInches, float heightInches )  
			{  
				return StaticVariable.PPTPicRootPath+roomID+"/"+suggestedName;  
				
			}  
		} );  
		wordToHtmlConverter.processDocument(wordDocument);  
		//save pictures  
		List pics=wordDocument.getPicturesTable().getAllPictures();  
		if(pics!=null){  
			for(int i=0;i<pics.size();i++){  
				Picture pic = (Picture)pics.get(i);  
				System.out.println();  
				try {  
					pic.writeImageContent(new FileOutputStream(StaticVariable.PPTPicRootPath+roomID+"/"  
							+ pic.suggestFullFileName()));  
				} catch (FileNotFoundException e) {  
					e.printStackTrace();  
				}    
			}  
		}  
		Document htmlDocument = wordToHtmlConverter.getDocument();  
		ByteArrayOutputStream out = new ByteArrayOutputStream();  
		DOMSource domSource = new DOMSource(htmlDocument);  
		StreamResult streamResult = new StreamResult(out);  

		TransformerFactory tf = TransformerFactory.newInstance();  
		Transformer serializer = tf.newTransformer();  
		/*serializer.setOutputProperty(OutputKeys.ENCODING, "gb2312");  */
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");  
		serializer.setOutputProperty(OutputKeys.METHOD, "html");  
		serializer.transform(domSource, streamResult);  
		out.close();  
		String content = (new String(out.toByteArray()).replace("<table", "<table border frame=box"));
		content = content.replace("/usr/local/apache-tomcat-8.0.30/webapps","");
		content = HtmlUtils.getHtml(content);
		FileUtils.writeFile(content, StaticVariable.PPTPicRootPath+roomID+"/"+"my.html");
		return content;  
	}  

	/*  public static void convert2Html(String fileName, String outPutFile)  
	            throws TransformerException, IOException,  
	            ParserConfigurationException {  
	        HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(fileName));//WordToHtmlUtils.loadDoc(new FileInputStream(inputFile));  
	        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(  
	                DocumentBuilderFactory.newInstance().newDocumentBuilder()  
	                        .newDocument());  
	         wordToHtmlConverter.setPicturesManager( new PicturesManager()  
	         {  
	             public String savePicture( byte[] content,  
	                     PictureType pictureType, String suggestedName,  
	                     float widthInches, float heightInches )  
	             {  
	                 return "test/"+suggestedName;  
	             }  
	         } );  
	        wordToHtmlConverter.processDocument(wordDocument);  
	        //save pictures  
	        List pics=wordDocument.getPicturesTable().getAllPictures();  
	        if(pics!=null){  
	            for(int i=0;i<pics.size();i++){  
	                Picture pic = (Picture)pics.get(i);  
	                System.out.println();  
	                try {  
	                    pic.writeImageContent(new FileOutputStream("k:/word/"  
	                            + pic.suggestFullFileName()));  
	                } catch (FileNotFoundException e) {  
	                    e.printStackTrace();  
	                }    
	            }  
	        }  
	        Document htmlDocument = wordToHtmlConverter.getDocument();  
	        ByteArrayOutputStream out = new ByteArrayOutputStream();  
	        DOMSource domSource = new DOMSource(htmlDocument);  
	        StreamResult streamResult = new StreamResult(out);  

	        TransformerFactory tf = TransformerFactory.newInstance();  
	        Transformer serializer = tf.newTransformer();  
	        serializer.setOutputProperty(OutputKeys.ENCODING, "GB2312");  
	        serializer.setOutputProperty(OutputKeys.INDENT, "yes");  
	        serializer.setOutputProperty(OutputKeys.METHOD, "html");  
	        serializer.transform(domSource, streamResult);  
	        out.close();  
	        writeFile((new String(out.toByteArray()).replace("<table", "<table border frame=box")), outPutFile);  
	    }  */
}
