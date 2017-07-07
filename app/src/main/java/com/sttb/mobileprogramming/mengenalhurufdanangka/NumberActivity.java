package com.sttb.mobileprogramming.mengenalhurufdanangka;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daprlabs.cardstack.SwipeDeck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class NumberActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SwipeDeck cardStack;
    private Context context = this;

    private NumberActivity.SwipeDeckAdapter adapter;
    private ArrayList<String> titleData;
    private ArrayList<String> subTitleData;
    private int[] imageData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);
        cardStack.setHardwareAccelerationEnabled(true);

        setArrayData();

        adapter = new NumberActivity.SwipeDeckAdapter(titleData, subTitleData, imageData, this);
        cardStack.setAdapter(adapter);

        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                addDataWithPosition(position);
            }

            @Override
            public void cardSwipedRight(int position) {
                addDataWithPosition(position);
            }

            @Override
            public void cardsDepleted() {
            }

            @Override
            public void cardActionDown() {
            }

            @Override
            public void cardActionUp() {
            }

        });
    }

    private void addDataWithPosition(int position) {
        if (position == titleData.size() - 1) {
            addData();
        }
    }

    private void addData() {
        setArrayData();

        NumberActivity.SwipeDeckAdapter adapter = new NumberActivity.SwipeDeckAdapter(titleData, subTitleData, imageData, context);
        cardStack.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setArrayData() {
        titleData = new ArrayList<>();
        titleData.add("1");
        titleData.add("2");
        titleData.add("3");
        titleData.add("4");
        titleData.add("5");
        titleData.add("6");
        titleData.add("7");
        titleData.add("8");
        titleData.add("9");
        titleData.add("10");


        subTitleData = new ArrayList<>();
        subTitleData.add("Satu");
        subTitleData.add("Dua");
        subTitleData.add("Tiga");
        subTitleData.add("Empat");
        subTitleData.add("Lima");
        subTitleData.add("Enam");
        subTitleData.add("Tujuh");
        subTitleData.add("Delapan");
        subTitleData.add("Sembilan");
        subTitleData.add("Sepuluh");


        imageData = new int[]{
                R.drawable.satuu,
                R.drawable.dua,
                R.drawable.tiga,
                R.drawable.empat,
                R.drawable.five,
                R.drawable.enam,
                R.drawable.tujuh,
                R.drawable.delapan,
                R.drawable.sembilan,
                R.drawable.ten
        };
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public class SwipeDeckAdapter extends BaseAdapter {

        private List<String> titleList;
        private List<String> subTitleList;
        private int[] imageList;
        private Context context;
        private TextToSpeech tts;


        public SwipeDeckAdapter(List<String> titleList, List<String> subTitleList, int[] imageList, Context context) {
            this.titleList = titleList;
            this.subTitleList = subTitleList;
            this.imageList = imageList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return titleList.size();
        }

        @Override
        public Object getItem(int position) {
            return titleList.get(position);
        }


        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View v = convertView;
            if (v == null) {
                LayoutInflater inflater = getLayoutInflater();
                v = inflater.inflate(R.layout.card, parent, false);
            }

            RelativeLayout imageView = (RelativeLayout) v.findViewById(R.id.offer_image);
            ImageView buttonPlay = (ImageView) v.findViewById(R.id.btn_play);
            imageView.setBackground(getResources().getDrawable(imageList[position]));
//            Picasso.with(context).load(imageList[position]).fit().centerCrop().into(imageView);
            TextView tvTitle = (TextView) v.findViewById(R.id.title);
            TextView tvSubtitle = (TextView) v.findViewById(R.id.subtitle);
            final String title = (String) titleList.get(position);
            final String subTitle = (String) subTitleList.get(position);
            tvTitle.setText(title);
            tvSubtitle.setText(subTitle);

            buttonPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {

                        @Override
                        public void onInit(int status) {
                            // TODO Auto-generated method stub
                            if (status == TextToSpeech.SUCCESS) {
                                tts.setLanguage(new Locale("id", "ID"));
                                speakOut(subTitle);
                            }
                        }
                    });

                }
            });

            return v;
        }

        private String removeLastChar(String str) {
            return str.substring(0, str.length() - 1);
        }

        private void speakOut(String value) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "UniqueID");
            tts.speak(value, TextToSpeech.QUEUE_FLUSH, map);
        }
    }
}

