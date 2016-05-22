package com.uprm.History;

import java.util.Comparator;
import java.util.Date;

public class DateComparator implements Comparator{

	@Override
	public int compare(Object arg0, Object arg1) {
		// TODO Auto-generated method stub
		Date date1 = (Date)(arg0);
		Date date2 = (Date)(arg1);

		if(date1.before(date2))
			return -1;
		else
			if(date1.after(date2))
				return 1;
			else
				return 0;
	}


}
