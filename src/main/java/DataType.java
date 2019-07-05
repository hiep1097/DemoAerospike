import com.aerospike.client.*;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.WritePolicy;

import java.util.*;

public class DataType {

    public static void main(String[] args) {
        AerospikeClient client = new AerospikeClient("172.28.128.4", 3000);
        // Initialize policy.
        WritePolicy policy = new WritePolicy();
        policy.setTimeout(50);

        //write
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"abc");
        map.put(2,"def");
        map.put(3,"ghi");
        Key key = new Key("test", "myset", "mykey");
        Bin bin1 = new Bin("mybin1",map);
        client.put(policy, key, bin1);

        //read
        Record record = client.get(policy, key);
        HashMap<Integer,String> m = (HashMap<Integer, String>) record.getMap("mybin1");
        Set<Integer> set = m.keySet();
        for (Object k : set) {
            System.out.println((long)k+" "+m.get(k));
        }
    }
}


