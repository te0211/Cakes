package com.teo.cakes.view.callback;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.*;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.teo.cakes.R;
import com.teo.cakes.service.model.Cake;


import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class CakeClickCallback {

    public void onClick(View view, Cake cake) {

        Context context = view.getContext();

        Log.d("onclick", cake.getDesc());


        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = (View) inflater.inflate(R.layout.activity_cakes_detail, null);

        TextView headerTextView = popupView.findViewById(R.id.desc);
        headerTextView.setText(cake.getDesc());
        headerTextView.setTextSize(15);
        headerTextView.setBackgroundColor(Color.GREEN);


        //Create popup window.
        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // Set popup window animation style.
        popupWindow.setAnimationStyle(R.style.popup_window_animation);

        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        popupWindow.setFocusable(true);

        popupWindow.setOutsideTouchable(true);

        popupWindow.update();

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 10, 10);

        // dismiss the popup window when touched
//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                popupWindow.dismiss();
//                return true;
//            }
//        });

    }
}

