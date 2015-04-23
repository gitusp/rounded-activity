package ro.tsuku.roundedactivity;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by usp on 4/23/15.
 */
public class RoundHelper {
    /**
     * Clips corners of the given activity
     * @param activity
     */
    public static void round(final Activity activity) {
        final Window window = activity.getWindow();
        final View decorView = window.getDecorView();

        // observing the end of its layout
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // getting the screen information
                Rect usableRect = new Rect();
                decorView.getWindowVisibleDisplayFrame(usableRect);
                int bottom = activity.findViewById(Window.ID_ANDROID_CONTENT).getBottom();
                // creating imageview
                ImageView imageView = new ImageView(activity);
                imageView.setImageResource(R.drawable.rounded_frame);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                // creating layout params
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(usableRect.width(), bottom - usableRect.top);
                layoutParams.setMargins(0, usableRect.top, 0, 0);
                imageView.setLayoutParams(layoutParams);
                ((ViewGroup) decorView).addView(imageView);
                // removing this listener
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    decorView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    decorView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });

    }
}
