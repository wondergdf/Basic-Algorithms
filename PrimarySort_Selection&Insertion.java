Class Selection_Sort
{
	public static void  sort(Comparable[] data)
	{
		for (int i=0; i<data.length; i++) {
			
			int min = i;

			for (int j=i+1; j<data.length; j++) {
				
				if(less(data[j],data[min])){
					min = j;
				}	
			}

            exch(data, i, min);//this exchange function will execute with each loop
		}

	}
}

Class Insertion_Sort
{
	public static void  sort(Comparable[] data) 
	{
		for (int i=1; i<data.length; i++) {

			for(int j=i; j>0 && less(data[j],data[j-1]);j--)
			{
				exch(data, j, j-1); // this exchange not execute with each loop
			}
			
		}
		
	}
}

Class Shell_Sort
{// extend the insertion sort 
	public static void sort (Comparable[] data)
	{
		int length = data.length;
		int h =1 ;
		while(h<length/3) {h = h*3 +1 ;}
		while(h>=1)
		{
			for (int i=h; i<length ; i++) 
			{
				for (int j = i;j>=h && less(data[j],data[j-h]); j=j-h) 
				{
					exch(data,j,j-h);
				}
				
			}

			h=h/3;
		}
	}
}