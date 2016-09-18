package com.koz4.koz4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    TextView mTextViewStan;
    Button mButtonSlonce;
    Button mButtonChmury;

    Firebase mRef;


    private static final String GEO_FIRE_DB = "https://koz4-c8914.firebaseio.com";
    private static final String GEO_FIRE_WE = "https://koz4-c8914.firebaseio.com/Stan";
    private static final String GEO_FIRE_REF = GEO_FIRE_DB + "/Geo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mTextViewStan = (TextView)findViewById(R.id.textViewStan);
        mButtonSlonce = (Button) findViewById(R.id.buttonSlonce);
        mButtonChmury = (Button)findViewById(R.id.buttonChmury);

        mRef = new Firebase(GEO_FIRE_WE);

        GeoFire geoFire = new GeoFire(FirebaseDatabase.getInstance().getReferenceFromUrl(GEO_FIRE_REF));


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                mTextViewStan.setText(text);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
        mButtonChmury.setOnClickListener(new View.OnClickListener(){
                                             @Override
                                             public void onClick(View view) {
                                                 mRef.setValue("Chrmury");
                                             }
                                         });

        mButtonSlonce.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mRef.setValue("Słońce");
            }
        });

        geoFire.setLocation("warszawa", new GeoLocation(37.7853889, -122.4056973));
    }
}
