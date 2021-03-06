package com.devofure.core.library.ui.cardElevation;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.cardview.widget.CardView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class CardPagerAdapter<I extends Fragment & CardPagerAdapter.CardHolderFragment> extends FragmentStatePagerAdapter implements CardAdapter {

	private List<I> cardList;
	private float elevation = 2;

	public CardPagerAdapter(FragmentManager fm) {
		super(fm);
		cardList = new ArrayList<>();
	}

	@Override
	public Fragment getItem(int position) {
		return cardList.get(position);
	}

	@NonNull
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		Object fragment = super.instantiateItem(container, position);
		cardList.set(position, (I) fragment);
		return fragment;
	}

	@Override
	public float getBaseElevation() {
		return elevation;
	}

	@Override
	public CardView getCardViewAt(int position) {
		if (position < cardList.size()) {
			return cardList.get(position).getCardView();
		}
		return null;
	}

	@Override
	public int getCount() {
		return cardList.size();
	}

	public void setElevation(float elevation) {
		this.elevation = elevation;
	}

	public void addAllCardFragments(List<I> fragments) {
		cardList.addAll(fragments);
	}

	public void setAllCardFragments(List<I> fragments) {
		cardList.clear();
		cardList.addAll(fragments);
	}

	public interface CardHolderFragment {
		CardView getCardView();
	}
}
