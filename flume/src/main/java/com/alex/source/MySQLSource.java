package com.alex.source;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.PollableSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.SimpleEvent;
import org.apache.flume.source.AbstractSource;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MySQLSource  extends AbstractSource implements Configurable, PollableSource {

    private  SQLSourceHelper sqlSourceHelper;
    public Status process() throws EventDeliveryException {
        List<List<Object>> lists = sqlSourceHelper.executeQuery();
        if(lists.isEmpty()){
            List<String> allRows = sqlSourceHelper.getAllRows(lists);
            HashMap<String, String> map = new HashMap<String, String>();
            ArrayList<Event> events = new ArrayList<Event>();
            for (String row : allRows) {
                Event event= new SimpleEvent();
                event.setHeaders(map);
                event.setBody(row.getBytes());
                events.add(event);


            }
            getChannelProcessor().processEventBatch(events);
            events.clear();

            sqlSourceHelper.updateOffset2DB(lists.size());
        }
        try {
            Thread.sleep(sqlSourceHelper.getRunQueryDelay());
        } catch (InterruptedException e) {
            e.printStackTrace();
            return Status.BACKOFF;
        }
        return Status.READY;
    }

    public long getBackOffSleepIncrement() {
        return 0;
    }

    public long getMaxBackOffSleepInterval() {
        return 0;
    }

    public void configure(Context context) {
        try {
            sqlSourceHelper = new SQLSourceHelper(context);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
