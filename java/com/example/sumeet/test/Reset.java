package com.example.sumeet.test;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Reset extends ActionBarActivity {
    public static String teamNumber = MainActivity.teamNumber;
    public static String matchNumber = MainActivity.matchNumber;
    public static String cords = MainActivity.cords;
    public static String totesSetAuton = "" + Autonomous.totesSetAuton;
    public static String totesMovedAuton = "" + Autonomous.totesMovedAuton;
    public static String autonMove = "" + Autonomous.autonMove;
    public static String autonTotesKnocked = "" + autonKnockedOver.totesAdded;
    public static String autonBinsKnocked = "" + autonKnockedOver.binsAdded;
    public static String teleopTotesKnocked = "" + teleopKnockedOver.totes;
    public static String teleopBinsKnocked = "" + teleopKnockedOver.bins;
    public static String autonComments = Comments.autonComments;
    public static String teleopComments = Comments.teleopComments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        if(MainActivity.isTablet){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,Comments.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reset, menu);
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

    private void generateCsvFile() {
        //System.out.println(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
        File root = android.os.Environment.getExternalStorageDirectory();
        File dir = new File(root.getAbsolutePath() + "/download");
        dir.mkdirs();
        File file = new File(dir,teamNumber + "," + matchNumber + ".csv");
        System.out.println(file.getPath());
        try {
            //File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), teamNumber + "," + matchNumber + ".csv");
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("Created new file");
            }

            BufferedWriter out = new BufferedWriter(new FileWriter(file));

            out.write("Team Number: " + teamNumber + "," + "Match Number: " + matchNumber);
            out.newLine();
            out.write("Cords: " + cords);
            out.newLine();
            out.write("Auton Move: " + autonMove);
            out.newLine();
            out.write("Auton totes moved: " + totesMovedAuton);
            out.newLine();
            out.write("Auton totes set: " + totesSetAuton);
            out.newLine();
            out.newLine();
            out.write("Stacks:");
            out.newLine();
            for (int i = 0; i < showStacks.stacks.size(); i++) {
                out.write("Bin: " + showStacks.stacks.get(i).getHasBin() + "," + "Noodle + bin: " + showStacks.stacks.get(i).getHasNoodle());
                out.newLine();
                out.write("Top: " + showStacks.stacks.get(i).getTop() + "," + "Bottom: " + showStacks.stacks.get(i).getBottom());
                out.newLine();
            }
            out.newLine();
            out.newLine();
            out.write("Co-op Stacks:");
            out.newLine();
            for(int i = 0; i<showStacks.yellowStacks.size(); i++){
                out.write("Top: " + showStacks.yellowStacks.get(i).getTop() + "," + "Bottom: " + showStacks.yellowStacks.get(i).getBottom());
                out.newLine();
            }
            out.newLine();
            out.newLine();
            out.write("Auton Comments: " + autonComments);
            out.newLine();
            out.write("Auton totes knocked over: " + autonTotesKnocked + "," + "Auton bins knocked over: " + autonBinsKnocked);
            out.newLine();
            out.write("Teleop Comments: " + teleopComments);
            out.newLine();
            out.write("Teleop totes knocked over: " + teleopTotesKnocked + "," + "Teleop bins knocked over: " + teleopBinsKnocked);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public void startOver(View view){
        generateCsvFile();
        showStacks.stacks = new ArrayList<>();
        showStacks.yellowStacks = new ArrayList<>();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
