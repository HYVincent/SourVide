
package com.vincent.sourvide.data;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

public class ManageDeviceDao extends BaseDaoImpl
{

    public ManageDeviceDao(ConnectionSource connectionsource, Class class1)
        throws SQLException
    {
        super(connectionsource, class1);
    }

    public ManageDeviceDao(DatabaseHelper databasehelper)
        throws SQLException
    {
        super(databasehelper.getConnectionSource(), ManageDevice.class);
    }

    public void clearTable()
        throws SQLException
    {
        TransactionManager.callInTransaction(connectionSource, new Callable() {

            public Object call() throws Exception
            {
                Iterator iterator = queryForAll().iterator();
                do
                {
                    if (!iterator.hasNext())
                        return null;
                    ManageDevice managedevice = (ManageDevice)iterator.next();
                    deleteDevice(managedevice);
                } while (true);
            }

        }
);
    }

	public void deleteDevice(final ManageDevice manageDevice)
        throws SQLException
    {
        TransactionManager.callInTransaction(connectionSource, new Callable() {


            public  Object call()
                throws Exception
            {
            	deleteById(manageDevice.getDeviceMac());
                return null;
            }
        }
);
    }

    public List getDeviceList(int i)
        throws SQLException
    {
        QueryBuilder querybuilder = queryBuilder();
        querybuilder.where().eq("deviceType", Integer.valueOf(i));
        return query(querybuilder.prepare());
    }

    public List queryAllDevices()
        throws SQLException
    {
        QueryBuilder querybuilder = queryBuilder();
        querybuilder.orderBy("order", true);
        return query(querybuilder.prepare());
    }
}
