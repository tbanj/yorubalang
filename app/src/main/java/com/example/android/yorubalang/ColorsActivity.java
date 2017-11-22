package com.example.android.yorubalang;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    private MenuItem itemToHide;
    /*This listener gets triggered when the
*{@link Mediaplayer}has completed playing the audio file
* */
    private MediaPlayer.OnCompletionListener mmCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText(ColorsActivity.this, R.string.toast_voice_remarks, Toast.LENGTH_SHORT).show();
            releaseMediaPlayer();
        }
    };

    AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    /*if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                     AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
                     || it means or
                    */
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                        // Pause playback because your Audio Focus was
                        // temporarily stolen, but will be back soon.
                        // i.e. for a phone call
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);

                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        // Stop playback, because you lost the Audio Focus.
                        // i.e. the user started some other playback app
                        // Remember to unregister your controls/buttons here.
                        // And release the kra — Audio Focus!
                        // You’re done.
                        mMediaPlayer.stop();
                    } else if (focusChange ==
                            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // Lower the volume, because something else is also
                        // playing audio over you.
                        // i.e. for notifications or navigation directions
                        // Depending on your audio playback, you may prefer to
                        // pause playback here instead. You do you.
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Resume playback, because you hold the Audio Focus
                        // again!
                        // i.e. the phone call ended or the nav directions
                        // are finished
                        // If you implement ducking and lower the volume, be
                        // sure to return it to normal here, as well.
                        mMediaPlayer.start();
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);
        mAudioManager =(AudioManager) getSystemService(AUDIO_SERVICE);

        final ArrayList<word> words = new ArrayList<word>();

        words.add(new word("red","pupa",R.drawable.color_red,R.raw.color_red));

        //words.add(new word("mustard yellow","chiwitta",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
        words.add(new word("yellow","ìyèyè", R.drawable.color_yellow,R.raw.color_yellow));
        words.add(new word("green","àwo ewéko", R.drawable.color_green,R.raw.color_green));
        words.add(new word("brown", "àwo ara", R.drawable.color_brown,R.raw.color_brown));
        words.add(new word("gray", "àwö eléérú", R.drawable.color_grey,R.raw.color_gray));
        words.add(new word("black", "dúdú", R.drawable.color_black,R.raw.color_black));
        words.add(new word("white","funfun", R.drawable.color_white,R.raw.color_white));

        words.add(new word("blue", "àwö sánmà", R.drawable.color_blue,R.raw.color_blue));
        words.add(new word("gold", "wúra", R.drawable.color_gold,R.raw.color_gold));
        words.add(new word("silver","fàdákà", R.drawable.color_silver,R.raw.color_silver));

        words.add(new word("purple", "àwö elésè àlùkò", R.drawable.color_purple,R.raw.color_purple));
        words.add(new word("pink","pupa fêêrê", R.drawable.color_pink,R.raw.color_pink));




             /*1--To declare a TextView inline in a java which wont be reusable in another part
        LinearLayout rootview =(LinearLayout) findViewById(R.id.rootview);
        for loop
        int index;
        for (index=0 ; index<words.size(); index++){
           // wordView.setText(words.get(index));
            LinearLayout rootview =(LinearLayout) findViewById(R.id.rootview);
            TextView wordView = new TextView(this);
            wordView.setText(words.get(index));

            rootview.addView(wordView);
            Log.v("NumberActivity", "word at index " +index +" "+ words.get(index));
        */



        WordAdapter itemsAdapter =new WordAdapter(this, words, R.color.category_colors);



        ListView listView = (ListView) findViewById(R.id.list);


        /* Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
         1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
         listview can take value of ArrayAdapter or ListAdapter, BaseAdapter
        */
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                word m=words.get(position);

                //to print each word to the log
                Log.v("FamilyActivity", "Current word: " + words);

                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(afChangeListener,
                        //use music stream
                        AudioManager.STREAM_MUSIC,
                        //request audio focus for short period since miwork audio app is just few secs each
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result ==mAudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    //mAudioManager.registerMediaButtonEventReceiver(RemoteControlReceiver);
                    //We have audiofocus now, start playback
                }

                mMediaPlayer = MediaPlayer.create(ColorsActivity.this, m.getMediaId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mmCompletionListener);
            }



        });
    }

    //Below code is use to display the Share item @ the toolbar of this activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.


        //itemToHide.setVisible(false);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //invalidateOptionsMenu();
        itemToHide = menu.findItem(R.id.action_about_id);
        itemToHide.setVisible(false);
        //itemToShow = menu.findItem(R.id.action_about_id);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected  (MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();





        if (id == R.id.action_share_id) {
            //itemToShow.setVisible(true);

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
    @Override
    protected void onStart() {
        super.onStart();
        Log.v("MainActivity", "onStart"+ "This method is activated when the app is launch + \n" +
                " by the user which run simultanously with onCreate() and onResume() method  ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("MainActivity", "onResume " +"onResume() is call when the user go back to view the \n" +
                "minimize which he was interacting with before");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("MainActivity", "onPause "+ "it runs together with onStop() but its the first method \n" +
                "to be called before onStop()");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.v("MainActivity", "onStop"+ "This method is activated when the app is minimize + \n" +
                " by the user ");
        releaseMediaPlayer();


    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(afChangeListener);
        }

    }

}
