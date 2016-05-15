package top.jjust.bean;

public abstract class Bean {
	protected long lastDate;
	public abstract void destory();
	public abstract boolean checkTime();
	@Override
	public abstract String toString();
}
