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
import java.io.Writer;
import java.util.ArrayList;


public class Reset extends ActionBarActivity {
    public static String teamNumber = MainActivity.teamNumber;
    public static String matchNumber = MainActivity.matchNumber;
    public static String cords = MainActivity.cords;
    public static String totesSetAuton = "" + Autonomous.totesSetAuton;
    public static String binsSetAuton = "" + Autonomous.binsSetAuton;
    public static String autonMove = "" + Autonomous.autonMove;
//    public static String autonTotesKnocked = "" + autonKnockedOver.totesAdded;
//    public static String autonBinsKnocked = "" + autonKnockedOver.binsAdded;
    public static String teleopTotesKnocked = "" + teleopKnockedOver.totes;
    public static String teleopBinsKnocked = "" + teleopKnockedOver.bins;
    public static String autonComments = Comments.autonComments;
    public static String teleopComments = Comments.teleopComments;
    public int feeder = 0;
    public int landfill = 0;
    private Writer append;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        if (MainActivity.isTablet) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Comments.class);
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
        File file = new File(dir, "Scouting info" + ".csv");
        File file2 = new File(dir, "Stack info" + ".csv");
        System.out.println(file.getPath());
        try {
            //File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), teamNumber + "," + matchNumber + ".csv");
//            if (!file.exists()) {
//                file.createNewFile();
//                System.out.println("Created new file");
//            }
//            if(!file2.exists()){
//                file2.createNewFile();
//            }

            BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
            BufferedWriter out2 = new BufferedWriter(new FileWriter(file2, true));


            out.append("\n");
            out.append(teamNumber + "\t");
            out.append(matchNumber + "\t");
            out.append(cords + "\t");
            out.append(autonMove + "\t");
            out.append(binsSetAuton + "\t");
            out.append(totesSetAuton + "\t");
//            out.append(autonTotesKnocked + "\t");
//            out.append(autonBinsKnocked + "\t");
            out.append(autonComments + "\t");
            out2.append("\n");
            for (int i = 0; i < showStacks.stacks.size(); i++) {
                out2.append(teamNumber + "\t");
                out2.append(matchNumber + "\t");
                out2.append(showStacks.stacks.get(i).getHasBin() + "\t");
                out2.append(showStacks.stacks.get(i).getHasNoodle() + "\t");
                out2.append(showStacks.stacks.get(i).getTop() + "\t");
                out2.append(showStacks.stacks.get(i).getBottom() + "\t");
                out2.append('G' + "\t");
                out2.append("\n");
                if (showStacks.stacks.get(i).getfromFeeder()) {
                    feeder++;
                } else if (showStacks.stacks.get(i).getFromLandFill()) {
                    landfill++;
                }
            }
            out.append('F' + "" + feeder + "\t");
            out.append('L' + "" + landfill + "\t");
            out.append(teleopTotesKnocked + "\t");
            out.append(teleopBinsKnocked + "\t");
            out.append(teleopComments + "\t");
            for (int i = 0; i < showStacks.yellowStacks.size(); i++) {
                out2.append(teamNumber + "\t");
                out2.append(matchNumber + "\t");
                out2.append(showStacks.yellowStacks.get(i).getTop() + "\t");
                out2.append(showStacks.yellowStacks.get(i).getBottom() + "\t");
                out2.append('Y' + "\t");
                out2.append("\n");
            }
            out.close();
            out2.close();
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

    public void startOver(View view) {
        generateCsvFile();
        showStacks.stacks = new ArrayList<>();
        showStacks.yellowStacks = new ArrayList<>();
        Autonomous.binsSetAuton  = 0;
        Autonomous.totesSetAuton = 0;
        autonKnockedOver.binsAdded = 0;
        autonKnockedOver.totesAdded = 0;
        teleopKnockedOver.bins = 0;
        teleopKnockedOver.totes = 0;
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
