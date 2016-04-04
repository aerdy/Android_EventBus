package necis.example.com.eventbusexamplenecis;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.format.Time;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by Jarcode on 2016-04-04.
 */
public class ChargingReceiver extends BroadcastReceiver {
    EventBus bus = EventBus.getDefault();
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Item item = null;

            Time now = new Time();
            now.setToNow();
            String timeOfEvent = now.format("%H:%M:%S");

            String eventData = "@" + timeOfEvent + " this device started ";
            if(intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
                item=new Item(eventData+"charging.");
            } else if(intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)){
                item=new Item(eventData+"discharging.");
            }
            Log.e("data",item.getData());
            bus.post(item);
        }catch (Exception e){
            Log.e("error",e.getMessage());

        }
    }
}
