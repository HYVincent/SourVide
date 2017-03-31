

package com.vincent.sourvide.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;


public class DatabaseHelper extends OrmLiteSqliteOpenHelper
{

    public DatabaseHelper(Context context)
    {
        super(context, "tcleair.db", null, 12);
    }

    public void onCreate(SQLiteDatabase sqlitedatabase, ConnectionSource connectionsource)
    {
        try
        {
            TableUtils.createTableIfNotExists(connectionsource, ManageDevice.class);
            return;
        }
        catch (SQLException sqlexception)
        {
            sqlexception.printStackTrace();
        }
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, ConnectionSource connectionsource, int i, int j)
    {
        try
        {
            TableUtils.dropTable(connectionSource, ManageDevice.class, true);
        }
        catch (SQLException sqlexception)
        {
            sqlexception.printStackTrace();
        }
        onCreate(sqlitedatabase, connectionSource);
    }
}
