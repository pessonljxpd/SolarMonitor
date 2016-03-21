package com.shelly.solarmonitor.storage;

import android.content.Context;

import com.shelly.solarmonitor.domin.repository.DisplayInfoRepository;
import com.ty.solarmonitor.R;

public class DisplayInfoRepositoryImpl implements DisplayInfoRepository {

	private Context mContext;

	public DisplayInfoRepositoryImpl(Context context) {
		this.mContext = context;
	}

	@Override
	public String getWtgInfo() {
		String msg = mContext.getString(R.string.dummy_string);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return msg;
	}

}
