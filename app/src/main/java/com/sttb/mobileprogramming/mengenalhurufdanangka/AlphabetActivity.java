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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AlphabetActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SwipeDeck cardStack;
    private Context context = this;

    private SwipeDeckAdapter adapter;
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

        adapter = new SwipeDeckAdapter(titleData, subTitleData, imageData, this);
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

        SwipeDeckAdapter adapter = new SwipeDeckAdapter(titleData, subTitleData, imageData, context);
        cardStack.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setArrayData() {
        titleData = new ArrayList<>();
        titleData.add("A a");
        titleData.add("B b");
        titleData.add("C c");
        titleData.add("D d");
        titleData.add("E e");
        titleData.add("F f");
        titleData.add("G g");
        titleData.add("H h");
        titleData.add("I i");
        titleData.add("J j");
        titleData.add("K k");
        titleData.add("L l");
        titleData.add("M m");
        titleData.add("N n");
        titleData.add("O o");
        titleData.add("P p");
        titleData.add("Q q");
        titleData.add("R r");
        titleData.add("S s");
        titleData.add("T t");
        titleData.add("U u");
        titleData.add("V v");
        titleData.add("W w");
        titleData.add("X x");
        titleData.add("Y y");
        titleData.add("Z z");


        subTitleData = new ArrayList<>();
        subTitleData.add("Ayam");
        subTitleData.add("Bola");
        subTitleData.add("Ceri");
        subTitleData.add("Dadu");
        subTitleData.add("Es Krim");
        subTitleData.add("Foto");
        subTitleData.add("Gajah");
        subTitleData.add("Hamster");
        subTitleData.add("Ikan");
        subTitleData.add("Jerapah");
        subTitleData.add("Kuda");
        subTitleData.add("Lebah");
        subTitleData.add("Madu");
        subTitleData.add("Nanas");
        subTitleData.add("Obor");
        subTitleData.add("Pisang");
        subTitleData.add("Queen");
        subTitleData.add("Roti");
        subTitleData.add("Semangka");
        subTitleData.add("Telur");
        subTitleData.add("Udang");
        subTitleData.add("Vas");
        subTitleData.add("Wortel");
        subTitleData.add("Xylophone");
        subTitleData.add("Yoyo");
        subTitleData.add("Zebra");

        imageData = new int[]{
                R.drawable.ayam,
                R.drawable.bola,
                R.drawable.ceri,
                R.drawable.dadu,
                R.drawable.eskrim,
                R.drawable.foto,
                R.drawable.gajahh,
                R.drawable.hamsteer,
                R.drawable.ikann,
                R.drawable.jerapah,
                R.drawable.kuda,
                R.drawable.lebah,
                R.drawable.madu,
                R.drawable.nanas,
                R.drawable.obor,
                R.drawable.pisang,
                R.drawable.queen,
                R.drawable.roti,
                R.drawable.semangka,
                R.drawable.telur,
                R.drawable.udang,
                R.drawable.vas,
                R.drawable.wortel,
                R.drawable.xylophone,
                R.drawable.yoyo,
                R.drawable.zebraa
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
                                speakOut(removeLastChar(title));
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