package com.example.sumeet.test;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    private int num = 0;
    public static String teamNumber;
    public static String matchNumber;
    public static String cords;
    public static boolean isTablet;
    private WindowManager w = getWindowManager();
    private Display d;
    private Point size = new Point();
    private EditText editText;
    private TextView textView;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(isTablet(this)){
            isTablet = true;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }else{
            isTablet = false;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }


    }

//    public boolean onTouchEvent(MotionEvent event){
//        imageView = (ImageView)findViewById(R.id.imageView);
//        textView = (TextView)findViewById(R.id.textView2);
//        if(event.getAction() == MotionEvent.ACTION_DOWN){
//            if(event.getX()<imageView.getRight() && event.getX()>imageView.getLeft() && event.getY()<imageView.getTop() && event.getY()>imageView.getBottom()){
//                //System.out.println("x" + event.getX());
//                //System.out.println("y" + event.getY());
//                textView.setText("x:" + (event.getX()-imageView.getLeft())+ " " + "y:" + (event.getY()-imageView.getTop()));
//               textView.setTextSize(11);
//
//            }
//            //x = event.getX();
//            //y = event.getY();
//            //returnCords(textView);
//        }
//
//        return super.onTouchEvent(event);
//    }


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

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public void startTeleop(View view){
        editText = (EditText)findViewById(R.id.editText);
        teamNumber = editText.getText().toString();
        editText = (EditText)findViewById(R.id.editText2);
        matchNumber = editText.getText().toString();
        Intent intent = new Intent(this, Autonomous.class);
        startActivity(intent);
    }

    public void getCords(View view){
        imageView = (ImageView)findViewById(R.id.imageView);
        textView = (TextView)findViewById(R.id.textView2);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                textView.setText("Touch coordinates : " + "x" +
                        String.valueOf(event.getX()) + "y" + String.valueOf(event.getY()));
                cords = "" + event.getX() + "," + event.getY();
                return true;
            }
        });
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
