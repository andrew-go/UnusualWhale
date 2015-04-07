package andrii.goncharenko.unusualwhale.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Andrey on 17.03.2015.
 */
public class BaseView extends View {

    protected Context context;

    public BaseView(Context context) {
        super(context);
        this.context = context;
    }

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public BaseView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

}
