package com.shelly.solarmonitor.presentation.ui.adapter;

import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shelly.solarmonitor.domin.model.CardItemModel;
import com.ty.solarmonitor.R;

public class CollectorRecyclerAdapter extends
		RecyclerView.Adapter<CollectorRecyclerAdapter.CollectoriewHolder> {

	public interface OnRecyclerViewListener {
		void onItemClick(int position);

		boolean onItemLongClick(int position);
	}

	private OnRecyclerViewListener mOnRecyclerViewListener;

	public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
		this.mOnRecyclerViewListener = onRecyclerViewListener;
	}

	public List<CardItemModel> cardItems;

	public CollectorRecyclerAdapter(List<CardItemModel> cardItems) {
		this.cardItems = cardItems;
	}

	public class CollectoriewHolder extends RecyclerView.ViewHolder implements OnClickListener,
			OnLongClickListener {
		View rootView;
		TextView title;
		TextView content;
		int position;

		public CollectoriewHolder(View itemView) {
			super(itemView);
			this.title = (TextView) itemView.findViewById(R.id.card_title);
			this.content = (TextView) itemView.findViewById(R.id.card_content);
			this.rootView = itemView.findViewById(R.id.rcl_rootview);
			rootView.setOnClickListener(this);
			rootView.setOnLongClickListener(this);
		}

		@Override
		public boolean onLongClick(View v) {
			if (mOnRecyclerViewListener != null) {
				return mOnRecyclerViewListener.onItemLongClick(position);
			}
			return false;
		}

		@Override
		public void onClick(View v) {
			if (mOnRecyclerViewListener != null) {
				mOnRecyclerViewListener.onItemClick(position);
			}
		}
	}

	@Override
	public CollectoriewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
		CollectoriewHolder viewHolder = new CollectoriewHolder(view);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(CollectoriewHolder holder, int position) {
		holder.title.setText(cardItems.get(position).title);
		holder.content.setText(cardItems.get(position).content);
		holder.position = position;
	}

	@Override
	public int getItemCount() {
		return cardItems.size();
	}
}
