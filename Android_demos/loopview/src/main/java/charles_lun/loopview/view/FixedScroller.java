package charles_lun.loopview.view;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * Created by 131 on 2015/6/18.
 */
public class FixedScroller extends Scroller {
    private int mDuration = 1500;

    public FixedScroller(Context context) {
        super(context);
    }

    public FixedScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    public void setmDuration(int time) {
        mDuration = time;
    }

    public int getmDuration() {
        return mDuration;
    }
}
