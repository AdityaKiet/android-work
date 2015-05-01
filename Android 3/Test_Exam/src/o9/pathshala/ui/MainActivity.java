package o9.pathshala.ui;

import o9.pathshala.test_exam.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	ListView lv;
	TextView tv;
	protected void onCreate(Bundle savedInstanceState) {
		String []ids = {"1","2"};
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv= (ListView)findViewById(R.id.listView1);
		tv = (TextView)findViewById(R.id.txt);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, ids);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				tv.setText(position+"");
			}
		});
	}

}
