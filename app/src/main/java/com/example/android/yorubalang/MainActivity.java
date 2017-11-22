package com.example.android.yorubalang;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //public static Object yorubaShareIntent;
    private String categoryToast;

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        System.exit(0);//exit application completely and kill all running tasks on backPressed

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*Method 1: steps to make a view reponse to click and also setup intents for it
        *@clickListener is the new object created
         * * STEP 1:  for linking an activity Through calling the clickListner either by using (INLINE OR OUTLINE clickListner)
          * STEP 2: OUTLINE  ClickListener is when you declare it inside another java file
         *          you declare this for each view when its clicked should open a page or do a particular function
         * * *      e.g NumbersClickListener.java file which content is below
         * *         public class NumbersClickListener implements View.OnClickListener {
                        @Override
                        public void onClick(View v) {
                           Toast.makeText(v.getContext(), "Open the List of Numbers", Toast.LENGTH_SHORT).show();
                           }
                        }
           STEP 3: in the main file where the view you want to make it clickable
                    is located you will assign a value to the clicklistener
                 *NumbersClickListener clickListner = new NumbersClickListener();
                 * TextView numbers = (TextView) findViewById(R.id.numbers_text_view);
                 * numbers.setOnClickListener(clickListner);
                 *                      OR
                 *numbers.setOnClickListener(new NumbersClickListener());
        **/


        /*Method 2: steps to make a view reponse to click and also setup intents for it

        *STEP 1: Find view that shows the view you want to make it clickable
                 TextView numbers = (TextView) findViewById(R.id.numbers_text_view);
         STEP 2: Set a Click Listener on that view
                  numbersTextView .setOnClickListener(new View.OnClickListener(){
                 @Override
                 public void onClick(View v) {
                    STEP 3:  set a toast message or any function which you want it to have
                    Toast.makeText(v.getContext(), "Open the List of Numbers", Toast.LENGTH_SHORT).show();
                    STEP 4:  set an intent if you want it to open to another activity
                    Intent numberIntent = new Intent(MainActivity.this, NumbersActivity.class);
                    startActivity(numberIntent);
            }
        });
         */


        /**
         * 2ndmethod for making a view clickable with intent to open
         * another page recommended and very short
         * for  NumbersActivity
         */
        TextView numbersTextView  = (TextView) findViewById(R.id.numbers_text_view);
        numbersTextView .setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                categoryToast= getResources().getString(R.string.toast_category_remarks)+ " "  +
                        getResources().getString(R.string.category_number_text)+ " "  + getResources().getString(R.string.toast_category_example);
                Toast.makeText(v.getContext(),  categoryToast , Toast.LENGTH_SHORT).show();
                Intent numberIntent = new Intent(MainActivity.this, NumbersActivity.class);
                startActivity(numberIntent);
            }
        });

        /**
         * 3rd method for linking an activity recommended and very short
         * for  Family_MembersActivity
         */
        TextView familyTextView = (TextView) findViewById(R.id.family_text_view);
        familyTextView .setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                categoryToast= getResources().getString(R.string.toast_category_remarks) + " " +
                        getResources().getString(R.string.category_family_members_text) + " " +getResources().getString(R.string.toast_category_example);
                Toast.makeText(v.getContext(), categoryToast, Toast.LENGTH_SHORT).show();
                Intent familyMembers = new Intent(MainActivity.this, Family_MembersActivity.class);
                startActivity(familyMembers);
            }
        });

        /*
        TextView ColorsList = (TextView) findViewById(R.id.colors_text_view);
        ColorsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Open the List of Family Members", Toast.LENGTH_SHORT).show();
                Intent ColorsList = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(ColorsList);
            }
        });*/

        final TextView ColorsTextView  = (TextView) findViewById(R.id.colors_text_view);
        ColorsTextView .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorsList(ColorsTextView);
            }
        });

        final TextView PhrasesTextView = (TextView) findViewById(R.id.phrases_text_view);
        PhrasesTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhrasesList(PhrasesTextView);
            }
        });
        }

    //For menu_main_main.xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_about_id) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
            finish();
            return true;


        }


        if (id == android.R.id.home) {
            ////exit application completely and kill all running tasks
            System.exit(0);
            return true;

        }




        if (id == R.id.action_share_id) {
            yorubaShareIntent();
            /*Intent sendIntent = new Intent();
            Uri url = Uri.parse("https://drive.google.com/file/d/16QRjLOhcZA-dvydhH4Q1Fnpf28eyWNF-/view?usp=drivesdk");
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hi!, I'm using "+getString(R.string.app_name)+" to improve and understand Yoruba Language Better " +
                    ". Get the application here "+url);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
            return true;
            */
        }

        return super.onOptionsItemSelected(item);
    }


        /**
         * 2nd Method for linking an activity
            public void openNumbersList(View view) {
              Intent i = new Intent(this, NumbersActivity.class);
               startActivity(i);
           }
        **/


         /**
        * 3rd Method for linking an activity
          * ColorsActivity & PhrasesActivity
          * for ColorsActivity
          **/
    public void openColorsList(View view) {
        categoryToast= getResources().getString(R.string.toast_category_remarks) + " " +
                getResources().getString(R.string.category_colors_text)+ " " + getResources().getString(R.string.toast_category_example);
        Toast.makeText(view.getContext(), categoryToast, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MainActivity.this, ColorsActivity.class);
        startActivity(i);
    }

    /**
     * for PhrasesActivity
     **/
    public void openPhrasesList(View view) {
        categoryToast= getResources().getString(R.string.toast_category_remarks)+ " "  +
                getResources().getString(R.string.category_phrases_text) + " "+ getResources().getString(R.string.toast_category_example);
        Toast.makeText(view.getContext(), categoryToast, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MainActivity.this, PhrasesActivity.class);
        startActivity(i);
    }


    public void yorubaShareIntent() {
        Intent sendIntent = new Intent();
        Uri url = Uri.parse("https://drive.google.com/file/d/16QRjLOhcZA-dvydhH4Q1Fnpf28eyWNF-/view?usp=drivesdk");
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hi!, I'm using "+getString(R.string.app_name)+" to improve and understand Yoruba Language Better " +
                ". Get the application here "+url);
        sendIntent.setType("text/plain");
         startActivity(sendIntent);


    }



}
