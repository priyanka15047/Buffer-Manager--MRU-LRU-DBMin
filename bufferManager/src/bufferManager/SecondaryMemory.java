package bufferManager;

import java.util.Vector;

public class SecondaryMemory 
{
	Vector<Block> mem = new Vector<Block>();
	
	public SecondaryMemory(int En,int Eb,int Dn, int Db, int Pn,int Pb )
	{	
		for(int i=0;i<Eb;i++)
		{
			Block bE = new Block("E"+(i+1),En/Eb);
			mem.add(bE);
		}
		
		for(int j=0;j<Db;j++)
		{
			Block bD = new Block("D"+(j+1),Dn/Db); 
			mem.add(bD);
		}
		
		for(int k=0;k<Pb;k++)
		{
			Block bP = new Block("P"+(k+1),Pn/Pb); 
			mem.add(bP);
		}
		
		
		mem.add(new Block("EI0",0));
		
		for(int i=0;i<12;i++)
		{
			mem.add(new Block("EI1" +(i+1), 0));
		}
		
		for(int j=0;j<334;j++)
		{
			mem.add(new Block("EI2"+ (j+1), 0));
		}
		
	    mem.add(new Block("DI0", 0));
	    for(int k=0;k<4;k++)
	    {
	    	mem.add(new Block("DI1" + (k+1),0));
	    }
		
		
		
		
		
		
		
		
	}
	
}
