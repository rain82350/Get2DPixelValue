import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.imageio.ImageIO;

public class GetPixel2D {
	
	public static void main(String[] args) {
		
		BufferedImage img = null;
		//BufferedImage share2 = null;
		//BufferedImage share3 = null;
		Raster tmpRas = null;
		try {
			img = ImageIO.read(new File("chemical_plant_256.png"));
			//share2 = ImageIO.read(new File("s2.png"));
			//share3 = ImageIO.read(new File("s3.png"));
			
			tmpRas=img.getData();
					
			System.out.println("Input OK! " );
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		int height =img.getHeight() ; int width = img.getWidth() ;
		
		//int a, r, g, b;
		
		int[][] pixel= new int[height][width];
		int[][] a= new int[height][width];
		int[][] r= new int[height][width];
		int[][] g= new int[height][width];
		int[][] b= new int[height][width];
		
		
		//Test for pixel value.
		/*
		int pixelTest = img.getRGB(0, 0);
		System.out.println(pixelTest);
		a = (pixelTest>>24)&0xff;
		r = (pixelTest & 0xff0000) >> 16;
		g = (pixelTest & 0x00ff00) >> 8;
		b = (pixelTest & 0x0000ff); 
		System.out.println( " A: "+ a +" R: "+ r + " G: "+ g + " B: " + b);
		int OriPix = (a << 24) + (r << 16) + (g << 8) + b;
		System.out.println(OriPix);
		*/
		
		BufferedWriter fw = null;
		File file = new File("SecretPixelValue.txt");
		
		
		for (int i = 0; i <height; i++) {
            for (int j = 0; j < width; j++) {
            	
            	/*
            	pixel[i][j] = img.getRGB(j, i);
            	
            	a[i][j] = (pixel[i][j] >> 24) & 0xff;
            	r[i][j] = (pixel[i][j] & 0xff0000) >> 16;
				g[i][j] = (pixel[i][j] & 0x00ff00) >> 8;
				b[i][j] = (pixel[i][j] & 0x0000ff); 
                */
				pixel[i][j]=tmpRas.getSample(j, i, 0);
				//System.out.println(r[i][j]);
				//Print 3 digit of value
				/*String rr = String.format( "%03d", r[i][j]);
				System.out.printf( rr + "  ");*/
				
				//System.out.printf( r[i][j] + "  ");
				//System.out.printf( g[i][j] + "  ");
				
				System.out.printf( pixel[i][j] + "  ");
				try {
					String charSp = Integer.toString(pixel[i][j]);
					fw = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(file, true), "UTF-8"));
					fw.append(charSp);
					fw.append(", ");
					fw.flush();

				} catch (Exception e) {
					e.printStackTrace();
				}
				
            }
            
            System.out.println("");
        }
		
		if (fw != null) {
			try {
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//System.out.println(pixel[0][252]+", "+pixel[0][253]+", "+pixel[0][254]+", "+pixel[0][255]);
	}
}
