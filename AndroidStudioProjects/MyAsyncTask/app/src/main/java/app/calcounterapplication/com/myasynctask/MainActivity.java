package app.calcounterapplication.com.myasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView MainList;
    private String [] Text ={"ido","shmuel","ido","nir","yea","ido","shmuel","ido","nir","yea","ido","shmuel","ido","nir","yea","ido","shmuel","ido","nir","yea",
            "ido","shmuel","ido","nir","yea","ido","shmuel","ido","nir","yea",
            "ido","shmuel","ido","nir","yea"  };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);
        MainList=(ListView)findViewById(R.id.ListView1);
        MainList.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,new ArrayList<String>()));
        new MyTask().execute();





    }
    class MyTask extends AsyncTask<Void,String,Void>{
        private ArrayAdapter adapter;
        private int count=0;
        @Override
        protected void onPreExecute() {
            adapter=(ArrayAdapter<String>) MainList.getAdapter();





        }

        @Override
        protected Void doInBackground(Void... voids) {
            for( String item: Text){
                publishProgress(item);
                    try {
                        Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            adapter.add(values[0]);
            count++;
            setProgress((int)(((double)count/Text.length)*10000));


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(MainActivity.this,"ALL Done",Toast.LENGTH_LONG).show();
            setProgressBarVisibility(false);
        }
    }
}
