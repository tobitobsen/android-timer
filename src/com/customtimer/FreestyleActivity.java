package com.customtimer;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FreestyleActivity extends Activity {

	
	TextView timeDisplay;
	MyCount counter;
	int state = 0;
	int length = 10000;
	long startTime = 0;
	long currentTime = 0;
	long timeElapsed = 0;
	long timeRemaining = 0;
	long prevTimeRemaining = 0;
	Button control;

	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.freestyle);

	  timeDisplay = (TextView) findViewById(R.id.timer);
	  control = (Button) findViewById(R.id.control);
	  counter = new MyCount(length, 1000);
	  
	  timeDisplay.setText("Start value: " + String.valueOf(formatTime(length)));
	}

	public void control(View view) {
	  switch (state) {
	  case 0:
	    startTime = System.currentTimeMillis();
	    counter.start();
	    control.setText(R.string.pause);
	    state = 1;
	    break;
	  case 1:
	    // pause
	    currentTime = System.currentTimeMillis();
	    timeElapsed = currentTime - startTime;
	    if (prevTimeRemaining == 0)
	    	timeRemaining = length - timeElapsed;
	    else
	    	timeRemaining = prevTimeRemaining - timeElapsed;
	    	counter.cancel();
	    	timeDisplay.setText("Time left: " + String.valueOf(formatTime(timeRemaining)));
	    	control.setText(R.string.resume);
	    	prevTimeRemaining = timeRemaining;

	      // resume
	    counter = new MyCount(timeRemaining, 1000);
	    state = 0;
	    break;
	  case 2:
	    prevTimeRemaining = 0;
	    counter = new MyCount(length, 1000);
	    control.setText(R.string.start);
//	    timeDisplay.setText(R.string.timer);
	    timeDisplay.setText("Start value: " + String.valueOf(formatTime(length)));
	    state = 0;
	  }
	}

	public class MyCount extends CountDownTimer {

	  public MyCount(long millisInFuture, long countDownInterval) {
	    super(millisInFuture, countDownInterval);
	  }

	  public void onFinish() {
	    timeDisplay.setText("done!");
	    state = 2;
	    control.setText(R.string.restart);
	  }

	  public void onTick(long millisUntilFinished) {
	    timeDisplay.setText("Time left: " + formatTime(millisUntilFinished));
	  }
    }
	  
	public String formatTime(long millis) {
		String output = "00:00:00";
		long seconds = millis / 1000;
		long minutes = seconds / 60;
		long hours = minutes / 60;

		seconds = seconds % 60;
		minutes = minutes % 60;
		hours = hours % 60;

		String secondsD = String.valueOf(seconds);
		String minutesD = String.valueOf(minutes);
		String hoursD = String.valueOf(hours); 

		if (seconds < 10)
			secondsD = "0" + seconds;
		if (minutes < 10)
		    minutesD = "0" + minutes;
		if (hours < 10)
		    hoursD = "0" + hours;

		output = hoursD + " : " + minutesD + " : " + secondsD;
		return output;
	}

	
}
