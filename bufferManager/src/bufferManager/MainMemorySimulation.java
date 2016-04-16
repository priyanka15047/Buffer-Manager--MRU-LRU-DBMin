package bufferManager;

import java.util.HashMap;
import java.util.Vector;

public class MainMemorySimulation 
{
	HashMap<String,Integer> pageTable;
	Vector<MemLocation> buffer ;
	static int global_clock = 0;
	 int pageFault = 0;
	int noOfBuffer = 50;
	 int memToBeAllocated = 0;
	
	public MainMemorySimulation() 
	{
		pageTable = new HashMap<String,Integer>();
		buffer = new Vector<MemLocation>();
		for(int i=0;i<noOfBuffer;i++)
		{
			buffer.add(new MemLocation());
		}
		
	}
	
	public boolean checkForAvailability(Block block) 
	{
		if(pageTable.containsKey(block.block_id))
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public void getBlockFromSecMemLRU(Block block) 
	{	
	
		if(pageTable.size()<noOfBuffer)
		{   
			pageTable.put(block.block_id,memToBeAllocated);
			
		
		/*	for(String key : pageTable.keySet())
			{
				System.out.println(key + " : " + pageTable.get(key));
			}*/
			global_clock ++;
			buffer.get(memToBeAllocated).lastUsed = global_clock;
			memToBeAllocated++;
			
		}
		else
		{		
			//find the min lastUsed time
			int min = 100000;
			int minLocation = 0;
			for(int x=0;x<noOfBuffer;x++)
			{	
				if(min > buffer.get(x).lastUsed)
				{
					min = buffer.get(x).lastUsed;
					minLocation = x;
				}
			}
			
			// remove entry from the pageTable 
			String deletekey = "";
			for(String key : pageTable.keySet())
			{   
				if(pageTable.get(key)==minLocation)
				{
					deletekey = key;
					
				}
			}
			pageTable.remove(deletekey,pageTable.get(deletekey));
			
			// put the latest block
			pageTable.put(block.block_id,minLocation);
			global_clock ++;
			for(int y=0;y<noOfBuffer;y++)
			{
				if(y==minLocation)
				{
					buffer.get(y).lastUsed=global_clock;
				}
			}
			
			
		}
		
	}
	
	public void getBlockFromSecMemMRU(Block block) 
	{	
	//	System.out.println(pageTable.size());
		if(pageTable.size()<noOfBuffer)
		{   
			pageTable.put(block.block_id,memToBeAllocated);
			global_clock ++;
			buffer.get(memToBeAllocated).lastUsed = global_clock;
			memToBeAllocated++;
			
		}
		
		else
		{	
			//find the max lastUsed time
			int max = 0;
			int maxLocation = 0;
			for(int x=0;x<noOfBuffer;x++)
			{ //	System.out.print(" "+buffer.get(x).lastUsed);
				if(max < buffer.get(x).lastUsed)
				{   
					max = buffer.get(x).lastUsed;
					maxLocation = x;
				}
			}
			
			
			// remove entry from the pageTable 
			String deletekey = "";
			for(String key : pageTable.keySet())
			{   
				if(pageTable.get(key) == maxLocation)
				{
					deletekey = key;
					
				}
			}
			
			pageTable.remove(deletekey);
			
			// put the latest block
			pageTable.put(block.block_id,maxLocation);
			global_clock ++;
			for(int y=0;y<noOfBuffer;y++)
			{
				if(y == maxLocation)
				{
					buffer.get(y).lastUsed=global_clock;
				}
			}
			
		}
		
	}
	
	public void updateLastUsed(Block block) 
	{	int location=0;
		for(String key: pageTable.keySet())
		{
			if(key.equals(block.block_id))
			{
				location= pageTable.get(key);
			}
		}
		global_clock++;
		buffer.get(location).lastUsed=global_clock;
		
	}

	public String getToBeReplaced(Block block) {
		int max = 0;
		int maxLocation = 0;
		for(int x=0;x<noOfBuffer;x++)
		{ //	System.out.print(" "+buffer.get(x).lastUsed);
			if(max < buffer.get(x).lastUsed)
			{   
				max = buffer.get(x).lastUsed;
				maxLocation = x;
			}
		}
		
		
		// remove entry from the pageTable 
		String deletekey = "";
		for(String key : pageTable.keySet())
		{   
			if(pageTable.get(key) == maxLocation)
			{
				deletekey = key;
				
			}
		}
		return deletekey;
	}

	public void getBlockOnto2ndPlace(Block block) 
	{	
		int max = 0;
		int maxLocation = 0;
		for(int x=0;x<noOfBuffer;x++)
		{ //	System.out.print(" "+buffer.get(x).lastUsed);
			if(max < buffer.get(x).lastUsed)
			{   
				max = buffer.get(x).lastUsed;
				maxLocation = x;
			}		}
		
		int Max2 = 0;
		int Location2 =0;
		for(int x=0;x<noOfBuffer;x++)
		{ //	System.out.print(" "+buffer.get(x).lastUsed);
			if(Max2 < buffer.get(x).lastUsed && buffer.get(x).lastUsed < max)
			{   
				Max2 = buffer.get(x).lastUsed;
				Location2 = x;
			}
		}
		
		// remove entry from the pageTable 
					String deletekey = "";
					for(String key : pageTable.keySet())
					{   
						if(pageTable.get(key) == Location2)
						{
							deletekey = key;
							
						}
					}
					
					pageTable.remove(deletekey);
					
					// put the latest block
					pageTable.put(block.block_id,Location2);
					global_clock ++;
					for(int y=0;y<noOfBuffer;y++)
					{
						if(y == Location2)
						{
							buffer.get(y).lastUsed=global_clock;
						}
					}
			}
	}
			