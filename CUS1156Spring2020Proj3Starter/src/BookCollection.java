abstract class BookCollection 
{
	public abstract void load(String filepath);
	public abstract String toString();
	public abstract boolean contains(String callNum);
	public abstract Book book(String callNum);
}
