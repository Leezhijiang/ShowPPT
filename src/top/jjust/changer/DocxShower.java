package top.jjust.changer;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.core.XWPFConverterException;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

import com.sun.accessibility.internal.resources.accessibility;

import top.jiang.utils.FileUtils;
import top.jiang.utils.HtmlUtils;
import top.jjust.common.StaticVariable;


public class DocxShower {

	/**
	 * 1
	 */
	/*public static String convert2Html1(InputStream is,String roomID) throws OfficeXmlFileException, TransformerException, IOException, ParserConfigurationException{
		String content = (convert(is, roomID).replace("<table class=\"X3 X4\"", "<table border='1'cellspacing=\"0\" cellpadding=\"0\""));
		FileUtils.writeFile(content, StaticVariable.PPTPicRootPath+roomID+"/"+"my.html");
		return content;
	}
	public static String convert(InputStream is,String roomID)  
	{  
		 InputStream in = new FileInputStream(new File(fileName));  
         XWPFDocument document = new XWPFDocument(in);  
        // 1) Load DOCX into XWPFDocument  
		XWPFDocument document = null;
		try {
			document = new XWPFDocument(is);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
			try {
				document = new XWPFDocument(is);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  

			// 2) Prepare XHTML options (here we set the IURIResolver to  
			// load images from a "word/media" folder)  
			File imageFolderFile = new File(StaticVariable.PPTPicRootPath+roomID);  
			XHTMLOptions options = XHTMLOptions.create().URIResolver(  
					new FileURIResolver(imageFolderFile));  
			options.setExtractor(new FileImageExtractor(imageFolderFile));  
			options.setIgnoreStylesIfUnused(false);  
			options.setFragment(true);  

			// 3) Convert XWPFDocument to XHTML  
//			OutputStream out = new FileOutputStream(new File(  
//			"d:/test.htm"));  
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			try {
				XHTMLConverter.getInstance().convert(document, out, options);
			} catch (XWPFConverterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			writeFile(new String(out.toByteArray()), outPutFile); 
			return new String(out.toByteArray());
	} */
	/**
	 * yuan
	 *
	 */
	/*		 public static void run() {  
	        try { 
//	        	newfunc("D://213.docx");
	        	//���ӱ߿�
	            writeFile((convert2Html("k://a.docx").replace("<table class=\"X3 X4\"", "<table border='1'cellspacing=\"0\" cellpadding=\"0\"")),"k://a.html");  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  

	    public static void writeFile(String content, String path) {  
	        FileOutputStream fos = null;  
	        BufferedWriter bw = null;  
	        try {  
	            File file = new File(path);  
	            fos = new FileOutputStream(file);  
	            bw = new BufferedWriter(new OutputStreamWriter(fos,"utf-8"));  
	            bw.write(content);  
	        } catch (FileNotFoundException fnfe) {  
	            fnfe.printStackTrace();  
	        } catch (IOException ioe) {  
	            ioe.printStackTrace();  
	        } finally {  
	            try {  
	                if (bw != null)  
	                    bw.close();  
	                if (fos != null)  
	                    fos.close();  
	            } catch (IOException ie) {  
	            }  
	        }  
	    }  

	    public static String convert2Html(String fileName)  
	            throws TransformerException, IOException,  
	            ParserConfigurationException ,OfficeXmlFileException{  
	        try {
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
				         return "/"+suggestedName;  
				     }  
				 } );  
				wordToHtmlConverter.processDocument(wordDocument);  
				//save pictures  
				List pics=wordDocument.getPicturesTable().getAllPictures();  
				if(pics!=null){  
				    for(int i=0;i<pics.size();i++){  
				        Picture pic = (Picture)pics.get(i);  
				        try {  
				            pic.writeImageContent(new FileOutputStream("k://"  
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
				serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");  
				serializer.setOutputProperty(OutputKeys.INDENT, "yes");  
				serializer.setOutputProperty(OutputKeys.METHOD, "html");  
				serializer.transform(domSource, streamResult);  
				out.close();  
//				writeFile(new String(out.toByteArray()), outPutFile);  
				return new String(out.toByteArray());
			} catch (OfficeXmlFileException e) {
				System.out.println("x");

             // 1) Load DOCX into XWPFDocument  
             InputStream in = new FileInputStream(new File(fileName));  
             XWPFDocument document = new XWPFDocument(in);  

             // 2) Prepare XHTML options (here we set the IURIResolver to  
             // load images from a "word/media" folder)  
             File imageFolderFile = new File("k://");  
             XHTMLOptions options = XHTMLOptions.create().URIResolver(  
                     new FileURIResolver(imageFolderFile));  
             options.setExtractor(new FileImageExtractor(imageFolderFile));  
             options.setIgnoreStylesIfUnused(false);  
             options.setFragment(true);  

             // 3) Convert XWPFDocument to XHTML  
//             OutputStream out = new FileOutputStream(new File(  
//                     "d:/test.htm"));  
             ByteArrayOutputStream out = new ByteArrayOutputStream();
             XHTMLConverter.getInstance().convert(document, out, options);  
             out.close();
//             writeFile(new String(out.toByteArray()), outPutFile); 
				return new String(out.toByteArray());
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				return "";
			} catch (TransformerFactoryConfigurationError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}
	    }  
//	private static void newfunc(String fileName) throws IOException {
//	InputStream in = new FileInputStream(fileName);  
//	XWPFDocument document = new XWPFDocument(in); 
//	XHTMLOptions options = XHTMLOptions.create().indent( 4 ); 
//	OutputStream out = System.out;
//	XHTMLConverter.getInstance().convert( document, out, options ); 
//	}
	 *
	 */
	/**
	 * 2
	 * @throws IOException 
	 */
	public static String convert2Html(InputStream is,String roomID) throws IOException{
		// 1) Load DOCX into XWPFDocument   
		XWPFDocument document = new XWPFDocument(is);  

		// 2) Prepare XHTML options (here we set the IURIResolver to  
		// load images from a "word/media" folder)  
		File imageFolderFile = new File(StaticVariable.PPTPicRootPath+roomID+"/");  
		XHTMLOptions options = XHTMLOptions.create().URIResolver(  
				new FileURIResolver(imageFolderFile));  
		options.setExtractor(new FileImageExtractor(imageFolderFile));  
		options.setIgnoreStylesIfUnused(false);  
		options.setFragment(true);  

		// 3) Convert XWPFDocument to XHTML  
//		OutputStream out = new FileOutputStream(new File(  
//		"d:/test.htm"));  
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		XHTMLConverter.getInstance().convert(document, out, options);  
		out.close();
		String content = new String(out.toByteArray());
		content = content.replace("/usr/local/apache-tomcat-8.0.30/webapps","");
		 content = content.replace("<table", "<table style=\"border:1;\" border frame=box");
		 content = HtmlUtils.getHtml(content);
		FileUtils.writeFile(content, StaticVariable.PPTPicRootPath+roomID+"/"+"my.html");
		return content;
	}
}
