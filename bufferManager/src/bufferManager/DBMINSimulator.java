package bufferManager;

import java.util.HashMap;
import java.lang.*;
import java.util.Vector;

public class DBMINSimulator {
	
	HashMap<String,String> pageTable;
	int Localset1;
	int Localset2;
	Vector<MemLocation> GlobalList;
	Vector<MemLocation> OuterRelation ;
	Vector<MemLocation> InnerRelation ;
	int noOfBuffer;
	static int global_clock;
	static int memToBeAllocated = 0;
	static int pageFault = 0 ; 
	
	public DBMINSimulator(int Query, int BufferSize) {
		// TODO Auto-generated constructor stub
		global_clock = 0;
		noOfBuffer = BufferSize;
		GlobalList = new Vector<MemLocation>();
		for(int i=0;i<BufferSize ; i++)
		{ GlobalList.add(new MemLocation());  
		}
		
		
		if(Query == 1){
			
			Localset1 = 1;
			Localset2 = BufferSize - 1;
			OuterRelation = new Vector<MemLocation>();
			InnerRelation = new Vector<MemLocation>();
			pageTable= new HashMap<String, String>();
		}
		else if(Query == 2){
			
			Localset1 = 1;
			Localset2 = BufferSize - 1;
			OuterRelation = new Vector<MemLocation>();
			InnerRelation = new Vector<MemLocation>();
			pageTable= new HashMap<String, String>();
		
			
		}
		else if(Query == 3)
		{ 
			Localset1 = 1;
			int sum = 0;
			int k = 5000;
			int[] m = {4};
			int[] n = {100};
			int[] b = new int[m.length];
			b=calculateYaoFormula(k,m,n);
			
			for(int i=0;i<b.length;i++)
			{
				sum = sum + b[i];
			}
			
			Localset2 = 1 + sum + 1;
			if(Localset2>noOfBuffer-Localset1)
			{
				Localset2 = noOfBuffer - Localset1;
			}
			OuterRelation = new Vector<MemLocation>();
			InnerRelation = new Vector<MemLocation>();
			pageTable= new HashMap<String, String>();
		}
		else if(Query == 4)
		{
			Localset1 = 1;
			int sum = 0;
			int k = 100;
			int[] m = {12,334};
			int[] n = {334,10000};
			int[] b = new int[m.length];
			b=calculateYaoFormula(k,m,n);
			for(int i=0;i<b.length;i++)
			{
				sum = sum + b[i];
			}
			
			Localset2 = 1 + sum + 1;
			if(Localset2>noOfBuffer-Localset1)
			{
				Localset2 = noOfBuffer - Localset1;
			}
			OuterRelation = new Vector<MemLocation>();
			InnerRelation = new Vector<MemLocation>();
			pageTable= new HashMap<String, String>();
			
		}
	}
	
	private int[] calculateYaoFormula(int k, int[] m, int[] n) 
	{  
		int[] b = new int[m.length];
		for(int i=0 ; i< m.length ; i++)
		{	
			float d = 1 - 1/(float)m[i];
			if(k > n[i] - n[i]/m[i])
			{	
				b[i] = m[i];
			}
			else
			{	float prod = 1f;
				float div ;
				for(int j = 1 ; j <=k ; j++)
				{	
					div = (n[i]*d - j + 1)/(n[i] - j + 1);
					prod = prod * div;
					
				}
				b[i] = (int)Math.ceil(m[i]*(1-prod));
			}
		}
		
		return (b);
		
		
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
	
	public void getBloickFromSecMemMRU(Block block,int flag) 
	{	if(flag==0)
		{
		   if(OuterRelation.size()<Localset1)
		   {
			   OuterRelation.add(GlobalList.get(memToBeAllocated));
			   global_clock++;
			   pageTable.put(block.block_id,"O" + OuterRelation.indexOf(GlobalList.get(memToBeAllocated)));
			   OuterRelation.get(OuterRelation.indexOf(GlobalList.get(memToBeAllocated))).lastUsed = global_clock;
			   memToBeAllocated++;
			   
		   }
		   else
		   {
			   // find the max lastUsed time
			   int max = 0;
			   int location = 0;
			   for(int i=0;i<Localset1;i++)
			   {
				   if(max < OuterRelation.get(i).lastUsed)
				   {
					   max = OuterRelation.get(i).lastUsed;
					   location = i;
				   }
			   }
			   
			// remove entry from the pageTable 
				String deletekey = "";
				for(String key : pageTable.keySet())
				{   
					if(pageTable.get(key).equals("O"+location))
					{
						deletekey = key;
					}
				}
				pageTable.remove(deletekey);
				
				// put the latest block
				pageTable.put(block.block_id,"O"+location);
				global_clock ++;
				for(int y=0;y<Localset1;y++)
				{
					if(y == location)
					{
						OuterRelation.get(y).lastUsed=global_clock;
					}
				}
				
		   }
		}
		else
		{	
			if(InnerRelation.size()<Localset2)
			   {	
				   InnerRelation.add(GlobalList.get(memToBeAllocated));
				   global_clock++;
				   pageTable.put(block.block_id,"I" + InnerRelation.indexOf(GlobalList.get(memToBeAllocated)));
				   InnerRelation.get(InnerRelation.indexOf(GlobalList.get(memToBeAllocated))).lastUsed = global_clock;
				   memToBeAllocated++;
				   
				   
			   }
			   else
			   {  
			   		
				   // find the max lastUsed time
				   int max = 0;
				   int location = 0;
				   for(int i=0;i<Localset2;i++)
				   {
					   if(max < InnerRelation.get(i).lastUsed)
					   {
						   max = InnerRelation.get(i).lastUsed;
						   location = i;
					   }
				   }
				   
				// remove entry from the pageTable 
					String deletekey = "";
					for(String key : pageTable.keySet())
					{   
						if(pageTable.get(key).equals("I"+location))
						{
							deletekey = key;
						}
					}
					pageTable.remove(deletekey);
					
					// put the latest block
					pageTable.put(block.block_id,"I"+location);
					global_clock ++;
					for(int y=0;y<Localset2;y++)
					{
						if(y == location)
						{
							InnerRelation.get(y).lastUsed=global_clock;
						}
					}
					
			   }
			
		}
		
	}
	public void updateLastUsed(Block block,int flag)
	{	int location =0;
		
		if(flag==0)
		{
			for(String key : pageTable.keySet())
			{
				if(key.equals(block.block_id))
				{
					location = Integer.parseInt(pageTable.get(key).substring(1));
					
				}
			}
			global_clock++;
			OuterRelation.get(location).lastUsed=global_clock;
			
		}
		else
		{
			for(String key : pageTable.keySet())
			{
				if(key.equals(block.block_id))
				{
					location = Integer.parseInt(pageTable.get(key).substring(1));
					
				}
			}
			global_clock++;
			InnerRelation.get(location).lastUsed=global_clock;
		}
		
	}

	

	public void getBlockFromSecLIFO(Block block, int flag, Block lastEntered)
	{
		if(flag==0)
		{	 
		   if(OuterRelation.size()<Localset1)
		   {
			   OuterRelation.add(GlobalList.get(memToBeAllocated));
			   pageTable.put(block.block_id,"O" + OuterRelation.indexOf(GlobalList.get(memToBeAllocated)));
			   OuterRelation.get(OuterRelation.indexOf(GlobalList.get(memToBeAllocated))).lastUsed = global_clock;
			   memToBeAllocated++;
			  
		   }
		   else
			{	
			   
			  	String value=OuterRelation.get(0).memLocation_id;
				String entryToDelet = "";
				for(String key : pageTable.keySet())
				{   
					if(pageTable.get(key).equals(value))
					{	
						entryToDelet = key;
						value= pageTable.get(key);
					}
				}
				
				pageTable.remove(entryToDelet);
				pageTable.put(block.block_id,value);
			}
				
		
		}
		else
		{   String value="";
			String entryToDelete="";
		
			if(InnerRelation.size()<Localset2)
			   {	
					InnerRelation.add(GlobalList.get(memToBeAllocated));
					global_clock++;
					pageTable.put(block.block_id,"I" + InnerRelation.indexOf(GlobalList.get(memToBeAllocated)));
					InnerRelation.get(InnerRelation.indexOf(GlobalList.get(memToBeAllocated))).lastUsed = global_clock;
					memToBeAllocated++;
					
			   }
			else
			{	
				for(String key : pageTable.keySet())
				{
					if(key.equals(lastEntered.block_id))
					{
						entryToDelete = key;
						value = pageTable.get(key);
					}
					
				}
				String entry = "";
				String entryValue = "";
				if(memToBeAllocated < noOfBuffer)
				{	/*System.out.println("memToAllocated" + memToBeAllocated);
					System.out.println("locationid" + GlobalList.get(memToBeAllocated).memLocation_id);*/
					pageTable.put(entryToDelete,GlobalList.get(memToBeAllocated).memLocation_id);
					memToBeAllocated++;
				
				}
				else
				{	entry = "";
					entryValue = "";
					for(String key: pageTable.keySet())
					{
						if(pageTable.get(key).equals(GlobalList.get(memToBeAllocated-1).memLocation_id))
						{
							entry = key;
							entryValue = pageTable.get(key);
						}
					}
					if(entry.equals(""))
					{
						pageTable.put(entryToDelete,GlobalList.get(memToBeAllocated-1).memLocation_id);
						
					}
					else
					{
						pageTable.remove(entry);
						pageTable.put(entryToDelete,GlobalList.get(memToBeAllocated-1).memLocation_id);
				
					}
					
				}
			
				pageTable.put(block.block_id,value);
			}
			
		}
		
	}
	

	public void getThePageInLocalSet(Block block, Block lastEntered) 
	{  
		String blockId="";
		String blockPossition="";
		String LastEnteredId = "";
		String LastEnteredPossition = "";
		int check =0;
		for(String keys: pageTable.keySet())
		{	
			if(keys.equals(block.block_id))
			{
				blockId = keys;
				blockPossition = pageTable.get(keys);
			}
		}
		
		 
			 if(blockPossition.charAt(0)=='I')
			{
				 check = 1;
			}
		 
		 
		 for(String keys: pageTable.keySet())
			{	
				if(keys.equals(lastEntered.block_id))
				{
					LastEnteredId = keys;
					LastEnteredPossition = pageTable.get(keys);
				}
			}
		 
		/* System.out.println("LastEnteredId" + LastEnteredId);
		 System.out.println("blockPossition" + blockPossition);
		 System.out.println("blockId" + blockId);
		 System.out.println("LastEnteredPossition" + LastEnteredPossition);
		 System.out.println("check" + check);*/
		 if(check==0)
		 {
			 pageTable.put(LastEnteredId,blockPossition);
			 pageTable.put(blockId,LastEnteredPossition);
		 }
	}
	
}
	
	
	


