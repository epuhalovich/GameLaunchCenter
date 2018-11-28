package fall2018.csc2017.slidingtiles.memory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.memory.MemoryBoard;


import java.io.Serializable;

public class MemoryGameActivity extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_board);
    }
}
