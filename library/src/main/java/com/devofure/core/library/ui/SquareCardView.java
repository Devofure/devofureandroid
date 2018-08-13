package com.devofure.core.library.ui;

import android.content.Context;
import androidx.cardview.widget.CardView;
import android.util.AttributeSet;


/**
 * squareCard
 */
public class SquareCardView extends CardView {

	public SquareCardView(Context context) {
		super(context);
	}

	public SquareCardView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SquareCardView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//noinspection SuspiciousNameCombination
		super.onMeasure(widthMeasureSpec, widthMeasureSpec); // This is the key that will make the height equivalent to its width
	}
}