package com.dan.tipcalc;

import java.text.DecimalFormat;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	EditText input;
	TextView output;
	float[] tipAmounts = {0.1f,0.15f,0.2f};
	Button[] tipButtons = new Button[3];
	HashMap<Integer,Integer> buttons = new HashMap<Integer,Integer>();
	DecimalFormat moneyFormat = new DecimalFormat("$0.00");
	float lastSelectedTip=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		input = (EditText)findViewById(R.id.etInput);
		input.addTextChangedListener(etWatcher);
		output = (TextView)findViewById(R.id.tvTipAmount);
		tipButtons[0] = (Button)findViewById(R.id.btTip1);
		tipButtons[1] = (Button)findViewById(R.id.btTip2);
		tipButtons[2] = (Button)findViewById(R.id.btTip3);
		buttons.put(R.id.btTip1, 0);
		buttons.put(R.id.btTip2, 1);
		buttons.put(R.id.btTip3, 2);
		for(int i=0;i<tipButtons.length;i++){
			tipButtons[i].setText(((int)(tipAmounts[i]*100))+"%");
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void calcTip(View v){
		int id = v.getId();
		String ip = input.getText().toString();
		if(ip.trim().equals("") || ip == null){
			output.setText("");
			return;
		}
		float inputAmount = Float.valueOf(ip);
		Integer tipIndex = buttons.get(id);
		if(inputAmount > 0 && tipIndex != null && tipIndex < 3){
			float tip = tipAmounts[tipIndex];
			lastSelectedTip = tip;
			float calculatedTip = tip*inputAmount;
			output.setText(moneyFormat.format(calculatedTip));
		}else{
			output.setText("invalid input");
		}
	}
	
	TextWatcher etWatcher = new TextWatcher(){

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			String edit = s.toString();
			if(edit.equals("") || edit == null){
				output.setText("");
				return;
			}
			float inputAmount = Float.valueOf(edit);
			if(lastSelectedTip > 0 && inputAmount > 0){
				float calculatedTip = lastSelectedTip*inputAmount;
				output.setText(moneyFormat.format(calculatedTip));
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			
		}
		
	};

}
