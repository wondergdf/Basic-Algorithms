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