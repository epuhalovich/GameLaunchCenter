package fall2018.csc2017.slidingtiles.memory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.memory.MemoryBoard;

public class MemoryGameActivity extends AppCompatActivity{

    TextView step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_board);

    }


}
