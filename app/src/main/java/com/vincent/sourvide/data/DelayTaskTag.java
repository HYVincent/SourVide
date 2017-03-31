package com.vincent.sourvide.data;

public class DelayTaskTag 
{
	public int workMode;  //0����Ч��1�����Ρ�
	
	public int delayDay;
	public int delayDour;  //��ʱʱ��
	public int delayMin;
	public int delaySec;

	public static final int DELAY_TAG_SIZE=5;
	
	public DelayTaskTag() 
	{
		workMode = 0;
		delayDay = 1;
		delayDour = 2;
		delayMin = 3;
		delaySec = 0;
	}
	
	public DelayTaskTag(DelayTaskTag value) 
	{
		workMode = value.workMode;
		delayDay = value.delayDay;
		delayDour = value.delayDour;
		delayMin = value.delayMin;
		delaySec = value.delaySec;
	}
	
    public void clear()
    {
    	workMode = 0;
		delayDay = 0;
		delayDour = 0;
		delayMin = 0;
		delaySec = 0;
    }
}
