package com.example.sumeet.test;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    public static String teamNumber;
    public static String matchNumber;
    public static String cords;
    public static String cordx;
    public static String cordy;
    private boolean fieldToggle = false;
    public static boolean isTablet;
    private EditText editText;
    private TextView textView;
    private ImageView imageView;
    private boolean imageSwitched = false;
    private Switch switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isTablet(this)) {
            isTablet = true;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            isTablet = false;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }



    }

    @Override
    public void onBackPressed() {
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

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public void startTeleop(View view) {
        editText = (EditText) findViewById(R.id.editText);
        teamNumber = editText.getText().toString();
        editText = (EditText) findViewById(R.id.editText2);
        matchNumber = editText.getText().toString();
        Intent intent = new Intent(this, Autonomous.class);
        startActivity(intent);
    }

    public void getCords(View view) {
        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView2);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //final Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                int x = 0;
                if(imageSwitched==false) {
                    x = (int) event.getX();
                }else{
                    x = 429-((int)event.getX());
                }
                int y = 432 - ((int) event.getY());

                textView.setText("Touch coordinates : " + "x" +
                        String.valueOf(x) + "y" + String.valueOf(y));
                cords = x + "  " + y;
                cordx = x + "";
                cordy = y + "";
                //textView.setText((imageView.getRight()-imageView.getLeft()) + "," + (imageView.getBottom()-imageView.getTop()));

                return true;
            }
        });

    }

    public void changeImage(View view){
        imageSwitched = true;
        fieldToggle = !fieldToggle;
        int resID = getResources().getIdentifier("field","drawable",getPackageName());
        int resID2 = getResources().getIdentifier("fieldrev","drawable",getPackageName());
        int resID3 = getResources().getIdentifier("grey","drawable",getPackageName());
        imageView = (ImageView)findViewById(R.id.imageView);

        if(fieldToggle==false){
            imageView.setImageDrawable(null);
            imageView.setImageResource(resID);
        }else{
            imageView.setImageDrawable(null);
            imageView.setImageResource(resID2);
        }

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
