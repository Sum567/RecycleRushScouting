package com.example.sumeet.test;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class showStacks extends ActionBarActivity {
    private TextView textView;
    public static ArrayList<Stack> stacks = new ArrayList<>();
    public static ArrayList<Stack> yellowStacks = new ArrayList<>();
    public static ArrayList<Stack> binStacks = new ArrayList<>();
    private LinearLayout layout;
    private String tempString = "";
    public static int temp1;
    public static int temp2;
    public static int temp3;
    public static int top;
    public static int bottom;
    public static boolean toggle;
    public int backToggle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_stacks);

        if (MainActivity.isTablet) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        if (stacks.size() > 0) {
            printStackNumbers();
        }

        if (yellowStacks.size() > 0) {
            printYellowStackNumbers();
        }

        if (binStacks.size() > 0) {
            printBinStackNumbers();
        }
        textView = (TextView) (findViewById((R.id.textView3)));
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Layout layout = ((TextView) v).getLayout();
                int x = (int) event.getX();
                int y = (int) event.getY();
                if (layout != null) {
                    int line = layout.getLineForVertical(y);
                    editStack(line);
                    //System.out.println(line);

                }

                return true;
            }
        });

        textView = (TextView) (findViewById((R.id.textView13)));
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Layout layout = ((TextView) v).getLayout();
                int x = (int) event.getX();
                int y = (int) event.getY();
                if (layout != null) {
                    int line = layout.getLineForVertical(y);
                    editYellowStack(line);
                    //System.out.println(line);

                }

                return true;
            }
        });

        textView = (TextView) (findViewById((R.id.textView30)));
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Layout layout = ((TextView) v).getLayout();
                int x = (int) event.getX();
                int y = (int) event.getY();
                if (layout != null) {
                    int line = layout.getLineForVertical(y);
                    editBinStack(line);
                    //System.out.println(line);

                }

                return true;
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Autonomous.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_stacks, menu);
        return true;
    }

    public void editStack(int stackNum) {
        int numStack = stackNum;
        //System.out.println(temp);
        for (int i = 0; i < stacks.size(); i++) {
            if (numStack == i) {
                temp1 = i;
                Intent intent = new Intent(this, makeStack.class);
                intent.putExtra("toggle", true);
                startActivity(intent);
            }
        }
    }

    public void editYellowStack(int stackNum) {
        int numStack2 = stackNum;
        //System.out.println(numStack2);
        for (int i = 0; i < yellowStacks.size(); i++) {
            if (numStack2 == i) {
                temp2 = i;
                Intent intent = new Intent(this, makeYellowStack.class);
                intent.putExtra("toggle", true);
                startActivity(intent);
            }
        }
    }

    public void editBinStack(int stackNum) {
        int numStack3 = stackNum;

        for (int i = 0; i < binStacks.size(); i++) {
            if (numStack3 == i) {
                temp3 = i;
                Intent intent = new Intent(this, makeBinStack.class);
                intent.putExtra("toggle", true);
            }
        }
    }

    public void createStack(View view) {
        Intent intent = new Intent(this, makeStack.class);
        intent.putExtra("toggle", false);
        startActivity(intent);
    }

    public void createYellowStack(View view) {
        Intent intent = new Intent(this, makeYellowStack.class);
        intent.putExtra("toggle", false);
        startActivity(intent);
    }

    public void createBinStack(View view) {
        Intent intent = new Intent(this, makeBinStack.class);
        intent.putExtra("toggle", false);
        startActivity(intent);
    }

    public void printStackNumbers() {
        textView = (TextView) (findViewById(R.id.textView3));
        if (textView.getText() == null) {
            for (int i = 0; i < stacks.size(); i++) {
                textView.append(tempString + " Stack" + "#" + (i + 1));

            }
        }
        if (textView.getText() != null) {
            tempString = textView.getText().toString();
            for (int i = 0; i < stacks.size(); i++) {
                textView.append(tempString + "Stack" + "#" + (i + 1) + "\n");
            }
        }


    }

    public void printYellowStackNumbers() {
        textView = (TextView) (findViewById(R.id.textView13));
        if (textView.getText() == null) {
            for (int i = 0; i < yellowStacks.size(); i++) {
                textView.append(tempString + "Stack" + "#" + (i + 1));
                textView.setTextColor(Color.YELLOW);
            }
        }
        if (textView.getText() != null) {
            tempString = textView.getText().toString();
            for (int i = 0; i < yellowStacks.size(); i++) {
                textView.append(tempString + "Stack" + "#" + (i + 1) + "\n");
                textView.setTextColor(Color.YELLOW);
            }
        }
    }

    public void printBinStackNumbers() {
        textView = (TextView) (findViewById(R.id.textView30));
        if (textView.getText() == null) {
            for (int i = 0; i < binStacks.size(); i++) {
                textView.append(tempString + "Stack" + "#" + (i + 1));
            }
        }
        if (textView.getText() != null) {
            tempString = textView.getText().toString();
            for (int i = 0; i < binStacks.size(); i++) {
                textView.append(tempString + "Stack" + "#" + (i + 1) + "\n");
            }
        }
    }

    public void startKnockedOver(View view) {
        Intent intent = new Intent(this, teleopKnockedOver.class);
        startActivity(intent);
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
}
