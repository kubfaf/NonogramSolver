
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageCreator {

    private static final String OUTPUT_FILE_NAME = "outputImage.png";
    private static final String OUTPUT_FILE_FORMAT = "png";

    public static void createImage(Grid grid) {

        final int sizeOfRectangle = 10;
        BufferedImage bufferedImage = new BufferedImage(grid.squares.length * sizeOfRectangle, grid.squares[0].length * sizeOfRectangle, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = bufferedImage.createGraphics();
        //draw filled squares

        for (int y = 0; y < grid.squares[0].length; y++) {
            for (int x = 0; x < grid.squares.length; x++) {
                SquareStatus squareStatus = grid.squares[x][y].status;
                if (squareStatus == SquareStatus.BLACK) {
                    g.setColor(Color.black);
                } else if (squareStatus == SquareStatus.WHITE) {
                    g.setColor(Color.white);
                } else {
                    g.setColor(Color.gray);
                }
                g.fill(new Rectangle(x * sizeOfRectangle, y * sizeOfRectangle, sizeOfRectangle, sizeOfRectangle));
            }
        }

        saveImage(bufferedImage);

    }

    private static void saveImage(BufferedImage bufferedImage) {
        try {
            System.out.println("Saving image...");
            ImageIO.write(bufferedImage, OUTPUT_FILE_FORMAT, new File(OUTPUT_FILE_NAME));
            System.out.println("Image saved!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
