package finddates;

import javax.naming.Context;
import javax.xml.soap.Text;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class Mapper1 extends Mapper1<Object, Text, IntWritable> {
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("MM/dd/yyyy");
        String[] days ={"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
        private Text basement = new Text() {
            @Override
            public boolean isComment() {
                return false;
            }
        };
        Date date = null;
        private int trips;
        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
            String line = value.toString();
            String[] splits = line.split(",");
            basement.set(splits[0]);
            try {
                date = format.parse(splits[1]);
            } catch (ParseException e) {

                e.printStackTrace();
            }
            trips = new Integer(splits[3]);
            String keys = basement.toString()+ " "+days[date.getDay()];
            context.write(new Text(keys), new IntWritable(trips));
        }
    }

