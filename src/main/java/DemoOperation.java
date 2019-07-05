import com.aerospike.client.*;
import com.aerospike.client.cdt.ListOperation;
import com.aerospike.client.policy.WritePolicy;

public class DemoOperation {
    public static void main(String[] args) {
        AerospikeClient client = new AerospikeClient("172.28.128.5", 3000);
        // Write initial record.
        Key key = new Key("test", "demo", "opkey");
        Bin bin1 = new Bin("optintbin", 7);
        Bin bin2 = new Bin("optstringbin", "string value");
        client.put(new WritePolicy(), key, bin1, bin2);

        Record record = client.get(new WritePolicy(),key);
        System.out.println(record.getInt("optintbin"));
        System.out.println(record.getString("optstringbin"));

        // Add integer, write new string and read record.
        Operation operation = new Operation(Operation.Type.ADD,bin1.name,Value.get(5));
        Bin bin3 = new Bin(bin2.name, "new string");

        record = client.operate(new WritePolicy(), key, operation, Operation.put(bin3), Operation.get());

        System.out.println(record.getInt("optintbin"));
        System.out.println(record.getString("optstringbin"));


    }
}




