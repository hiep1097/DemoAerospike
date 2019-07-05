import com.aerospike.client.*;
import com.aerospike.client.cdt.ListOperation;
import com.aerospike.client.cdt.ListOrder;
import com.aerospike.client.cdt.ListReturnType;
import com.aerospike.client.policy.WritePolicy;

import java.util.ArrayList;
import java.util.List;

public class DemoListOperation {
    public static void main(String[] args) {
        AerospikeClient client = new AerospikeClient("172.28.128.5", 3000);
        Key key = new Key("test", "demo", "listkey");
        String binName = "listbin";
        // Delete record if it already exists.
        client.delete(new WritePolicy(), key);
        //init list
        List<Value> inputList = new ArrayList<Value>();
        inputList.add(Value.get(3));
        inputList.add(Value.get(1));
        inputList.add(Value.get("2"));
        inputList.add(Value.get(4));

        // Write values to empty list.
        Record record = client.operate(new WritePolicy(), key,
                ListOperation.appendItems(binName, inputList)
        );

        long size = record.getLong(binName);

        System.out.println(size);

        client.operate(new WritePolicy(), key,
                ListOperation.setOrder(binName, ListOrder.ORDERED)
        );

        record = client.operate(new WritePolicy(), key,
                ListOperation.removeByIndex(binName,-1,ListReturnType.VALUE)
        );

        long index = record.getLong(binName);

        System.out.println(index);
    }
}
