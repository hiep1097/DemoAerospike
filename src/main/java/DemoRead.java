import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.Policy;

import java.util.concurrent.ThreadLocalRandom;

public class DemoRead {
    public static void main(String[] args) {
        int lowKeyVal = 0;
        int numKeys = 100000;
        int numReads = 100000;

        AerospikeClient client = new AerospikeClient("172.28.128.4", 3000);
        int randomInt;
        Key key = null;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numReads; i++) {
            randomInt = ThreadLocalRandom.current().nextInt(lowKeyVal, lowKeyVal + numKeys);
            key = new Key("test", "demo", randomInt);
            Record record = client.get(new Policy(), key);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("records read/sec: " + numReads/((endTime - startTime)/1000));
        client.close();
    }
}

