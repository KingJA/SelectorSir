package sample.kingja.selectorsir;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kingja.selectorsir.wheelview.ChangeAddressDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void show(View view) {
        ChangeAddressDialog changeAddressDialog = new ChangeAddressDialog(this);
        changeAddressDialog.setAddress("浙江省","温州市","龙湾区","","","");
        changeAddressDialog.show();
    }
}
