import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.policy.WritePolicy;

import java.util.concurrent.ThreadLocalRandom;

public class DemoWrite {
    public static void main(String[] args) {
        // Default is to write 100,000 records
        int lowKeyVal = 0;
        int numKeys = 100000;

        AerospikeClient client = new AerospikeClient("172.28.128.4", 3000);
        int randomInt;
        Key key = null;
        long startTime = System.currentTimeMillis();
        for (int i = lowKeyVal; i < lowKeyVal + numKeys; i++) {
            key = new Key("test", "demo", i);
            randomInt = ThreadLocalRandom.current().nextInt(1, 100000);
            Bin int_bin = new Bin("intbin", randomInt);
            Bin str_bin = new Bin("strbin", String.valueOf(randomInt));
            client.put(new WritePolicy(), key, int_bin, str_bin);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("records write/sec: " + numKeys/((endTime - startTime)/1000));
        client.close();
    }
}

