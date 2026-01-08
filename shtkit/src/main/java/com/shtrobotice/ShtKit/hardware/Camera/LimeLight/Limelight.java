package com.shtrobotice.ShtKit.hardware.Camera.LimeLight;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Limelight {
    Limelight3A ll;
    Map<Integer, LLResultTypes.FiducialResult> fiducialResultMap = new HashMap<>();
    List<Integer> TaggingIDs = new ArrayList<>();

    public Limelight(HardwareMap hm, String name) {
        ll = hm.get(Limelight3A.class, "limelight");
        ll.start();
    }

    public boolean isTagging(int ID) { return TaggingIDs.contains(ID); }
    public LLResultTypes.FiducialResult getResult(int ID) { if(isTagging(ID)) { return fiducialResultMap.get(ID); } else { return null; }}
    public double getX(int ID) { if(isTagging(ID)) { return fiducialResultMap.get(ID).getTargetXPixels(); } else { return 0.0; }}
    public double getY(int ID) { if(isTagging(ID)) { return fiducialResultMap.get(ID).getTargetYPixels(); } else { return 0.0; }}


    public void update() {
        LLResult result = ll.getLatestResult();

        List<LLResultTypes.FiducialResult> fiducialResults = result.getFiducialResults();

        TaggingIDs = new ArrayList<>();
        fiducialResultMap = new HashMap<>();

        if(!fiducialResults.isEmpty()) {
            for (LLResultTypes.FiducialResult fid : result.getFiducialResults()) {
                TaggingIDs.add(fid.getFiducialId());
                fiducialResultMap.put(fid.getFiducialId(), fid);
            }
        }
    }
}
