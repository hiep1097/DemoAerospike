import com.aerospike.client.*;
import com.aerospike.client.policy.*;

import java.util.Map;
import java.util.Set;

public class Readwrite {
    public static void main(String[] args) {
//        AerospikeClient client = new AerospikeClient("172.28.128.4", 3000);

        Host[] hosts = new Host[]{
                new Host("a.host", 3000),
                new Host("b.host", 3000),
                new Host("172.28.128.4", 3000),
        };

        ClientPolicy clientPolicy = new ClientPolicy();
        clientPolicy.readPolicyDefault.replica = Replica.MASTER_PROLES;
        clientPolicy.readPolicyDefault.consistencyLevel = ConsistencyLevel.CONSISTENCY_ALL;
        clientPolicy.readPolicyDefault.socketTimeout = 100;
        clientPolicy.readPolicyDefault.totalTimeout = 100;
        clientPolicy.writePolicyDefault.commitLevel = CommitLevel.COMMIT_ALL;
        clientPolicy.writePolicyDefault.socketTimeout = 500;
        clientPolicy.writePolicyDefault.totalTimeout = 500;
        AerospikeClient client = new AerospikeClient(clientPolicy, new Host("172.28.128.4", 3000));

        // Make a copy of the client's default write policy.
        WritePolicy policy = new WritePolicy(client.writePolicyDefault);

        // Change commit level.
        policy.commitLevel = CommitLevel.COMMIT_MASTER;

        Key key = new Key("test", "myset", "mykey");
        Bin bin = new Bin("mybin", "myvalue");

        // Write record with modified write policy.
        client.put(policy, key, bin);

//        // Initialize policy.
//        WritePolicy policy = new WritePolicy();
//        policy.setTimeout(50);
//
//
//        // Write single value.
//
//        client.put(null, key, bin);
//
//        // Write multiple values.
//        Bin bin1 = new Bin("name", "John");
//        Bin bin2 = new Bin("age", 24);
//        client.put(policy, key, bin1, bin2);
//
////        Bin bin3 = Bin.asNull("name"); // Set bin value to null to drop bin.
////        client.put(policy, key, bin3);
//
//
//        Record record = client.get(clientPolicy.readPolicyDefault, key);
//        System.out.println(record.bins.size());
//
//        // Record record = client.get(policy, key, "name", "age");
//
//        int size = 100;
//
//        Key[] keys = new Key[size];
//
//        for (int i = 0; i < size; i++) {
//            keys[i] = new Key("test", "myset", (i + 1));
//        }

        //       Record[] records = client.get(new BatchPolicy(),keys);

//        for (int i=1;i<=1;i++) System.out.println(records[i].bins.size());


//        Map<String, Object> bins = record.bins;
//        Set<String> set = bins.keySet();
//        for (String keyy : set) {
//            System.out.println(bins.get(keyy));
//        }


//        Key key = new Key("test", "myset", "mykey");
//        client.delete(policy, key);


    }
}
