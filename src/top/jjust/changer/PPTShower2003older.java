package top.jjust.changer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;

import top.jjust.bean.RoomBean;
import top.jjust.cache.CacheManager;
import top.jjust.common.StaticVariable;



public class PPTShower2003older {
	public static boolean doPPTtoImage(InputStream is, String roomID) {
        try {
        	int count = 0;//ppt总量
            SlideShow ppt = new SlideShow(is);
            is.close();
            Dimension pgsize = ppt.getPageSize();
              int width =  (int)pgsize.getWidth();
               int height = (int)pgsize.getHeight();
            Slide[] slide = ppt.getSlides();
            for (int i = 0; i < slide.length; i++) {
            	count++;
 /*           	 TextRun[] truns = slide[i].getTextRuns();
            	    for (int k = 0; k < truns.length; k++) {
            	     RichTextRun[] rtruns = truns[k].getRichTextRuns();
            	     for (int l = 0; l < rtruns.length; l++) {
            	      int index = rtruns[l].getFontIndex();
            	      String name = rtruns[l].getFontName();
            	      rtruns[l].setFontIndex(1);
            	      rtruns[l].setFontName("宋体");
            	     }
            	    }*/
                System.out.print("第" + i + "页。");
                BufferedImage img = new BufferedImage(width,
                        height, BufferedImage.TYPE_INT_RGB);

                Graphics2D graphics = img.createGraphics();
                int backgroundColor = slide[i].getColorScheme()
                        .getBackgroundColourRGB();
                graphics.setPaint(new Color(backgroundColor));
                graphics.fill(new Rectangle2D.Float(0, 0, width,
                        height));
                slide[i].getBackground().draw(graphics);
                slide[i].draw(graphics);

                FileOutputStream out = new FileOutputStream(StaticVariable.PPTPicRootPath+roomID+"/"+i+".png");
                javax.imageio.ImageIO.write(img, "png", out);
              //得到房间对象修改PPt总数
				((RoomBean)CacheManager.getBean(roomID)).setPPTCount(count);
                out.close();

            }
            return true;
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
        }
        return false;
    }
}
