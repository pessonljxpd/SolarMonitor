package com.shelly.solarmonitor.domin.model;

import com.shelly.library.R;

/**
 * Created by Sagar on 6/14/2015.
 */
public class CardItemModel {

	private int id;
	private int iconResId;
	private String title;
	private String content;

	public CardItemModel(int id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setIconResId(int iconResId) {
		this.iconResId = iconResId;
	}

	public int getIconResId() {
		return R.drawable.ic_launcher;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
