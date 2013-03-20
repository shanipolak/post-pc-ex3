package il.ac.huji.todolist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TodoListManagerActivity extends Activity {

	private ArrayAdapter<Task> adapter;
	ListView listTasks;
	static final String CALL = "Call ";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo_list_manager);
		
		List<Task> tasks = new ArrayList<Task>();
		listTasks =  (ListView)findViewById(R.id.lstTodoItems);
		
		adapter = new TodoListAdapter(this, tasks);
		listTasks.setAdapter(adapter);
		registerForContextMenu(listTasks);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.menuItemAdd:

			Intent intent = new Intent(this, AddNewTodoItemActivity.class);
			startActivityForResult(intent, 1337);		
		}
		return true;
	}
	
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		getMenuInflater().inflate(R.menu.contextmenu, menu);
		
		//set context menu title
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
		String selectedItem = (adapter.getItem(info.position)).getName();
		menu.setHeaderTitle(selectedItem);
		
		//call option
		MenuItem item = (MenuItem) menu.findItem(R.id.menuItemCall);
		if(selectedItem.startsWith(CALL))
		{
			item.setTitle(selectedItem);
		}
		else
		{
			menu.removeItem(R.id.menuItemCall);
		}

	}


	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		int selectedItemIndex = info.position;

		switch (item.getItemId()){
			case R.id.menuItemDelete:
			adapter.remove(adapter.getItem(selectedItemIndex));
			break;
		
			case R.id.menuItemCall:
				String phoneNum = parseCallTask(adapter.getItem(selectedItemIndex).getName());
				Intent dial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNum));
				startActivity(dial); 
		}
		return true;
	}

	//returns the phone number from a call task
	private String parseCallTask(String task) {
		
		return task.substring(CALL.length());
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1337 && resultCode == RESULT_OK) {
			String taskName = data.getStringExtra("title");
			Date dueDate = (Date)data.getSerializableExtra("dueDate");
			adapter.add(new Task(taskName, dueDate));
		}
	}

}