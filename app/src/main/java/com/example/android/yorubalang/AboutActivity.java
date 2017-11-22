package com.example.android.yorubalang;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity  {

    //This is use to call a method from another java class
    //MainActivity mm =new MainActivity();

    private MenuItem itemToHide;
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        startActivity(new Intent(AboutActivity.this, MainActivity.class));
        finish();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ActionBar ab = getSupportActionBar();
        ab.setTitle(Html.fromHtml("<font color=#ffffff>" + getString(R.string.app_name) + "</font>"));
        //ab.setIcon(getResources().getDrawable(R.drawable.ic_action_back)); white back button
        if(ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);

        }

        View mainContent = findViewById(R.id.main_content);
        if (mainContent != null) {
            mainContent.setAlpha(0);
            mainContent.animate().alpha(1).setDuration(2000);
        }

        ((TextView)findViewById(R.id.authorLinkedin)).setMovementMethod(LinkMovementMethod.getInstance());
        ((TextView)findViewById(R.id.githubURL)).setMovementMethod(LinkMovementMethod.getInstance());
        ((TextView)findViewById(R.id.apiURL)).setMovementMethod(LinkMovementMethod.getInstance());
        ((TextView)findViewById(R.id.textFieldVersionName)).setText(BuildConfig.VERSION_NAME);

        overridePendingTransition(0, 0);

    }
     @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

         itemToHide = menu.findItem(R.id.action_about_id);
         itemToHide.setVisible(false);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected  (MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            startActivity(new Intent(AboutActivity.this, MainActivity.class));
            finish();
            return true;
        }
        if (id == R.id.action_share_id) {
            //mm.yorubaShareIntent(get);
            Intent sendIntent = new Intent();
            Uri url = Uri.parse("https://drive.google.com/file/d/16QRjLOhcZA-dvydhH4Q1Fnpf28eyWNF-/view?usp=drivesdk");
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hi!, I'm using "+getString(R.string.app_name)+" to improve and understand Yoruba Language Better " +
                    ". Get the application here "+url);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);


        }

        return super.onOptionsItemSelected(item);
    }
}
