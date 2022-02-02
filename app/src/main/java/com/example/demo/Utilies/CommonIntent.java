package com.example.demo.Utilies;

import android.content.Context;
import android.content.Intent;

public class CommonIntent {
    public static void intentActivity(Context context, Class<?> nextclass)
    {
        Intent intent = new Intent(context,nextclass);
        context.startActivity(intent);
    }
}
