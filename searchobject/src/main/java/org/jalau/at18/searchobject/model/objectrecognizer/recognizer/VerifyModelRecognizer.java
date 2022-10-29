package org.jalau.at18.searchobject.model.objectrecognizer.recognizer;
import ai.djl.ModelException;
import ai.djl.translate.TranslateException;
import org.jalau.at18.searchobject.common.logger.At18Logger;

import java.io.IOException;
import java.util.logging.Logger;
public class VerifyModelRecognizer {
    Logger log = At18Logger.getLogger();
    public ModelRecognizer getModelRecognizer(String modelRecognizer) throws ModelException, TranslateException, IOException {
        if (modelRecognizer.equals(TypeModelRecognizer.YOLO.getModel())) {
            log.info("Model selected: YOLO");
            return new Yolo();
        }
        if (modelRecognizer.equals(TypeModelRecognizer.SSD.getModel())) {
            log.info("Model selected: SSD");
            return new SSD();
        }
        if (modelRecognizer.equals(TypeModelRecognizer.OBJECTDETECTION.getModel())) {
            log.info("Model selected: OBJECTDETECTION");
            return new ObjectDetection();
        }
        if (modelRecognizer.equals(TypeModelRecognizer.RCNN.getModel())) {
            log.info("Model selected: RCNN");
            return new RCNN();
        }
        return null;
    }
}
