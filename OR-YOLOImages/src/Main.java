import YOLO.yoloImage;

public class Main {
    public static void main(String[] args) {
        yoloImage yoloForImage = new yoloImage("C:\\Users\\TUF GAMER\\Desktop\\yoloV3\\image_thumbnail.jpg", "C:\\Users\\TUF GAMER\\Desktop\\yoloV3\\output", 608, "littleBird");
        yoloForImage.loadPipeline();
    }
}