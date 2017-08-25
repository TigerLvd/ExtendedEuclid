package ru.lvd.dan.extendedeuclid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvOutNumb;
    TextView tvOutMod;
    TextView tvOutOut;
    TextView tvOutStep;
    Button btnRun;
    Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOutNumb = (TextView) findViewById(R.id.editText);
        tvOutMod = (TextView) findViewById(R.id.editText2);
        tvOutStep = (TextView) findViewById(R.id.editText3);
        tvOutOut = (TextView) findViewById(R.id.textView2);
        btnRun = (Button) findViewById(R.id.button);
        btnClear = (Button) findViewById(R.id.button2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //public void onClickExAct(View v){
    //    Intent intent = new Intent(this, Ex_Activity.class);
    //    startActivity(intent);
    //}

    //@Override
    public void onClickRun(View v) {
        if(tvOutNumb.getText().length()>0 && tvOutMod.getText().length()>0)
        {
            int num = Integer.parseInt(tvOutNumb.getText().toString());
            int mod = Integer.parseInt(tvOutMod.getText().toString());
            int x, y, d;
            int q, r, x1, x2, y1, y2;
            if (mod == 0)
            {
                d = num; x = 1; y = 0;
            }
            else
            {
                x2 = 1; x1 = 0; y2 = 0; y1 = 1;
                while (mod > 0)
                {
                    q = num / mod;
                    r = num - q * mod;
                    x = x2 - q * x1;
                    y = y2 - q * y1;
                    num = mod; mod = r;
                    x2 = x1; x1 = x;
                    y2 = y1; y1 = y;
                }

                d = num;
                x = x2;
                y = y2;
            }
            String str = Integer.toString(x);
            if(x<0)
            {
                str += " (" + Integer.toString(x + Integer.parseInt(tvOutMod.getText().toString())) + ")";
            }
            tvOutOut.setText(str);
        }
    }

    public void onClickClear(View v){
        tvOutOut.setText("");
        tvOutNumb.setText("");
        tvOutMod.setText("");
        tvOutStep.setText("");
    }

    public void onClickStep(View v){
        if(tvOutNumb.getText().length()>0 && tvOutMod.getText().length()>0 && tvOutStep.getText().length()>0) {
            int num = Integer.parseInt(tvOutNumb.getText().toString());
            int mod = Integer.parseInt(tvOutMod.getText().toString());
            int step = Integer.parseInt(tvOutStep.getText().toString());
            int res = 1, c = step;
            while (c != 0) {
                if (c % 2 == 1) {
                    res = (res * num) % mod;
                }
                num = (num * num) % mod;
                c = c / 2;
            }
            res = res % mod;
            tvOutOut.setText(Integer.toString(res));
        }
    }

    public void onClickSol(View v){
        if(tvOutNumb.getText().length()>0 && tvOutMod.getText().length()>0)
        {
            int num = Integer.parseInt(tvOutNumb.getText().toString());
            int mod = Integer.parseInt(tvOutMod.getText().toString());
            String str = "";

            int r, k1, k2, k3, k4, a, b, res=0;
            a = mod; b = ((num % mod) + mod) % mod ;
            r = mod % num;
            k1 = 1;
            k2 = mod / num;
            str = Integer.toString(mod) + " - " + Integer.toString(k2) + " * " + Integer.toString(num) + " = " + Integer.toString(r) + "\n";
            if (r != 0 && r != 1)
            {
                mod = num;
                num = r;
                r = mod % num;
                k3 = mod / num;
                k4 = 1 + k3 * k2;
                str += Integer.toString(mod) + " - " + Integer.toString(k3) + " * " + Integer.toString(num) + " = " + Integer.toString(r) + " = " + Integer.toString(b) + " * " + Integer.toString(k4) + " - " + Integer.toString(a) + " * " + Integer.toString(k3) + "\n";
                if (r != 0 && r != 1)
                {
                    do {
                        mod = num;
                        num = r;
                        r = mod % num;
                        k1 = k1 + (mod / num) * k3;
                        k2 = k2 + (mod / num) * k4;
                        str += Integer.toString(mod) + " - " + Integer.toString(mod / num) + " * " + Integer.toString(num) + " = " + Integer.toString(r) + " = " + a + " * " + Integer.toString(k1) + " - " + Integer.toString(b) + " * " + Integer.toString(k2) + "\n";
                        if (r != 0 && r != 1)
                        {
                            mod = num;
                            num = r;
                            r = mod % num;
                            k3 = k3 + (mod / num) * k1;
                            k4 = k4 + (mod / num) * k2;
                            str += Integer.toString(mod) + " - " + Integer.toString(mod / num) + " * " + Integer.toString(num) + " = " + Integer.toString(r) + " = " + Integer.toString(b) + " * " + Integer.toString(k4) + " - " + Integer.toString(a) + " * " + Integer.toString(k3) + "\n";
                            if(r ==1)
                            {
                                res = k4;
                            }
                        }
                        else
                        {
                            res = -k2;
                        }
                    } while (r != 1 && r != 0);
                }
                else
                {
                    res = k4;
                }
            }
            else
            {
                res = -k2;
            }
            if(r !=0)
            {
                tvOutOut.setText(str + "____________________\nОтвет: " + Integer.toString((res + a) % a));
            }
            else
            {
                tvOutOut.setText("Не взаимопростые числа");
            }
        }
    }
}
