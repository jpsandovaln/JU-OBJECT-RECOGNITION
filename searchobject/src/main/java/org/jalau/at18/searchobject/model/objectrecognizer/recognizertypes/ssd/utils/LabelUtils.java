package org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.ssd.utils;
/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
import com.google.protobuf.TextFormat;
import java.nio.charset.StandardCharsets;

import org.jalau.at18.searchobject.model.objectrecognizer.recognizertypes.ssd.protos.StringIntLabelMapOuterClass;

public class LabelUtils {
    public static String[] loadLabels() throws Exception {
        final String FILE_NAME="labels/mscoco_label_map.pbtxt";
        String text = new String(FileUtils.getBytes(FILE_NAME), StandardCharsets.UTF_8);
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
