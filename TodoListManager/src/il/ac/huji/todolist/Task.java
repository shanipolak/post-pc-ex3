package il.ac.huji.todolist;

import java.util.Calendar;
import java.util.Date;

public class Task {

	private String _name;
	private int _day;
	private int _month;
	private int _year;
	private boolean _noDate;
	
	public Task(String name, Date date)
	{
		_name = name;
		
		//get day, month, year from date
		if(date != null)
		{
			_noDate = false;
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			_day = cal.get(Calendar.DAY_OF_MONTH);
			_month = cal.get(Calendar.MONTH);
			_year = cal.get(Calendar.YEAR);
		}
		else
		{
			_noDate = true;
			_day = -1;
			_month =-1;
			_year = -1;
		}
	}
	
	public String getName()
	{
		return _name;
	}
	
	public int getday()
	{
		return _day;
	}
	
	public int getMonth()
	{
		return _month;
	}
	
	public int getYear()
	{
		return _year;
	}
	
	public String getStrDueDate()
	{
		if(_noDate)
		{
			return "No due date";
		}
		return String.valueOf(_day) + "/" + String.format("%02d", _month) +"/" + String.valueOf(_year);
	}
	
}
