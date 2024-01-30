package com.example.kingfl;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        CharSequence widgetText = context.getString(R.string.appwidget_text, calculatePastDaysFromCurrentYear(), calculateRemainingDaysFromCurrentYear());
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private static int calculateRemainingDaysFromCurrentYear() {
        // Get the current date
        Calendar currentDate = Calendar.getInstance();

        // Set the date to January 1st of the current year
        Calendar endOfYear = Calendar.getInstance();
        endOfYear.set(Calendar.MONTH, Calendar.DECEMBER);
        endOfYear.set(Calendar.DAY_OF_MONTH, 31);

        // Calculate the difference in days
        long diffMillis = endOfYear.getTimeInMillis() - currentDate.getTimeInMillis();
        int RemainingDaysFromCurrentYear = (int) TimeUnit.MILLISECONDS.toDays(diffMillis);
        return RemainingDaysFromCurrentYear + 1;
    }

    private static int calculatePastDaysFromCurrentYear() {
        Calendar current = Calendar.getInstance();

        Calendar startOfYear = Calendar.getInstance();
        startOfYear.set(Calendar.MONTH, Calendar.JANUARY);
        startOfYear.set(Calendar.DAY_OF_MONTH, 1);

        long diffMillis = current.getTimeInMillis() - startOfYear.getTimeInMillis();
        int daysFromCurrentYear = (int) TimeUnit.MILLISECONDS.toDays(diffMillis);
        return daysFromCurrentYear;
    }
}