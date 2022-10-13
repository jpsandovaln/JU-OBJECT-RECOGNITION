package org.jalau.at18.searchobject.service;

import org.jalau.at18.searchobject.model.FileSource;
import org.jalau.at18.searchobject.model.FrameInfo;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessVideoService {
    // This method receive the Video in format MP4.
    // Of each video convert to frames, and send the list of frames to that these are proccesed according the criteria
    public List<FrameInfo> processVideoInFrames(FileSource fileSource) {
        // to be implemented by
        // Mauricio, Libertad, and Jose
        // this is only an example to know that should return of each model
        List<FrameInfo> frames = new ArrayList<>();
        frames.add(new FrameInfo("25.png", LocalTime.of(0, 5, 3)));
        return frames;
    }
}
