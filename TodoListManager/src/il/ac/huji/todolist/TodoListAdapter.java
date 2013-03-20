package il.ac.huji.todolist;

import java.util.Calendar;
import java.util.List;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TodoListAdapter extends ArrayAdapter<Task> {
	
	public TodoListAdapter(TodoListManagerActivity activity, List<Task> tasks) {
		super(activity, android.R.layout.simple_list_item_1, tasks);
	}
	

	public View getView(int position, View convertView, ViewGroup parent) {
		
		Task task = getItem(position);
		
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View view = inflater.inflate(R.layout.row, null);
		
		TextView taskName = (TextView)view.findViewById(R.id.txtTodoTitle);
		TextView taskDueDate = (TextView)view.findViewById(R.id.txtTodoDueDate);
		
		taskName.setText(task.getName());
		taskDueDate.setText(task.getStrDueDate());
		
		do
		{
			if(task.getday() == -1)
			{
				break;
			}

			// cur date
			Calendar calendar = Calendar.getInstance();
			int curDay = calendar.get(Calendar.DAY_OF_MONTH);
			int curMonth = calendar.get(Calendar.MONTH) + 1;// January is 0
			int curYear = calendar.get(Calendar.YEAR);


			if(curYear > task.getYear() || (curYear == task.getYear() && curMonth > task.getMonth()) ||
					(curYear == task.getYear() && curMonth == task.getMonth() && curDay >= task.getday()))
			{
				taskName.setTextColor(Color.RED);
				taskDueDate.setTextColor(Color.RED);
			}
			else{
				taskName.setTextColor(Color.BLACK);
				taskDueDate.setTextColor(Color.BLACK);
			}
		}
		while(false);

		return view;
	}

}
