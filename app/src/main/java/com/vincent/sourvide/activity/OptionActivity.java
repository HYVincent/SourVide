

package com.vincent.sourvide.activity;


import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.vincent.sourvide.R;


public class OptionActivity extends TitleActivity
{
	

    private ListView mOptionListView;
    private static final int ID_ADD_NEW_DEVICE = 0;
   // private static final int ID_CHECK_UPDATE = 1;
    private static final int ID_MANUAL = 1;
    private static final int ID_FAQ = 2;
    private static final int ID_ABOUT = 3;
    
    public OptionActivity()
    {
    }


    private void findView()
    {
    	mOptionListView = (ListView)findViewById(R.id.option_list);
    	initOption();
    }

	
    private void initOption()
    {

	    	ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();/*�������д������*/

			HashMap<String, Object> map = new HashMap<String, Object>();  
	        map.put("option_icon", R.drawable.option_add);  
	        map.put("option_content",  getString(R.string.option_text_add));  
	        listItem.add(map); 
	        
	        /*map = new HashMap<String, Object>();  
	        map.put("option_icon", R.drawable.option_update);  
	        map.put("option_content",  getString(R.string.option_text_update));  
	        listItem.add(map); */
	        
	        map = new HashMap<String, Object>();  
	        map.put("option_icon", R.drawable.option_manual);  
	        map.put("option_content",  getString(R.string.option_text_manual)); 
	        listItem.add(map); 
	        
	        map = new HashMap<String, Object>();  
	        map.put("option_icon", R.drawable.option_faq);  
	        map.put("option_content",  getString(R.string.option_text_faq));  
	        listItem.add(map); 
	        
	        map = new HashMap<String, Object>();  
	        map.put("option_icon", R.drawable.option_about);  
	        map.put("option_content",  getString(R.string.option_text_about));  
	        listItem.add(map);  
	        
	        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this, listItem, R.layout.option_list_item_layout, 
	             new String[] {"option_icon", "option_content"}, 
	             new int[] {R.id.option_icon, R.id.option_content});
	        
	        mOptionListView.setAdapter(mSimpleAdapter); 
	        
	        mOptionListView.setOnItemClickListener
	        (
	        		new android.widget.AdapterView.OnItemClickListener() 
		            {
				            public void onItemClick(AdapterView adapterview, View view, int i, long l)
				            {
				            	
				            	if(i == ID_ADD_NEW_DEVICE)
				            	{
				            		Intent intent = new Intent();
			                        intent.setClass(OptionActivity.this, ConfigDeviceActivity.class);
			                        startActivity(intent);
				            	}
				            	/*else if(i == ID_CHECK_UPDATE)
				            	{
				            		Intent intent = new Intent();
			                        intent.setClass(OptionActivity.this, ConfigDeviceActivity.class);
			                        startActivity(intent);
				            	}*/
				            	else if(i == ID_MANUAL)
				            	{
				            		Intent intent = new Intent();
			                        intent.setClass(OptionActivity.this, UserGuideActivity.class);
			                        startActivity(intent);
				            	}
				            	else if(i == ID_FAQ)
				            	{
				            		Intent intent = new Intent();
			                        intent.setClass(OptionActivity.this, FAQActivity.class);
			                        startActivity(intent);
				            	}
				            	else if(i == ID_ABOUT)
				            	{
				            		Intent intent = new Intent();
			                        intent.setClass(OptionActivity.this, AboutActivity.class);
			                        startActivity(intent);
				            	}
				            	    
				            	    
				            }
	                }
	        );
    	}
    
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.option_layout);
        setTitle(R.string.option_title);
        setBackVisible();
        findView();     
        

    }

	protected void onPause()
    {
           super.onPause();

    }

    protected void onResume()
    {
        super.onResume();
        
        
    }

    
    private OnClickListener mOptionClick = new OnClickListener() 
    {
		
		@Override
		public void onClick(View arg0) 
		{
			
		}
	};

}
