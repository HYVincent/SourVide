
package com.vincent.sourvide.data;


import java.io.Serializable;
import java.util.Arrays;

import org.json.JSONObject;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;



@DatabaseTable(tableName = "devices")
public class ManageDevice
    implements Serializable
{
	@DatabaseField
    private static final long serialVersionUID = 0x6926f1e1752fc6d4L;
	@DatabaseField
    private String city;
	@DatabaseField
    private String cityCode;
	@DatabaseField
    private int deviceLock;
    @DatabaseField(id = true)
    private String deviceMac;
    @DatabaseField
    private String deviceName;
    @DatabaseField
    private int devicePassword;
    @DatabaseField
    private short deviceType;
    @DatabaseField
    private double latitude;
    @DatabaseField
    private double longitude;
    @DatabaseField
    private String netIp;
    @DatabaseField
    private boolean news;
    @DatabaseField
    private long order;
    @DatabaseField(dataType=DataType.BYTE_ARRAY)
    private byte publicKey[];
    private String qrInfo;
    private int subDevice;    
    private int switchState;
    private EairInfo eairInfo;
    @DatabaseField
	public int terminalId;
    @DatabaseField
    private String userName;

    public ManageDevice()
    {

        city = "nocity";
        cityCode = "0000";
        deviceLock = 0;
        deviceMac = "00:00:00:00";
        deviceName = "noname";
        devicePassword = 0;
        deviceType = 0;
        latitude = 0;
        longitude = 0;
        netIp = "0.0.0.0";
        news = true;
        order = 0;
        qrInfo = "";
        terminalId=0;
        userName = "nouser";
 
    }

    public String getCity()
    {
        return city;
    }

    public String getCityCode()
    {
        return cityCode;
    }

    public int getDeviceLock()
    {
        return deviceLock;
    }

    public String getDeviceMac()
    {
        return deviceMac;
    }

    public String getDeviceName()
    {
        return deviceName;
    }

    public int getDevicePassword()
    {
        return devicePassword;
    }

    public short getDeviceType()
    {
        return deviceType;
    }

    public String getJson()
    {
        String s;
        try
        {
            JSONObject jsonobject = new JSONObject();
            jsonobject.put("devicePassword", devicePassword);
            jsonobject.put("deviceType", deviceType);
            jsonobject.put("deviceMac", deviceMac);
            jsonobject.put("deviceLock", deviceLock);
            jsonobject.put("publicKey", Arrays.toString(publicKey));
            jsonobject.put("terminalId", terminalId);
            jsonobject.put("subDevice", subDevice);
            jsonobject.put("latitude", latitude);
            jsonobject.put("longitude", longitude);
            jsonobject.put("city", city);
            jsonobject.put("cityCode", cityCode);
            jsonobject.put("netIp", netIp);
            s = jsonobject.toString();
        }
        catch (Exception exception)
        {
            return null;
        }
        return s;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public String getNetIp()
    {
        return netIp;
    }

    public long getOrder()
    {
        return order;
    }

    public byte[] getPublicKey()
    {
        return publicKey;
    }

    public String getQrInfo()
    {
    	return Integer.toString(terminalId & 0x7fffffff);
        //return qrInfo;
    }

    public int getSubDevice()
    {
        return subDevice;
    }

    public int getSwitchState()
    {
        return switchState;
    }

    public EairInfo getEairInfo()
    {
        return eairInfo;
    }

    public int getTerminalId()
    {
        return terminalId;
    }

    public String getUserName()
    {
        return userName;
    }

   /* public WeatherInfo getWeatherInfo()
    {
        return weatherInfo;
    }*/

    public boolean isNews()
    {
        return news;
    }

    public void setCity(String s)
    {
        city = s;
    }

    public void setCityCode(String s)
    {
        cityCode = s;
    }

    public void setDeviceLock(int i)
    {
        deviceLock = i;
    }

    public void setDeviceMac(String s)
    {
        deviceMac = s;
    }

    public void setDeviceName(String s)
    {
        deviceName = s;
    }

    public void setDevicePassword(int i)
    {
        devicePassword = i;
    }

    public void setDeviceType(short word0)
    {
        deviceType = word0;
    }

    public void setLatitude(double d)
    {
        latitude = d;
    }

    public void setLongitude(double d)
    {
        longitude = d;
    }

    public void setNetIp(String s)
    {
        netIp = s;
    }

    public void setNews(boolean flag)
    {
        news = flag;
    }

    public void setOrder(long l)
    {
        order = l;
    }

    public void setPublicKey(byte abyte0[])
    {
        publicKey = abyte0;
    }

    public void setQrInfo(String s)
    {
        qrInfo = s;
    }

    public void setSubDevice(int i)
    {
        subDevice = i;
    }

    public void setSwitchState(int i)
    {
        switchState = i;
    }

    public void setEairInfo(EairInfo info)
    {
    	eairInfo = info;
    }

    public void setTerminalId(int i)
    {
        terminalId = i;
    }

    public void setUserName(String s)
    {
        userName = s;
    }

    
    /*public void setWeatherInfo(WeatherInfo weatherinfo)
    {
        weatherInfo = weatherinfo;
    }*/
}
