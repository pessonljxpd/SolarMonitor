package com.shelly.solarmonitor.storage;

import android.content.Context;

import com.shelly.solarmonitor.domin.repository.WtgInfoRepository;
import com.ty.solarmonitor.R;

public class WtgInfoRepositoryImpl implements WtgInfoRepository {

	private Context mContext;

	public WtgInfoRepositoryImpl(Context context) {
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
