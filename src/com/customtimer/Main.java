package com.customtimer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends Activity {
	
	private Button freestyleButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        freestyleButton = (Button) findViewById(R.id.MainButtonFreestyle);
        freestyleButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(v.getContext(), FreestyleActivity.class);
				startActivity(i);
				
			}
		});
        
        
    }
}