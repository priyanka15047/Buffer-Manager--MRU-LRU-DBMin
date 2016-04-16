package bufferManager;

public class MemLocation 
{
	String memLocation_id;
	int lastUsed;
	static int mem_id=1;
	
	public MemLocation() 
	{
	   this.memLocation_id = "L" + mem_id;
	   this.mem_id++;
	   this.lastUsed = 0;
	}
}
