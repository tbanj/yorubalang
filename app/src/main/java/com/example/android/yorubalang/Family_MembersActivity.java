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

public class Family_MembersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    private MenuItem itemToHide;

    /*This listener gets triggered when the
*{@link Mediaplayer}has completed playing the audio file
* */
    private MediaPlayer.OnCompletionListener mmCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText(Family_MembersActivity.this,R.string.toast_voice_remarks, Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.activity_family__members);

        //Create and setup the {@link AudioManager} to request audiofocus
        mAudioManager =(AudioManager) getSystemService(AUDIO_SERVICE);


        final ArrayList<word> words = new ArrayList<word>();
        words.add(new word("father","baba",R.drawable.family_father,R.raw.family_father));
        words.add(new word("mother","mama",R.drawable.family_mother,R.raw.family_mother));
        words.add(new word("son","ọmọkunrin", R.drawable.family_son,R.raw.family_son));
        words.add(new word("daughter","ọmọbinrin", R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new word("older brother","egbon ọmọkunrin", R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new word("younger brother","aburo ọmọkunrin", R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words.add(new word("older sister","egbon ọmọbinrin", R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new word("younger sister","aburo ọmọbinrin", R.drawable.family_younger_sister,R.raw.family_younger_sister));
        words.add(new word("grandmother","iya agba", R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new word("grandfather","bami agba", R.drawable.family_grandfather,R.raw.family_grandfather));




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



        WordAdapter itemsAdapter =new WordAdapter(this, words, R.color.category_family);



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

                mMediaPlayer = MediaPlayer.create(Family_MembersActivity.this, m.getMediaId());
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
