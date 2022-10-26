package org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.ssd.utils;

import com.google.protobuf.TextFormat;
import java.nio.charset.StandardCharsets;

import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.ssd.protos.StringIntLabelMapOuterClass;

public class LabelUtils {
    public static String[] loadLabels() throws Exception {
        String text = new String(FileUtils.getBytes("labels/mscoco_label_map.pbtxt"), StandardCharsets.UTF_8);
        StringIntLabelMapOuterClass.StringIntLabelMap.Builder builder = StringIntLabelMapOuterClass.StringIntLabelMap.newBuilder();
        TextFormat.merge(text, builder);
        StringIntLabelMapOuterClass.StringIntLabelMap proto = builder.build();
        int maxId = 0;
        for (StringIntLabelMapOuterClass.StringIntLabelMapItem item : proto.getItemList()) {
            if (item.getId() > maxId) {
                maxId = item.getId();
            }
        }
        String[] ret = new String[maxId + 1];
        for (StringIntLabelMapOuterClass.StringIntLabelMapItem item : proto.getItemList()) {
            ret[item.getId()] = item.getDisplayName();
        }
        return ret;
    }
}