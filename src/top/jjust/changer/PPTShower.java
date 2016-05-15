/**
 * poi13.1版本
 */
/*package top.jjust.changer;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.hslf.HSLFSlideShow;

import top.jjust.bean.RoomBean;
import top.jjust.cache.CacheManager;
import top.jjust.common.StaticVariable;


public class PPTShower {
	public static void run() throws Exception{
		File file = new File("k:/HTML5ppt.ppt");
		InputStream is = new FileInputStream(file);
		PPTShower.readDoc(is,001+"");
	}
	//接受一个inputstream，每个单张ppt转化为图片保存。
	public static void readDoc(InputStream is,String roomID) throws IOException{
		int count = 0;//ppt总量
		HSLFSlideShow hslfSlideShow = new HSLFSlideShow(is);
		  SlideShow slideShow = new SlideShow(hslfSlideShow);
		if(hslfSlideShow.getSlides()!=null&&hslfSlideShow.getSlides().size()>0){
			for(int i = 0 ;i<hslfSlideShow.getSlides().size();i++){
				HSLFSlide slide = hslfSlideShow.getSlides().get(i);//读取每一页
				count++;
				//修改中文乱码
				List<List<HSLFTextParagraph>> textList= slide.getTextParagraphs();
			        for (List<HSLFTextParagraph> RichtextList : textList) {
			            for (HSLFTextParagraph text  : RichtextList) {
			               List<HSLFTextRun> textRuns = text.getTextRuns();
			               for(HSLFTextRun textRun:textRuns){
			            	  System.out.println(textRun.getRawText());
			            	  textRun.setFontFamily("新宋体");
			               }//防止中文乱码
			            }
			        }
			    //转化为图片
			        int width =hslfSlideShow.getPageSize().width;
			        int height = hslfSlideShow.getPageSize().height;
				BufferedImage img = new BufferedImage(width
						,height
						,
						BufferedImage.TYPE_INT_RGB);   
				Graphics2D graphics = img.createGraphics();
				 int backgroundColor = slide.getColorScheme()
                   .getBackgroundColourRGB();
				 graphics.setPaint(new Color(backgroundColor));
				graphics.fill(new Rectangle2D.Float(0, 0, width,
						height));
				slide.getBackground().draw(graphics);
				slide.draw(graphics);
				//图片存储路径
				FileOutputStream fos = new FileOutputStream(StaticVariable.PPTPicRootPath+roomID+"/"+i+".png");
				ImageIO.write(img,"png", fos);
				//得到房间对象修改PPt总数
				((RoomBean)CacheManager.getBean(roomID)).setPPTCount(count);
				fos.close();
			}
		}
	}

}*/
