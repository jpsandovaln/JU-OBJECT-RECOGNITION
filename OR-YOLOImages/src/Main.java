import YOLO.yoloImage;

public class Main {
    public static void main(String[] args) {
        yoloImage yoloForImage = new yoloImage("C:\\Users\\TUF GAMER\\Desktop\\yoloV3\\infinity.png", "C:\\Users\\TUF GAMER\\Desktop\\yoloV3\\output", 288, "dogOut");
        yoloForImage.loadPipeline();
    }
}