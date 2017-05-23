package com.mioffers.malloffer.core.connectivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by laxman.muttineni on 19/05/17.
 */
public class FireBaseHandler {

    DatabaseReference databaseRef;

    public FireBaseHandler() {
        databaseRef = FirebaseDatabase.getInstance().getReference();
    }



}
