package top.jjust.changer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

import top.jjust.bean.RoomBean;
import top.jjust.cache.CacheManager;
import top.jjust.common.StaticVariable;

public class PPTShower2007 {
	/**
	 * PPT转图片 （jpeg）(2007)
	 */
	public static boolean doPPTtoImage2007(InputStream is,String roomID) {
		try {
			int count = 0;//ppt总量
			XMLSlideShow xmlSlideShow = new XMLSlideShow(is);
			XSLFSlide[] xslfSlides = xmlSlideShow.getSlides();

			is.close();
			for (int i = 0; i < xslfSlides.length; i++) {
				XSLFSlide slide = xslfSlides[i];
				int width = xslfSlides[i].getSlideShow().getPageSize().width;
				int height = xslfSlides[i].getSlideShow().getPageSize().height;
				count++;
				//修改中文乱码
				for( XSLFShape shape : slide.getShapes() ){
					if ( shape instanceof XSLFTextShape ){
						XSLFTextShape txtshape = (XSLFTextShape)shape ;
						for ( XSLFTextParagraph textPara : txtshape.getTextParagraphs() ){
							List<XSLFTextRun> textRunList = textPara.getTextRuns();
							for(XSLFTextRun textRun: textRunList) {
								textRun.setFontFamily("宋体");
							}
						}
					}
				}
				//转化为图片
				BufferedImage img = new BufferedImage(width,height
						, BufferedImage.TYPE_INT_RGB);

				Graphics2D graphics = img.createGraphics();
				graphics.setPaint(Color.white);
				graphics.fill(new Rectangle2D.Float(0, 0, width,
						height));
				xslfSlides[i].draw(graphics);

				FileOutputStream out = new FileOutputStream(StaticVariable.PPTPicRootPath+roomID+"/"+i+".png");
				javax.imageio.ImageIO.write(img, "jpeg", out);
				//得到房间对象修改PPt总数
				((RoomBean)CacheManager.getBean(roomID)).setPPTCount(count);
				out.close();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
