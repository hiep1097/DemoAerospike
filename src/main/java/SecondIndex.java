import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.query.Filter;
import com.aerospike.client.query.IndexType;
import com.aerospike.client.query.RecordSet;
import com.aerospike.client.query.Statement;
import com.aerospike.client.task.IndexTask;

public class SecondIndex {

    public static void main(String[] args) {
        AerospikeClient client = new AerospikeClient("172.28.128.4", 3000);
        //init index
//        IndexTask task = client.createIndex(null, "test", "demo",
//                "idx_test_demo", "bin", IndexType.STRING);
//
//        task.waitTillComplete();
       // client.dropIndex(null,"test","demo","idx_test_demo");


        for (int i=1;i<=100;i++){
            Key key = new Key("test","demo",i);
            Bin bin;
            if (i%2==0) bin = new Bin("bin",i);
            else bin = new Bin("bin",i+"");
            client.put(null,key,bin);
        }


        Statement stmt = new Statement();
        stmt.setNamespace("test");
        stmt.setSetName("demo");
        stmt.setFilter(Filter.equal("bin",2));

        RecordSet rs = client.query(null, stmt);

        try {
            while (rs.next()) {
                Key key = rs.getKey();
                Record record = rs.getRecord();
                System.out.println(record.getString("bin"));
            }
        }
        finally {
            rs.close();
        }
    }
}
