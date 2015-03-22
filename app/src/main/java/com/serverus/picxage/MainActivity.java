package com.serverus.picxage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;

    private static final int SELECTED_PICTURE =1;

    ImageView imageView;

    List<Drawable> listOfDrawable = new ArrayList<Drawable>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // call the custom action bar or app bar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        imageView = (ImageView) findViewById(R.id.imageView);



    }

    public void btnClick(View view){

        Intent intent = new Intent();
        // get only the image
        intent.setType("image/*");
        // action to call the gallery
        intent.setAction(Intent.ACTION_GET_CONTENT);
        // start a new activity which is the gallery
        startActivityForResult(Intent.createChooser(intent, "select image"), 1);

    }

    public void resetBtnClick(View view){

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to reset?")
                .setTitle("Reset Everything?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Resources res = getResources();
                                imageView.setImageDrawable(res.getDrawable(R.mipmap.ic_launcher));
                            }
                        }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SELECTED_PICTURE){
            if(resultCode == RESULT_OK){
                // get the uri of the image
                Uri uri = data.getData();

                //uri log checker
                L.m(uri.toString());

                // set the new image
                imageView.setImageURI(uri);

                // conver the image view to drawable then put it inside
                // listOfDrawable which is a arrayList of drawable
                listOfDrawable.add(imageView.getDrawable());

                // log
                L.m(listOfDrawable.get(0).toString());
            }

        }
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
}
