package com.devofure.core.library.ui.cardElevation;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class CardPageAdapterBase<I> extends PagerAdapter implements CardAdapter {

	private List<CardView> mViews;
	private List<I> mData;
	private float mBaseElevation;

	public CardPageAdapterBase() {
		mData = new ArrayList<>();
		mViews = new ArrayList<>();
	}

	public void addCardItem(I item) {
		mViews.add(null);
		mData.add(item);
	}

	public float getBaseElevation() {
		return mBaseElevation;
	}

	@Override
	public CardView getCardViewAt(int position) {
		return mViews.get(position);
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@NonNull
	@Override
	public Object instantiateItem(@NonNull ViewGroup container, int position) {
		View view = LayoutInflater.from(container.getContext())
				.inflate(getLayout(), container, false);
		container.addView(view);
		bind(mData.get(position), view);
		CardView cardView = findCardView(view);

		if (mBaseElevation == 0) {
			mBaseElevation = cardView.getCardElevation();
		}

		cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
		mViews.set(position, cardView);
		return view;
	}

	protected abstract int getLayout();

	public abstract void bind(I item, View view);

	protected abstract CardView findCardView(View view);

	@Override
	public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
		container.removeView((View) object);
		mViews.set(position, null);
	}

	@Override
	public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
		return view == object;
	}
}