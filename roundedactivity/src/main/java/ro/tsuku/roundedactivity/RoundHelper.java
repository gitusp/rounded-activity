package ro.tsuku.roundedactivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
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
        final ViewGroup decorView = (ViewGroup) window.getDecorView();
        final View contentView = decorView.findViewById(android.R.id.content);

        // Create image view.
        final ImageView imageView = new ImageView(activity);
        imageView.setImageResource(R.drawable.rounded_frame);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        decorView.addView(imageView);

        // observing the end of its layout.
        contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // getting the screen information
                Rect usableRect = new Rect();
                decorView.getWindowVisibleDisplayFrame(usableRect);
                // creating layout params
                FrameLayout.LayoutParams layoutParams;
                if ((window.getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) != 0) {
                    // Full screen.
                    // HACK: getWindowVisibleDisplayFrame doesn't reflect status bar immediately.
                    layoutParams = new FrameLayout.LayoutParams(contentView.getWidth(), contentView.getHeight() + usableRect.top);
                    layoutParams.setMargins(usableRect.left, 0, 0, 0);
                } else {
                    // Not full screen.
                    layoutParams = new FrameLayout.LayoutParams(contentView.getWidth(), contentView.getHeight());
                    layoutParams.setMargins(usableRect.left, usableRect.top, 0, 0);
                }
                // Apply layout params.
                imageView.setLayoutParams(layoutParams);
            }
        });
    }
}
