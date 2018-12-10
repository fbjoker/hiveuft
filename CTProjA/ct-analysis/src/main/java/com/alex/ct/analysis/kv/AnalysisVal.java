package com.alex.ct.analysis.kv;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class AnalysisVal implements Writable {

    private String sumcall;
    private  String sumduration;

    public AnalysisVal() {
    }

    public AnalysisVal(String sumcall, String sumduration) {
        this.sumcall = sumcall;
        this.sumduration = sumduration;
    }

    public String getSumcall() {
        return sumcall;
    }

    public void setSumcall(String sumcall) {
        this.sumcall = sumcall;
    }

    public String getSumduration() {
        return sumduration;
    }

    public void setSumduration(String sumduration) {
        this.sumduration = sumduration;
    }

    public void write(DataOutput out) throws IOException {

        out.writeUTF(sumcall);
        out.writeUTF(sumduration);

    }

    public void readFields(DataInput in) throws IOException {

        sumcall=in.readUTF();
        sumduration=in.readUTF();
    }
}
