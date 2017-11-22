package com.example.android.yorubalang;

/**
  Engr. Temitope
 *{@link word} represernts a vocabulary word that the user wants to learn.
 *It contains a default translation and a Miwok translation for that word.
 *
 */


public class word {
    /**Default Miwok Translation word */
    /**This is also known as state when a variable is declare */
    private String mDefaultTranslation;

    /**Default Miwok Translation word */
    /**This is also known as state when a variable is declare */
    private String mYorubaTranslation;

    private int imageResourceId= NO_IMAGE_PROVIDED ;

    private static final int NO_IMAGE_PROVIDED =-1;

    private int mediaId ;



    /**This is also known as state when a variable is declare */
    public word(String defaultTranslation, String YorubaTranslation, int mMediaId){
        mDefaultTranslation = defaultTranslation;
        mYorubaTranslation = YorubaTranslation;
        mediaId =mMediaId;

    }


    /**This is also known as state when a variable is declare */
    public word(String defaultTranslation, String YorubaTranslation, int mResourceId, int mMediaId){
        mDefaultTranslation = defaultTranslation;
        mYorubaTranslation = YorubaTranslation;
        imageResourceId = mResourceId;
        mediaId =mMediaId;

    }




    /**
     * Get the default translation of the word.
     */
    public String getdefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Get the Miwok translation of the word.
     */
    public String getYorubaTranslation() {
        return mYorubaTranslation;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getMediaId() {
        return mediaId;
    }

    /*
    * Return whether or not there is an Image Id
    * */
    public boolean hasImage(){
        return imageResourceId != NO_IMAGE_PROVIDED;
    }


}
