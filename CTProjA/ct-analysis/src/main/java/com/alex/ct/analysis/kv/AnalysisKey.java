package com.alex.ct.analysis.kv;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class AnalysisKey implements WritableComparable<AnalysisKey> {

    private  String call1;
    private String date;

    public AnalysisKey() {
    }

    public AnalysisKey(String call1, String date) {
        this.call1 = call1;
        this.date = date;
    }

    public String getCall1() {
        return call1;
    }

    public void setCall1(String call1) {
        this.call1 = call1;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int compareTo(AnalysisKey key) {
        int reslut = call1.compareTo(key.getCall1());
        if (reslut==0){
            date.compareTo(key.getDate());
        }

        return reslut;
    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(call1);
        out.writeUTF(date);



    }

    public void readFields(DataInput in) throws IOException {

        call1=in.readUTF();
        date=in.readUTF();

    }
}
