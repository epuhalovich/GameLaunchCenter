package fall2018.csc2017.slidingtiles.memorygame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import fall2018.csc2017.slidingtiles.R;

import java.util.Arrays;
import java.util.Collections;

public class MemoryGameActivity extends AppCompatActivity {

    ImageView icon_11, icon_12, icon_13, icon_14, icon_21, icon_22, icon_23, icon_24, icon_31, icon_32, icon_33, icon_34, icon_41, icon_42, icon_43, icon_44;
    TextView scoretitle, score;

    Integer[] CardCollection = {10, 11, 12, 13, 14, 15, 16, 17, 20, 21, 22, 23, 24, 25, 26, 27};
    int front10, front11, front12, front13, front14, front15, front16, front17, front20, front21, front22, front23, front24, front25, front26, front27 ;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        Collections.shuffle(Arrays.asList(CardCollection));

        FrontCardIcon();

        icon_11 = (ImageView) findViewById(R.id.icon_11);
        icon_11.setTag("11");
        icon_11.setClickable(true);
        icon_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int CardValue = Integer.parseInt((String) v.getTag());
                CardIcon(icon_11, CardValue);
            }
        });
        icon_12 = (ImageView) findViewById(R.id.icon_12);
        icon_12.setTag("12");
        icon_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int CardValue = Integer.parseInt((String) v.getTag());
                CardIcon(icon_12, CardValue);
            }
        });
        icon_13 = (ImageView) findViewById(R.id.icon_13);
        icon_13.setTag("13");
        icon_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int CardValue = Integer.parseInt((String) v.getTag());
                CardIcon(icon_13, CardValue);
            }
        });
        icon_14 = (ImageView) findViewById(R.id.icon_14);
        icon_14.setTag("14");
        icon_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int CardValue = Integer.parseInt((String) v.getTag());
                CardIcon(icon_14, CardValue);
            }
        });
        icon_21 = (ImageView) findViewById(R.id.icon_21);
        icon_21.setTag("21");
        icon_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int CardValue = Integer.parseInt((String) v.getTag());
                CardIcon(icon_21, CardValue);
            }
        });
        icon_22 = (ImageView) findViewById(R.id.icon_22);
        icon_22.setTag("22");
        icon_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int CardValue = Integer.parseInt((String) v.getTag());
                CardIcon(icon_22, CardValue);
            }
        });
        icon_23 = (ImageView) findViewById(R.id.icon_23);
        icon_23.setTag("23");
        icon_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int CardValue = Integer.parseInt((String) v.getTag());
                CardIcon(icon_23, CardValue);
            }
        });
        icon_24 = (ImageView) findViewById(R.id.icon_24);
        icon_24.setTag("24");
        icon_24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int CardValue = Integer.parseInt((String) v.getTag());
                CardIcon(icon_24, CardValue);
            }
        });
        icon_31 = (ImageView) findViewById(R.id.icon_31);
        icon_31.setTag("31");
        icon_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int CardValue = Integer.parseInt((String) v.getTag());
                CardIcon(icon_31, CardValue);
            }
        });
        icon_32 = (ImageView) findViewById(R.id.icon_32);
        icon_32.setTag("32");
        icon_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int CardValue = Integer.parseInt((String) v.getTag());
                CardIcon(icon_32, CardValue);
            }
        });
        icon_33 = (ImageView) findViewById(R.id.icon_33);
        icon_33.setTag("33");
        icon_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int CardValue = Integer.parseInt((String) v.getTag());
                CardIcon(icon_33, CardValue);
            }
        });
        icon_34 = (ImageView) findViewById(R.id.icon_34);
        icon_34.setTag("34");
        icon_34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int CardValue = Integer.parseInt((String) v.getTag());
                CardIcon(icon_34, CardValue);
            }
        });
        icon_41 = (ImageView) findViewById(R.id.icon_41);
        icon_41.setTag("41");
        icon_41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int CardValue = Integer.parseInt((String) v.getTag());
                CardIcon(icon_41, CardValue);
            }
        });
        icon_42 = (ImageView) findViewById(R.id.icon_42);
        icon_42.setTag("42");
        icon_42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int CardValue = Integer.parseInt((String) v.getTag());
                CardIcon(icon_42, CardValue);
            }
        });
        icon_43 = (ImageView) findViewById(R.id.icon_43);
        icon_43.setTag("43");
        icon_43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int CardValue = Integer.parseInt((String) v.getTag());
                CardIcon(icon_43, CardValue);
            }
        });
        icon_44 = (ImageView) findViewById(R.id.icon_44);
        icon_44.setTag("44");
        icon_44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int CardValue = Integer.parseInt((String) v.getTag());
                CardIcon(icon_44, CardValue);
            }
        });
        scoretitle = (TextView) findViewById(R.id.scoretitle);
        score = (TextView) findViewById(R.id.score);
    }
    private void CardIcon(ImageView icon, int card) {
        if (CardCollection[card] == 10) {
            icon.setImageResource(front10);
        } else if (CardCollection[card] == 11) {
            icon.setImageResource(front11);
        } else if (CardCollection[card] == 12) {
            icon.setImageResource(front12);
        } else if (CardCollection[card] == 13) {
            icon.setImageResource(front13);
        } else if (CardCollection[card] == 14) {
            icon.setImageResource(front14);
        } else if (CardCollection[card] == 15) {
            icon.setImageResource(front15);
        } else if (CardCollection[card] == 16) {
            icon.setImageResource(front16);
        } else if (CardCollection[card] == 17) {
            icon.setImageResource(front17);
        } else if (CardCollection[card] == 20) {
            icon.setImageResource(front20);
        } else if (CardCollection[card] == 21) {
            icon.setImageResource(front21);
        } else if (CardCollection[card] == 22) {
            icon.setImageResource(front22);
        } else if (CardCollection[card] == 23) {
            icon.setImageResource(front23);
        } else if (CardCollection[card] == 24) {
            icon.setImageResource(front24);
        } else if (CardCollection[card] == 25) {
            icon.setImageResource(front25);
        } else if (CardCollection[card] == 26) {
            icon.setImageResource(front26);
        } else if (CardCollection[card] == 27) {
            icon.setImageResource(front27);
        }
    }
    private void FrontCardIcon(){
            front10 = R.drawable.front_10
    }
}