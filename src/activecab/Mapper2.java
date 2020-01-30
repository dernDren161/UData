package activecab;

import javax.naming.Context;
import javax.xml.soap.Text;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class Mapper2 {
    public static class TokenizerMapper
            extends Mapper<Object, Text, Text, IntWritable>{
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("MM/dd/yyyy");
        String[] days ={"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
        private Text basement = new Text();
        Date date = null;
        private int active_vehicles;
        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
            String line = value.toString();
            String[] splits = line.split(",");
            basement.set(splits[0]);
            try {
                date = format.parse(splits[1]);
            } catch (ParseException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }
            active_vehicles = new Integer(splits[3]);
            String keys = basement.toString()+ " "+days[date.getDay()];
            context.write(new Text(keys), new IntWritable(active_vehicles));
        }
    }
}
