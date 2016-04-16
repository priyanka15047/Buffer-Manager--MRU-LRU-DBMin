package bufferManager;

import java.awt.print.Pageable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Random;

public class CodeRunner {

	public static void main(String arg[])
	{
		int noOfBufer = 50;
		// gererate secondary memory		
		SecondaryMemory secondary_memory = new SecondaryMemory(10000,1000,100,25,5000,500);

		//initialize main memory
		MainMemorySimulation mainMem = new MainMemorySimulation();



		// 1st Query Execution in LRU................................................................
		for(int i=1000;i<1025;i++)
		{	

			for(int j=0;j<1000;j++)
			{        
				if(!mainMem.checkForAvailability(secondary_memory.mem.get(i)))
				{   
					mainMem.pageFault++;
					mainMem.getBlockFromSecMemLRU(secondary_memory.mem.get(i));
				}
				else
				{   
					mainMem.updateLastUsed(secondary_memory.mem.get(i));
				}

				if(!mainMem.checkForAvailability(secondary_memory.mem.get(j)))
				{  
					mainMem.pageFault++;
					mainMem.getBlockFromSecMemLRU(secondary_memory.mem.get(j));
				}
				else
				{
					mainMem.updateLastUsed(secondary_memory.mem.get(j));
				}


			}
		}

		System.out.println("Q1 : " + mainMem.pageFault);
		mainMem.pageFault=0;
		mainMem.memToBeAllocated=0;
		mainMem.pageTable = new HashMap<String,Integer>();


		// Q2 Query Execution in LRU................................................................
		for(int i=1000;i<1025;i++)
		{	

			for(int j=1025;j<1525;j++)
			{       if(!mainMem.checkForAvailability(secondary_memory.mem.get(i)))
			{  
				mainMem.pageFault++;
				mainMem.getBlockFromSecMemLRU(secondary_memory.mem.get(i));
			}
			else
			{
				mainMem.updateLastUsed(secondary_memory.mem.get(i));
			}


			if(!mainMem.checkForAvailability(secondary_memory.mem.get(j)))
			{   

				mainMem.pageFault++;
				mainMem.getBlockFromSecMemLRU(secondary_memory.mem.get(j));

			}

			}
		}

		System.out.println("Q2 : " + mainMem.pageFault);
		mainMem.pageFault=0;
		mainMem.memToBeAllocated=0;
		mainMem.pageTable = new HashMap<String,Integer>();
		Random random = new Random();

		// Q3 Query Execution in LRU................................................................
		for(int i=1025;i<1525;i++)
		{	

			for(int j=0;j<10;j++)
			{	  
				if(!mainMem.checkForAvailability(secondary_memory.mem.get(i)))
				{   
					mainMem.pageFault++;
					mainMem.getBlockFromSecMemLRU(secondary_memory.mem.get(i));
				}
				else
				{   
					mainMem.updateLastUsed(secondary_memory.mem.get(i));
				}
				int a=0;
				if(!mainMem.checkForAvailability(secondary_memory.mem.get(1872)))
				{
					mainMem.pageFault++;
					mainMem.getBlockFromSecMemLRU(secondary_memory.mem.get(1872)); 
				}
				else
				{
					mainMem.updateLastUsed(secondary_memory.mem.get(1872)); 
				}
				a= 1873 + random.nextInt(4);

				if(!mainMem.checkForAvailability(secondary_memory.mem.get(a)))
				{
					mainMem.pageFault++;
					mainMem.getBlockFromSecMemLRU(secondary_memory.mem.get(a));
				}
				else
				{
					mainMem.updateLastUsed(secondary_memory.mem.get(a)); 
				}

				a = 1000 + random.nextInt(25);

				if(!mainMem.checkForAvailability(secondary_memory.mem.get(a)))
				{
					mainMem.pageFault++;

					mainMem.getBlockFromSecMemLRU(secondary_memory.mem.get(a));
				}
				else
				{
					mainMem.updateLastUsed(secondary_memory.mem.get(a)); 
				}

			}
		}
		System.out.println("Q3 : " + mainMem.pageFault);
		mainMem.pageFault=0;
		mainMem.memToBeAllocated=0;
		mainMem.pageTable = new HashMap<String,Integer>();

		// Q4 Query Execution in LRU................................................................
		for(int i=1000;i<1025;i++)
		{   
			for(int j=0;j<4;j++)
			{	
				if(!mainMem.checkForAvailability(secondary_memory.mem.get(i)))
				{   
					mainMem.pageFault++;
					mainMem.getBlockFromSecMemLRU(secondary_memory.mem.get(i));
				}
				else
				{   
					mainMem.updateLastUsed(secondary_memory.mem.get(i));
				}
				int a=0;
				if(!mainMem.checkForAvailability(secondary_memory.mem.get(1525)))
				{
					mainMem.pageFault++;
					mainMem.getBlockFromSecMemLRU(secondary_memory.mem.get(1525)); 
				}
				else
				{
					mainMem.updateLastUsed(secondary_memory.mem.get(1525)); 
				}
				a= 1526 + random.nextInt(12);

				if(!mainMem.checkForAvailability(secondary_memory.mem.get(a)))
				{
					mainMem.pageFault++;
					mainMem.getBlockFromSecMemLRU(secondary_memory.mem.get(a));
				}
				else
				{
					mainMem.updateLastUsed(secondary_memory.mem.get(a)); 
				}

				a = 1538 + random.nextInt(334);

				if(!mainMem.checkForAvailability(secondary_memory.mem.get(a)))
				{
					mainMem.pageFault++;

					mainMem.getBlockFromSecMemLRU(secondary_memory.mem.get(a));
				}
				else
				{
					mainMem.updateLastUsed(secondary_memory.mem.get(a)); 
				}
				a = 0 + random.nextInt(1000);

				if(!mainMem.checkForAvailability(secondary_memory.mem.get(a)))
				{
					mainMem.pageFault++;

					mainMem.getBlockFromSecMemLRU(secondary_memory.mem.get(a));
				}
				else
				{
					mainMem.updateLastUsed(secondary_memory.mem.get(a)); 
				}
				/* if(!mainMem.checkForAvailability(secondary_memory.mem.get(i)))
					{   
			   			mainMem.pageFault++;
			   			mainMem.getBlockFromSecMemLRU(secondary_memory.mem.get(i));
					}*/
			}
		}
		System.out.println("Q4 : " + mainMem.pageFault);
		mainMem.pageFault=0;
		mainMem.memToBeAllocated=0;
		mainMem.pageTable = new HashMap<String,Integer>();

		//Q1 Query Execution in MRU.................................................................
		for(int i=1000;i<1025;i++)
		{
			for(int j=0;j<1000;j++)
			{      
				if(!mainMem.checkForAvailability(secondary_memory.mem.get(i)))
				{
					
					mainMem.pageFault++;
					mainMem.getBlockFromSecMemMRU(secondary_memory.mem.get(i));
				}
				else
				{
					
					mainMem.updateLastUsed(secondary_memory.mem.get(i));
				}

				if(!mainMem.checkForAvailability(secondary_memory.mem.get(j)))
				{  
					mainMem.pageFault++;
					if(mainMem.pageTable.size() < mainMem.noOfBuffer)
					{
						mainMem.getBlockFromSecMemMRU(secondary_memory.mem.get(j));
					}
					else
					{
						if(mainMem.getToBeReplaced(secondary_memory.mem.get(j)).equals(secondary_memory.mem.get(i).block_id))
						{
							
							mainMem.getBlockOnto2ndPlace(secondary_memory.mem.get(j));
						}
						else
						{
							mainMem.getBlockFromSecMemMRU(secondary_memory.mem.get(j));
						}
					}
					
				}
				else
				{  
					mainMem.updateLastUsed(secondary_memory.mem.get(j));
				}
			}
			
			
		}

		System.out.println("Q1 : " + mainMem.pageFault);
		mainMem.pageFault=0;
		mainMem.memToBeAllocated=0;
		mainMem.pageTable = new HashMap<String,Integer>();

		//Q2 Query Execution in MRU................................................................
		for(int i=1000;i<1025;i++)
		{	


			for(int j=1025;j<1525;j++)
			{      
				if(!mainMem.checkForAvailability(secondary_memory.mem.get(i)))
				{  
					mainMem.pageFault++;
					mainMem.getBlockFromSecMemMRU(secondary_memory.mem.get(i));
				}
				else
				{
					mainMem.updateLastUsed(secondary_memory.mem.get(i));
				}

				if(!mainMem.checkForAvailability(secondary_memory.mem.get(j)))
				{   

					mainMem.pageFault++;
					if(mainMem.pageTable.size() < mainMem.noOfBuffer)
					{
						mainMem.getBlockFromSecMemMRU(secondary_memory.mem.get(j));
					}
					else
					{
						if(mainMem.getToBeReplaced(secondary_memory.mem.get(j)).equals(secondary_memory.mem.get(i).block_id))
						{
							
							mainMem.getBlockOnto2ndPlace(secondary_memory.mem.get(j));
						}
						else
						{
							mainMem.getBlockFromSecMemMRU(secondary_memory.mem.get(j));
						}
					}

				}
				else
				{
					mainMem.updateLastUsed(secondary_memory.mem.get(j));
				}

			}
		}

		System.out.println("Q2 : " + mainMem.pageFault);
		mainMem.pageFault=0;
		mainMem.memToBeAllocated=0;
		mainMem.global_clock = 0;
		mainMem.pageTable = new HashMap<String,Integer>();
		int count =0;
		
		// Q3 Query Execution in MRU................................................................
		for(int i=1025;i<1525;i++)
		{	
			
		for(int j=0;j<10;j++)
		{	 if(!mainMem.checkForAvailability(secondary_memory.mem.get(i)))
			{  
				mainMem.pageFault++;
				mainMem.getBlockFromSecMemMRU(secondary_memory.mem.get(i));
			}
			else
			{
				mainMem.updateLastUsed(secondary_memory.mem.get(i));
			}

			int a=0;
			if(!mainMem.checkForAvailability(secondary_memory.mem.get(1872)))
			{
				mainMem.pageFault++;
				mainMem.getBlockFromSecMemMRU(secondary_memory.mem.get(1872)); 
			}
			else
			{
				mainMem.updateLastUsed(secondary_memory.mem.get(1872)); 
			}
			a= 1873 + random.nextInt(4);

			if(!mainMem.checkForAvailability(secondary_memory.mem.get(a)))
			{
				mainMem.pageFault++;
				mainMem.getBlockFromSecMemMRU(secondary_memory.mem.get(a));
			}
			else
			{
				mainMem.updateLastUsed(secondary_memory.mem.get(a)); 
			}
			
			a = 1000 + random.nextInt(25);
			if(!mainMem.checkForAvailability(secondary_memory.mem.get(a)))
			{
				mainMem.pageFault++;
				mainMem.getBlockFromSecMemMRU(secondary_memory.mem.get(a));
			}
			else
			{
				mainMem.updateLastUsed(secondary_memory.mem.get(a));
			}
			
			if(!mainMem.checkForAvailability(secondary_memory.mem.get(i)))
			{
				mainMem.pageFault++;
				if(mainMem.pageTable.size() < mainMem.noOfBuffer)
				{
					mainMem.getBlockFromSecMemMRU(secondary_memory.mem.get(i));
				}
				else
				{
					if(mainMem.getToBeReplaced(secondary_memory.mem.get(i)).equals(secondary_memory.mem.get(a).block_id))
					{
						
						mainMem.getBlockOnto2ndPlace(secondary_memory.mem.get(i));
					}
					else
					{
						mainMem.getBlockFromSecMemMRU(secondary_memory.mem.get(i));
					}
				}
			 }
			
			
			

		}
	}

		System.out.println("Q3 : " + mainMem.pageFault);
		mainMem.pageFault=0;
		mainMem.memToBeAllocated=0;
		mainMem.pageTable = new HashMap<String,Integer>();

		// Q4 Query Execution in MRU................................................................
		for(int i=1000;i<1025;i++)
		{	if(!mainMem.checkForAvailability(secondary_memory.mem.get(i)))
			{  
				mainMem.pageFault++;
				mainMem.getBlockFromSecMemMRU(secondary_memory.mem.get(i));
			}
			else
			{
				mainMem.updateLastUsed(secondary_memory.mem.get(i));
			}
		for(int j=0;j<4;j++)
		{	
			int a=0;
			if(!mainMem.checkForAvailability(secondary_memory.mem.get(1525)))
			{
				mainMem.pageFault++;
				mainMem.getBlockFromSecMemMRU(secondary_memory.mem.get(1525)); 
			}
			else
			{
				mainMem.updateLastUsed(secondary_memory.mem.get(1525)); 
			}
			a= 1526 + random.nextInt(12);

			if(!mainMem.checkForAvailability(secondary_memory.mem.get(a)))
			{
				mainMem.pageFault++;
				mainMem.getBlockFromSecMemMRU(secondary_memory.mem.get(a));
			}
			else
			{
				mainMem.updateLastUsed(secondary_memory.mem.get(a)); 
			}

			a = 1538 + random.nextInt(334);

			if(!mainMem.checkForAvailability(secondary_memory.mem.get(a)))
			{
				mainMem.pageFault++;
				mainMem.getBlockFromSecMemMRU(secondary_memory.mem.get(a));
			}
			else
			{
				mainMem.updateLastUsed(secondary_memory.mem.get(a)); 
			}
		
			/*if(!mainMem.checkForAvailability(secondary_memory.mem.get(i)))
			{	  
				mainMem.pageFault++;
				mainMem.getBlockFromSecMemMRU(secondary_memory.mem.get(i));
			}
			else
			{
				mainMem.updateLastUsed(secondary_memory.mem.get(i));
			}*/
			a = 0 + random.nextInt(1000);
			if(!mainMem.checkForAvailability(secondary_memory.mem.get(a)))
			{	  
				mainMem.pageFault++;
				mainMem.getBlockFromSecMemMRU(secondary_memory.mem.get(a));
			}
			else
			{
				mainMem.updateLastUsed(secondary_memory.mem.get(a));
			}
			
			if(!mainMem.checkForAvailability(secondary_memory.mem.get(i)))
			{
				mainMem.pageFault++;
				if(mainMem.pageTable.size() < mainMem.noOfBuffer)
				{
					mainMem.getBlockFromSecMemMRU(secondary_memory.mem.get(i));
				}
				else
				{
					if(mainMem.getToBeReplaced(secondary_memory.mem.get(i)).equals(secondary_memory.mem.get(a).block_id))
					{
					
						mainMem.getBlockOnto2ndPlace(secondary_memory.mem.get(i));
					}
					else
					{
						mainMem.getBlockFromSecMemMRU(secondary_memory.mem.get(i));
					}
			}
		}
		else
		{
			mainMem.updateLastUsed(secondary_memory.mem.get(i)); 
		}
		
	}
}
		System.out.println("Q4 : " + mainMem.pageFault);
		mainMem.pageFault=0;
		mainMem.memToBeAllocated=0;
		mainMem.pageTable = new HashMap<String,Integer>();

		// Q1 Execution in DBMin...........................................................
		DBMINSimulator dbMin = new DBMINSimulator(1,noOfBufer);
		for(int i=1000;i<1025;i++)
		{	

			if(!dbMin.checkForAvailability(secondary_memory.mem.get(i)))
			{  
				dbMin.pageFault++;
				dbMin.getBloickFromSecMemMRU(secondary_memory.mem.get(i),0);
			}
			else
			{
				dbMin.updateLastUsed(secondary_memory.mem.get(i),0);
			}

			for(int j=0;j<1000;j++)
			{      
				if(!dbMin.checkForAvailability(secondary_memory.mem.get(j)))
				{   
					dbMin.pageFault++;
					dbMin.getBloickFromSecMemMRU(secondary_memory.mem.get(j),1);

				}
				else
				{
					dbMin.updateLastUsed(secondary_memory.mem.get(j),1);
				}
			}
		}

		System.out.println("Q1: " + dbMin.pageFault);
		dbMin.pageFault=0;
		dbMin.memToBeAllocated=0;
		dbMin.pageTable = new HashMap<String,String>();


		// Q2 Execution in DBMIn........................................................
		dbMin = new DBMINSimulator(2,noOfBufer);
		for(int i=1000;i<1025;i++)
		{	
			if(!dbMin.checkForAvailability(secondary_memory.mem.get(i)))
			{  
				dbMin.pageFault++;
				dbMin.getBloickFromSecMemMRU(secondary_memory.mem.get(i),0);
			}
			else
			{
				dbMin.updateLastUsed(secondary_memory.mem.get(i),0);
			}
			for(int j=1025;j<1525;j++)
			{      

				if(!dbMin.checkForAvailability(secondary_memory.mem.get(j)))
				{   

					dbMin.pageFault++;
					dbMin.getBloickFromSecMemMRU(secondary_memory.mem.get(j),1);

				}
				else
				{
					dbMin.updateLastUsed(secondary_memory.mem.get(i),1);
				}

			}
		}

		System.out.println("Q2: " + dbMin.pageFault);
		dbMin.pageFault=0;
		dbMin.memToBeAllocated=0;
		dbMin.pageTable = new HashMap<String,String>();

		// Q3 Execution in DBMin......................................................

		dbMin = new DBMINSimulator(3,noOfBufer);
		Block lastEntered = null;
		for(int i=1025;i<1525;i++)
		{	
			if(!dbMin.checkForAvailability(secondary_memory.mem.get(i)))
			{  
				dbMin.pageFault++;
				dbMin.getBlockFromSecLIFO(secondary_memory.mem.get(i),0,null);

			}


			for(int j=0;j<10;j++)
			{	int a=0;
			if(!dbMin.checkForAvailability(secondary_memory.mem.get(1872)))
			{
				dbMin.pageFault++;
				dbMin.getBlockFromSecLIFO(secondary_memory.mem.get(1872), 1, lastEntered); 
				lastEntered = secondary_memory.mem.get(1872);
			}
			else
			{	
				dbMin.getThePageInLocalSet(secondary_memory.mem.get(1872),lastEntered);

			}

			a= 1873 + random.nextInt(4);

			if(!dbMin.checkForAvailability(secondary_memory.mem.get(a)))
			{
				dbMin.pageFault++;
				dbMin.getBlockFromSecLIFO(secondary_memory.mem.get(a), 1, lastEntered);
				lastEntered = secondary_memory.mem.get(a);
			}
			else
			{	 
				dbMin.getThePageInLocalSet(secondary_memory.mem.get(a),lastEntered);

			}

			a = 1000 + random.nextInt(25);

			if(!dbMin.checkForAvailability(secondary_memory.mem.get(a)))
			{
				dbMin.pageFault++;
				dbMin.getBlockFromSecLIFO(secondary_memory.mem.get(a), 1, lastEntered);
				lastEntered = secondary_memory.mem.get(a);
			}
			else
			{	 
				dbMin.getThePageInLocalSet(secondary_memory.mem.get(a),lastEntered);

			}
			}

		}
		System.out.println("Q3: " + dbMin.pageFault);

		dbMin.pageFault=0;
		dbMin.memToBeAllocated=0;
		dbMin.pageTable = new HashMap<String,String>();

		// Q4 Query Execution in DBMin................................................................
		dbMin = new DBMINSimulator(4,noOfBufer);
		lastEntered=null;
		for(int i=1000;i<1025;i++)
		{   if(!dbMin.checkForAvailability(secondary_memory.mem.get(i)))
		{  
			dbMin.pageFault++;
			dbMin.getBlockFromSecLIFO(secondary_memory.mem.get(i),0,null);

		}

		for(int j=0;j<4;j++)
		{	int a=0;
		if(!dbMin.checkForAvailability(secondary_memory.mem.get(1525)))
		{
			dbMin.pageFault++;
			dbMin.getBlockFromSecLIFO(secondary_memory.mem.get(1525),1,lastEntered); 
			lastEntered = secondary_memory.mem.get(1525);
		}
		else
		{	
			dbMin.getThePageInLocalSet(secondary_memory.mem.get(1525),lastEntered);

		}

		a= 1526 + random.nextInt(12);

		if(!dbMin.checkForAvailability(secondary_memory.mem.get(a)))
		{
			dbMin.pageFault++;

			dbMin.getBlockFromSecLIFO(secondary_memory.mem.get(a),1,lastEntered);
			lastEntered = secondary_memory.mem.get(a);
		}
		else
		{	 
			dbMin.getThePageInLocalSet(secondary_memory.mem.get(a),lastEntered);

		}


		a = 1538 + random.nextInt(334);

		if(!dbMin.checkForAvailability(secondary_memory.mem.get(a)))
		{
			dbMin.pageFault++;

			dbMin.getBlockFromSecLIFO(secondary_memory.mem.get(a),1,lastEntered);
			lastEntered = secondary_memory.mem.get(a);
		}
		else
		{	 
			dbMin.getThePageInLocalSet(secondary_memory.mem.get(a),lastEntered);

		}
		a = 0 + random.nextInt(1000);

		if(!dbMin.checkForAvailability(secondary_memory.mem.get(a)))
		{
			dbMin.pageFault++;

			dbMin.getBlockFromSecLIFO(secondary_memory.mem.get(a),1,lastEntered);
			lastEntered = secondary_memory.mem.get(a);
		}
		else
		{	 
			dbMin.getThePageInLocalSet(secondary_memory.mem.get(a),lastEntered);

		}


		}
		}
		System.out.println("Q4 : " + dbMin.pageFault);
		dbMin.pageFault=0;
		dbMin.memToBeAllocated=0;
		dbMin.pageTable = new HashMap<String,String>();


	}


}
