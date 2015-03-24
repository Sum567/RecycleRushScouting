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
//    public static String autonTotesKnocked = "" + autonKnockedOver.totesAdded;
//    public static String autonBinsKnocked = "" + autonKnockedOver.binsAdded;
    public int feeder = 0;
    public int landfill = 0;


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

        File file = new File(dir, "Scouting_info1" + ".csv");
        File file2 = new File(dir, "Stack_info1" + ".csv");
        System.out.println(file.getPath());
        try {

            BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
            BufferedWriter out2 = new BufferedWriter(new FileWriter(file2, true));

            out.append(MainActivity.teamNumber + "\t");
            out.append(MainActivity.matchNumber + "\t");
            out.append(MainActivity.cordx + "\t");
            out.append(MainActivity.cordy + "\t");
            if(Autonomous.autonMove){
                out.append("1" + "\t");
            }else{
                out.append("0" + "\t");
            }
            out.append(Autonomous.binsSetAuton + "\t");
            out.append(Autonomous.totesSetAuton + "\t");
            out.append(Autonomous.totesStackedAuton + "\t");
//            out.append(autonTotesKnocked + "\t");
//            out.append(autonBinsKnocked + "\t");
            out.append(Comments.autonComments + "\t");
            for (int i = 0; i < showStacks.stacks.size(); i++) {
                out2.append(MainActivity.teamNumber + "\t");
                out2.append(MainActivity.matchNumber + "\t");
                if(showStacks.stacks.get(i).getHasBin()){
                    out2.append("1" + "\t");
                }else{
                    out2.append("0" + "\t");
                }

                if(showStacks.stacks.get(i).getHasNoodle()){
                    out2.append("1" + "\t");
                }else{
                    out2.append("0" + "\t");
                }

                out2.append(showStacks.stacks.get(i).getTop() + "\t");
                out2.append(showStacks.stacks.get(i).getBottom() + "\t");
                out2.append('G' + "\t");
                out2.newLine();
                if (showStacks.stacks.get(i).getfromFeeder()) {
                    feeder++;
                } else if (showStacks.stacks.get(i).getFromLandFill()) {
                    landfill++;
                }
            }
            out.append(feeder + "\t");
            out.append(landfill + "\t");
            out.append(teleopKnockedOver.totes + "\t");
            out.append(teleopKnockedOver.bins + "\t");
            out.append(Comments.teleopComments + "\t");
            for (int i = 0; i < showStacks.yellowStacks.size(); i++) {
                out2.append(MainActivity.teamNumber + "\t");
                out2.append(MainActivity.matchNumber + "\t");
                out2.append("0" + "\t");
                out2.append("0" + "\t");
                out2.append(showStacks.yellowStacks.get(i).getTop() + "\t");
                out2.append(showStacks.yellowStacks.get(i).getBottom() + "\t");
                out2.append('Y' + "\t");
                out2.newLine();
            }
            for (int i = 0; i < showStacks.binStacks.size(); i++) {
                out2.append(MainActivity.teamNumber + "\t");
                out2.append(MainActivity.matchNumber + "\t");
                out2.append("0" + "\t");
                out2.append("0" + "\t");
                out2.append(showStacks.binStacks.get(i).getTop() + "\t");
                out2.append(showStacks.binStacks.get(i).getBottom() + "\t");
                out2.append('B' + "\t");
                out2.newLine();
            }
            out.newLine();
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
        showStacks.binStacks = new ArrayList<>();
        Autonomous.binsSetAuton  = 0;
        Autonomous.totesSetAuton = 0;
        Autonomous.totesStackedAuton = 0;
        teleopKnockedOver.bins = 0;
        teleopKnockedOver.totes = 0;
        
        Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
