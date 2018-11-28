package fall2018.csc2017.slidingtiles.memory;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import fall2018.csc2017.slidingtiles.R;

import java.util.Random;

public class MemoryBoard extends AppCompatActivity {
    ImageView icon_11, icon_12, icon_13, icon_14, icon_21, icon_22, icon_23, icon_24,
            icon_31, icon_32, icon_33, icon_34, icon_41, icon_42, icon_43, icon_44;

    int count = 0;

    int[] frontcard = {R.drawable.pair_a1, R.drawable.pair_a2, R.drawable.pair_b1, R.drawable.pair_b2, R.drawable.pair_c1, R.drawable.pair_c2, R.drawable.pair_d1, R.drawable.pair_d2, R.drawable.pair_e1, R.drawable.pair_e2,
            R.drawable.pair_f1, R.drawable.pair_f2, R.drawable.pair_g1, R.drawable.pair_g2, R.drawable.pair_h1, R.drawable.pair_h2};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_board);


        icon_11 = findViewById(R.id.icon_11);
        icon_11.setClickable(true);
        icon_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random ram = new Random();
                int num = ram.nextInt(frontcard.length);
                if (count == 0) {
                    icon_11.setImageResource(frontcard[num]);
                    icon_11.invalidate();
                    count++;
                } else if (count == 1) {
                    icon_11.setImageResource(frontcard[num]);
                    icon_11.invalidate();
                    count++;
                } else if (count > 2) {
                    icon_11.setClickable(false);
                }
            }
        });

        icon_12 = findViewById(R.id.icon_12);
        icon_12.setClickable(true);
        icon_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random ram = new Random();
                int num = ram.nextInt(frontcard.length);
                if (count == 0) {
                    icon_12.setImageResource(frontcard[num]);
                    icon_12.invalidate();
                    count++;
                } else if (count == 1) {
                    icon_12.setImageResource(frontcard[num]);
                    icon_12.invalidate();
                    count++;
                } else if (count > 2) {
                    icon_12.setClickable(false);
                }
            }
        });

        icon_13 = findViewById(R.id.icon_13);
        icon_13.setClickable(true);
        icon_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random ram = new Random();
                int num = ram.nextInt(frontcard.length);
                if (count == 0) {
                    icon_13.setImageResource(frontcard[num]);
                    icon_13.invalidate();
                    count++;
                } else if (count == 1) {
                    icon_13.setImageResource(frontcard[num]);
                    icon_13.invalidate();
                    count++;
                } else if (count > 2) {
                    icon_13.setClickable(false);
                }
            }
        });

        icon_14 = findViewById(R.id.icon_14);
        icon_14.setClickable(true);
        icon_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random ram = new Random();
                int num = ram.nextInt(frontcard.length);
                if (count == 0) {
                    icon_14.setImageResource(frontcard[num]);
                    icon_14.invalidate();
                    count++;
                } else if (count == 1) {
                    icon_14.setImageResource(frontcard[num]);
                    icon_14.invalidate();
                    count++;
                } else if (count > 2) {
                    icon_14.setClickable(false);
                }
            }
        });

        icon_21 = findViewById(R.id.icon_21);
        icon_21.setClickable(true);
        icon_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random ram = new Random();
                int num = ram.nextInt(frontcard.length);
                if (count == 0) {
                    icon_21.setImageResource(frontcard[num]);
                    icon_21.invalidate();
                    count++;
                } else if (count == 1) {
                    icon_21.setImageResource(frontcard[num]);
                    icon_21.invalidate();
                    count++;
                } else if (count > 2) {
                    icon_21.setClickable(false);
                }
            }
        });

        icon_22 = findViewById(R.id.icon_22);
        icon_22.setClickable(true);
        icon_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random ram = new Random();
                int num = ram.nextInt(frontcard.length);
                if (count == 0) {
                    icon_22.setImageResource(frontcard[num]);
                    icon_22.invalidate();
                    count++;
                } else if (count == 1) {
                    icon_22.setImageResource(frontcard[num]);
                    icon_22.invalidate();
                    count++;
                } else if (count > 2) {
                    icon_22.setClickable(false);
                }
            }
        });

        icon_23 = findViewById(R.id.icon_23);
        icon_23.setClickable(true);
        icon_23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random ram = new Random();
                int num = ram.nextInt(frontcard.length);
                if (count == 0) {
                    icon_23.setImageResource(frontcard[num]);
                    icon_23.invalidate();
                    count++;
                } else if (count == 1) {
                    icon_23.setImageResource(frontcard[num]);
                    icon_23.invalidate();
                    count++;
                } else if (count > 2) {
                    icon_23.setClickable(false);
                }
            }
        });

        icon_24 = findViewById(R.id.icon_24);
        icon_24.setClickable(true);
        icon_24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random ram = new Random();
                int num = ram.nextInt(frontcard.length);
                if (count == 0) {
                    icon_24.setImageResource(frontcard[num]);
                    icon_24.invalidate();
                    count++;
                } else if (count == 1) {
                    icon_24.setImageResource(frontcard[num]);
                    icon_24.invalidate();
                    count++;
                } else if (count > 2) {
                    icon_24.setClickable(false);
                }
            }
        });

        icon_31 = findViewById(R.id.icon_31);
        icon_31.setClickable(true);
        icon_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random ram = new Random();
                int num = ram.nextInt(frontcard.length);
                if (count == 0) {
                    icon_31.setImageResource(frontcard[num]);
                    icon_31.invalidate();
                    count++;
                } else if (count == 1) {
                    icon_31.setImageResource(frontcard[num]);
                    icon_31.invalidate();
                    count++;
                } else if (count > 2) {
                    icon_31.setClickable(false);
                }
            }
        });

        icon_32 = findViewById(R.id.icon_32);
        icon_32.setClickable(true);
        icon_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random ram = new Random();
                int num = ram.nextInt(frontcard.length);
                if (count == 0) {
                    icon_32.setImageResource(frontcard[num]);
                    icon_32.invalidate();
                    count++;
                } else if (count == 1) {
                    icon_32.setImageResource(frontcard[num]);
                    icon_32.invalidate();
                    count++;
                } else if (count > 2) {
                    icon_32.setClickable(false);
                }
            }
        });

        icon_33 = findViewById(R.id.icon_33);
        icon_33.setClickable(true);
        icon_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random ram = new Random();
                int num = ram.nextInt(frontcard.length);
                if (count == 0) {
                    icon_33.setImageResource(frontcard[num]);
                    icon_33.invalidate();
                    count++;
                } else if (count == 1) {
                    icon_33.setImageResource(frontcard[num]);
                    icon_33.invalidate();
                    count++;
                } else if (count > 2) {
                    icon_33.setClickable(false);
                }
            }
        });

        icon_34 = findViewById(R.id.icon_34);
        icon_34.setClickable(true);
        icon_34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random ram = new Random();
                int num = ram.nextInt(frontcard.length);
                if (count == 0) {
                    icon_34.setImageResource(frontcard[num]);
                    icon_34.invalidate();
                    count++;
                } else if (count == 1) {
                    icon_34.setImageResource(frontcard[num]);
                    icon_34.invalidate();
                    count++;
                } else if (count > 2) {
                    icon_34.setClickable(false);
                }
            }
        });

        icon_41 = findViewById(R.id.icon_41);
        icon_41.setClickable(true);
        icon_41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random ram = new Random();
                int num = ram.nextInt(frontcard.length);
                if (count == 0) {
                    icon_41.setImageResource(frontcard[num]);
                    icon_41.invalidate();
                    count++;
                } else if (count == 1) {
                    icon_41.setImageResource(frontcard[num]);
                    icon_41.invalidate();
                    count++;
                } else if (count > 2) {
                    icon_41.setClickable(false);
                }
            }
        });

        icon_42 = findViewById(R.id.icon_42);
        icon_42.setClickable(true);
        icon_42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random ram = new Random();
                int num = ram.nextInt(frontcard.length);
                if (count == 0) {
                    icon_42.setImageResource(frontcard[num]);
                    icon_42.invalidate();
                    count++;
                } else if (count == 1) {
                    icon_42.setImageResource(frontcard[num]);
                    icon_42.invalidate();
                    count++;
                } else if (count > 2) {
                    icon_42.setClickable(false);
                }
            }
        });

        icon_43 = findViewById(R.id.icon_43);
        icon_43.setClickable(true);
        icon_43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random ram = new Random();
                int num = ram.nextInt(frontcard.length);
                if (count == 0) {
                    icon_43.setImageResource(frontcard[num]);
                    icon_43.invalidate();
                    count++;
                } else if (count == 1) {
                    icon_43.setImageResource(frontcard[num]);
                    icon_43.invalidate();
                    count++;
                } else if (count > 2) {
                    icon_43.setClickable(false);
                }
            }
        });

        icon_44 = findViewById(R.id.icon_44);
        icon_44.setClickable(true);
        icon_44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random ram = new Random();
                int num = ram.nextInt(frontcard.length);
                if (count == 0) {
                    icon_44.setImageResource(frontcard[num]);
                    icon_44.invalidate();
                    count++;
                } else if (count == 1) {
                    icon_44.setImageResource(frontcard[num]);
                    icon_44.invalidate();
                    count++;
                } else if (count > 2) {
                    icon_44.setClickable(false);
                }
            }
        });
    }
    public void CardDelay(){
        Handler delayhandler = new Handler();
        delayhandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                CardBackAgain();
            }
        }, 2000);

    }

    public void CardBackAgain() {
        if (count == 2) {
            icon_11.setImageResource(R.drawable.icon_back);
            icon_12.setImageResource(R.drawable.icon_back);
            icon_13.setImageResource(R.drawable.icon_back);
            icon_14.setImageResource(R.drawable.icon_back);
            icon_21.setImageResource(R.drawable.icon_back);
            icon_22.setImageResource(R.drawable.icon_back);
            icon_23.setImageResource(R.drawable.icon_back);
            icon_24.setImageResource(R.drawable.icon_back);
            icon_31.setImageResource(R.drawable.icon_back);
            icon_32.setImageResource(R.drawable.icon_back);
            icon_33.setImageResource(R.drawable.icon_back);
            icon_34.setImageResource(R.drawable.icon_back);
            icon_41.setImageResource(R.drawable.icon_back);
            icon_42.setImageResource(R.drawable.icon_back);
            icon_43.setImageResource(R.drawable.icon_back);
            icon_44.setImageResource(R.drawable.icon_back);
            count = 0;
        }
    }
};
