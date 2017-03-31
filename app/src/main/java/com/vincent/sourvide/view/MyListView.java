
package com.vincent.sourvide.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class MyListView extends ListView
{

    public MyListView(Context context)
    {
        super(context);
    }

    public MyListView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public MyListView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, android.view.View.MeasureSpec.makeMeasureSpec(0x1fffffff, 0x80000000));
    }
}
