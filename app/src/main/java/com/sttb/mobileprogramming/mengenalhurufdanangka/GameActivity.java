package com.sttb.mobileprogramming.mengenalhurufdanangka;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daprlabs.cardstack.SwipeDeck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class GameActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SwipeDeck cardStack;
    private Context context = this;

    private GameActivity.SwipeDeckAdapter adapter;
    private ArrayList<String> titleData;
    private ArrayList<String> subTitleData;
    private int[] imageData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);
        cardStack.setHardwareAccelerationEnabled(true);

        setArrayData();

        adapter = new GameActivity.SwipeDeckAdapter(titleData, subTitleData, imageData, this);
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

        GameActivity.SwipeDeckAdapter adapter = new GameActivity.SwipeDeckAdapter(titleData, subTitleData, imageData, context);
        cardStack.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setArrayData() {
        titleData = new ArrayList<>();
        titleData.add("A a");
        titleData.add("B b");
        titleData.add("C c");
        titleData.add("D d");
        titleData.add("G g");
        titleData.add("H h");
        titleData.add("I i");
        titleData.add("J j");
        titleData.add("K k");
        titleData.add("L l");
        titleData.add("M m");
        titleData.add("R r");
        titleData.add("S s");
        titleData.add("T t");
        titleData.add("U u");
        titleData.add("V v");
        titleData.add("W w");
        titleData.add("Y y");
        titleData.add("Z z");


        subTitleData = new ArrayList<>();
        subTitleData.add("Ayam");
        subTitleData.add("Bola");
        subTitleData.add("Ceri");
        subTitleData.add("Dadu");
        subTitleData.add("Gajah");
        subTitleData.add("Hamster");
        subTitleData.add("Ikan");
        subTitleData.add("Jerapah");
        subTitleData.add("Kuda");
        subTitleData.add("Lebah");
        subTitleData.add("Madu");
        subTitleData.add("Roti");
        subTitleData.add("Semangka");
        subTitleData.add("Telur");
        subTitleData.add("Udang");
        subTitleData.add("Vas");
        subTitleData.add("Wortel");
        subTitleData.add("Yoyo");
        subTitleData.add("Zebra");

        imageData = new int[]{
                R.drawable.ayam,
                R.drawable.bola,
                R.drawable.ceri,
                R.drawable.dadu,
                R.drawable.gajahh,
                R.drawable.hamsteer,
                R.drawable.ikann,
                R.drawable.jerapah,
                R.drawable.kuda,
                R.drawable.lebah,
                R.drawable.madu,
                R.drawable.roti,
                R.drawable.semangka,
                R.drawable.telur,
                R.drawable.udang,
                R.drawable.vas,
                R.drawable.wortel,
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
                v = inflater.inflate(R.layout.card_game, parent, false);
            }

            Random rand = new Random();
            int index = rand.nextInt((imageList.length - 1) - 0 + 1) + 0;

            RelativeLayout imageView = v.findViewById(R.id.offer_image);
            ImageView buttonPlay = v.findViewById(R.id.btn_play);
            TextView tvTitle = v.findViewById(R.id.title);
            final EditText etSubtitle = v.findViewById(R.id.subtitle);
            Button btnCek = v.findViewById(R.id.btn_cek);

            final String title = titleList.get(index);
            final String subTitle = subTitleList.get(index);
            tvTitle.setText(title);
            imageView.setBackground(getResources().getDrawable(imageList[index]));

            btnCek.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String subtitle = etSubtitle.getText().toString();
                    if (subtitle.equalsIgnoreCase(subTitle)) {
                        Toast.makeText(GameActivity.this, "Jawaban kamu benar :)", Toast.LENGTH_LONG).show();
                        cardStack.swipeTopCardRight(1);
                        hideSoftKeyboard();
                    } else {
                        Toast.makeText(GameActivity.this, "Jawaban kamu salah :(", Toast.LENGTH_LONG).show();
                        hideSoftKeyboard();
                        etSubtitle.setText("");


                    }
                }
            });

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

        protected void hideSoftKeyboard() {
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
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

