package com.nexu.projet.miashs.nexu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BarreDeProgres extends AppCompatActivity{
    protected static int timer_runtime = 1000;
    private ProgressBar simpleProgressBar;
    private TextView loading;
    protected boolean nbActive;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.statistiques);
        simpleProgressBar = (ProgressBar)findViewById(R.id.progressBar2);
        loading =(TextView)findViewById(R.id.loading);
        //timer_runtime = R.id.max * 3600;

        final Thread timerThread = new Thread(){
            @Override
            public void run(){
                nbActive = true;
                try{
                    int waited=0;
                    while(nbActive&&(waited<timer_runtime)){
                        sleep(200) ;
                        if(nbActive){
                            waited+=200;
                            updateProgress(waited);
                        }
                    }
                }catch(InterruptedException e){
                    //En cas d'ereur
                }finally{
                    onContinue();
                }
            }
        };
        timerThread.start();
    }


    public void updateProgress(int timePassed){
        if(null!=simpleProgressBar){
            int progress= simpleProgressBar.getMax() *timePassed / timer_runtime;
            simpleProgressBar.setProgress(progress);
        }

    }

    public void onContinue(){
        loading.setVisibility(View.VISIBLE);
    }
}
